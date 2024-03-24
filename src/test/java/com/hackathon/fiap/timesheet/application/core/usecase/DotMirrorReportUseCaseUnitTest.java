package com.hackathon.fiap.timesheet.application.core.usecase;

import com.hackathon.fiap.timesheet.application.core.constant.EmployeeRole;
import com.hackathon.fiap.timesheet.application.core.constant.PointRecordType;
import com.hackathon.fiap.timesheet.application.core.domain.Employee;
import com.hackathon.fiap.timesheet.application.core.domain.PointRecord;
import com.hackathon.fiap.timesheet.application.core.ports.out.EmployeeOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.PointRecordOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.SendDotMirrorEmailOutputPort;
import com.hackathon.fiap.timesheet.application.core.reports.DefaultDotMirrorReportData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DotMirrorReportUseCaseUnitTest {
    @Mock
    private EmployeeOutputPort employeeOutputPort;
    @Mock
    private PointRecordOutputPort pointRecordOutputPort;
    @Mock
    private SendDotMirrorEmailOutputPort sendDotMirrorEmailOutputPort;
    @InjectMocks
    private DotMirrorReportUseCase dotMirrorReportUseCase;
    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void generate() {
        // Arrange
        Long employeeId = 1L;
        Employee employee = new Employee();
        employee.setEmployeeId(employeeId);
        employee.setName("Test");
        YearMonth thisMonth = YearMonth.now();
        int lastMonth = thisMonth.minusMonths(1).getMonthValue();
        int year = thisMonth.getYear();
        LocalDate startDate = LocalDate.of(year, lastMonth, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
        PointRecord pointRecord = new PointRecord();
        pointRecord.setEmployeeId(employeeId);
        pointRecord.setDate(startDate);
        pointRecord.setTime(LocalTime.now());
        pointRecord.setType(PointRecordType.IN);
        List<PointRecord> pointRecords = List.of(pointRecord);

        when(employeeOutputPort.get(employeeId)).thenReturn(Optional.of(employee));
        when(pointRecordOutputPort.findByEmployeeIdAndMonthAndYear(employeeId, startDate, endDate)).thenReturn(pointRecords);

        // Act
        DefaultDotMirrorReportData result = dotMirrorReportUseCase.generate(employeeId);

        // Assert
        assertEquals(employeeId, result.getEmployeeId());
        assertEquals(employee.getName(), result.getEmployeeName());
        assertEquals(lastMonth, result.getMonth());
        assertEquals(year, result.getYear());
        assertFalse(result.getPointRecords().isEmpty());
    }

    @Test
    void generateAndSendByEmail() {
        // Arrange
        String email = "test@test.com";
        Long employeeId = 1L;
        Employee employee = new Employee(employeeId, "Test", "test@test.com", EmployeeRole.MANAGER, true);
        YearMonth thisMonth = YearMonth.now();
        int lastMonth = thisMonth.minusMonths(1).getMonthValue();
        int year = thisMonth.getYear();
        LocalDate startDate = LocalDate.of(year, lastMonth, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);

        List<PointRecord> pointRecords = List.of(
                new PointRecord(1L, employeeId, startDate, LocalTime.of(8, 0), PointRecordType.IN),
                new PointRecord(2L, employeeId, startDate, LocalTime.of(12, 0), PointRecordType.OUT),
                new PointRecord(3L, employeeId, startDate, LocalTime.of(13, 0), PointRecordType.IN),
                new PointRecord(4L, employeeId, startDate, LocalTime.of(18, 0), PointRecordType.OUT)
        );

        when(employeeOutputPort.get(employeeId)).thenReturn(Optional.of(employee));
        when(pointRecordOutputPort.findByEmployeeIdAndMonthAndYear(employeeId, startDate, endDate)).thenReturn(pointRecords);

        // Act
        dotMirrorReportUseCase.generateAndSendByEmail(email, employeeId);

        // Assert
        verify(sendDotMirrorEmailOutputPort).send(eq(email), any(DefaultDotMirrorReportData.class));
    }
}