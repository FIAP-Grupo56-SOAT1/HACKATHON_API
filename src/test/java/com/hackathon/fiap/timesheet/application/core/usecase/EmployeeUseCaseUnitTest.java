package com.hackathon.fiap.timesheet.application.core.usecase;

import com.hackathon.fiap.timesheet.application.core.constant.EmployeeRole;
import com.hackathon.fiap.timesheet.application.core.domain.Employee;
import com.hackathon.fiap.timesheet.application.core.ports.out.EmployeeOutputPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class EmployeeUseCaseUnitTest {
    @Mock
    private EmployeeOutputPort employeeOutputPort;
    @InjectMocks
    private EmployeeUseCase employeeUseCase;
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
    void create() {
        // Arrange
        String name = "John Doe";
        String email = "john.doe@test.com";
        EmployeeRole role = EmployeeRole.MANAGER;
        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        employee.setRole(role);
        employee.setActive(true);

        when(employeeOutputPort.save(any(Employee.class))).thenReturn(employee);

        // Act
        Employee result = employeeUseCase.create(name, email, role);

        // Assert
        assertEquals(employee, result);
    }

    @Test
    void update() {
        // Arrange
        Long employeeId = 1L;
        String name = "John Doe";
        String email = "john.doe@test.com";
        EmployeeRole role = EmployeeRole.MANAGER;
        Boolean active = true;
        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        employee.setRole(role);
        employee.setActive(active);

        when(employeeOutputPort.get(employeeId)).thenReturn(Optional.of(employee));
        when(employeeOutputPort.save(any(Employee.class))).thenReturn(employee);

        // Act
        Employee result = employeeUseCase.update(employeeId, name, email, role, active);

        // Assert
        assertEquals(employee, result);
    }

    @Test
    void delete() {
        // Arrange
        Long employeeId = 1L;

        when(employeeOutputPort.exists(employeeId)).thenReturn(true);

        // Act
        employeeUseCase.delete(employeeId);

        // Assert
        verify(employeeOutputPort).delete(employeeId);
    }

    @Test
    void get() {
        // Arrange
        Long employeeId = 1L;
        Employee employee = new Employee();

        when(employeeOutputPort.get(employeeId)).thenReturn(Optional.of(employee));

        // Act
        Employee result = employeeUseCase.get(employeeId);

        // Assert
        assertEquals(employee, result);
    }

    @Test
    void listEmployees() {
        // Arrange
        Employee employee1 = new Employee();
        Employee employee2 = new Employee();
        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeOutputPort.listEmployees()).thenReturn(employees);

        // Act
        List<Employee> result = employeeUseCase.listEmployees();

        // Assert
        assertEquals(employees, result);
    }
}