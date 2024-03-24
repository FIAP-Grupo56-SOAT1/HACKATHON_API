package com.hackathon.fiap.timesheet.application.core.usecase;

import com.hackathon.fiap.timesheet.application.core.constant.PointRecordType;
import com.hackathon.fiap.timesheet.application.core.domain.PointRecord;
import com.hackathon.fiap.timesheet.application.core.ports.out.EmployeeOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.PointRecordOutputPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PointRecordUseCaseUnitTest {
    @Mock
    private PointRecordOutputPort pointRecordOutputPort;
    @Mock
    private EmployeeOutputPort employeeOutputPort;
    @InjectMocks
    private PointRecordUseCase pointRecordUseCase;
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
    void recordPoint() {
        // Arrange
        Long employeeId = 1L;
        PointRecordType type = PointRecordType.IN;
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        PointRecord pointRecord = new PointRecord();
        pointRecord.setEmployeeId(employeeId);
        pointRecord.setDate(date);
        pointRecord.setTime(time);
        pointRecord.setType(type);

        when(employeeOutputPort.exists(employeeId)).thenReturn(true);
        when(pointRecordOutputPort.getLastPointRecord(employeeId, date)).thenReturn(Optional.empty());
        when(pointRecordOutputPort.save(any(PointRecord.class))).thenReturn(pointRecord);

        // Act
        PointRecord result = pointRecordUseCase.recordPoint(employeeId, type);

        // Assert
        assertEquals(pointRecord, result);
    }

    @Test
    void manualRecordPoint() {
        // Arrange
        Long employeeId = 1L;
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        PointRecordType type = PointRecordType.IN;
        PointRecord pointRecord = new PointRecord();
        pointRecord.setEmployeeId(employeeId);
        pointRecord.setDate(date);
        pointRecord.setTime(time);
        pointRecord.setType(type);

        when(employeeOutputPort.exists(employeeId)).thenReturn(true);
        when(pointRecordOutputPort.getLastPointRecord(employeeId, date)).thenReturn(Optional.empty());
        when(pointRecordOutputPort.save(any(PointRecord.class))).thenReturn(pointRecord);

        // Act
        PointRecord result = pointRecordUseCase.manualRecordPoint(employeeId, date, time, type);

        // Assert
        assertEquals(pointRecord, result);
    }

    @Test
    void get() {
        // Arrange
        Long pointRecordId = 1L;
        PointRecord pointRecord = new PointRecord();

        when(pointRecordOutputPort.get(pointRecordId)).thenReturn(Optional.of(pointRecord));

        // Act
        PointRecord result = pointRecordUseCase.get(pointRecordId);

        // Assert
        assertEquals(pointRecord, result);
    }

    @Test
    void list() {
        // Arrange
        PointRecord pointRecord1 = new PointRecord();
        PointRecord pointRecord2 = new PointRecord();
        List<PointRecord> pointRecords = Arrays.asList(pointRecord1, pointRecord2);

        when(pointRecordOutputPort.list()).thenReturn(pointRecords);

        // Act
        List<PointRecord> result = pointRecordUseCase.list();

        // Assert
        assertEquals(pointRecords, result);
    }

    @Test
    void listByDate() {
        // Arrange
        LocalDate date = LocalDate.now();
        PointRecord pointRecord1 = new PointRecord();
        PointRecord pointRecord2 = new PointRecord();
        List<PointRecord> pointRecords = Arrays.asList(pointRecord1, pointRecord2);

        when(pointRecordOutputPort.listByDate(date)).thenReturn(pointRecords);

        // Act
        List<PointRecord> result = pointRecordUseCase.listByDate(date);

        // Assert
        assertEquals(pointRecords, result);
    }

    @Test
    void listByEmployeeId() {
        // Arrange
        Long employeeId = 1L;
        PointRecord pointRecord1 = new PointRecord();
        PointRecord pointRecord2 = new PointRecord();
        List<PointRecord> pointRecords = Arrays.asList(pointRecord1, pointRecord2);

        when(pointRecordOutputPort.listByEmployeeId(employeeId)).thenReturn(pointRecords);

        // Act
        List<PointRecord> result = pointRecordUseCase.listByEmployeeId(employeeId);

        // Assert
        assertEquals(pointRecords, result);
    }

    @Test
    void listByDateAndEmployeeId() {
        // Arrange
        Long employeeId = 1L;
        LocalDate date = LocalDate.now();
        PointRecord pointRecord1 = new PointRecord();
        PointRecord pointRecord2 = new PointRecord();
        List<PointRecord> pointRecords = Arrays.asList(pointRecord1, pointRecord2);

        when(pointRecordOutputPort.listByDateAndEmployeeId(employeeId, date)).thenReturn(pointRecords);

        // Act
        List<PointRecord> result = pointRecordUseCase.listByDateAndEmployeeId(employeeId, date);

        // Assert
        assertEquals(pointRecords, result);
    }
}