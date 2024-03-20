package com.hackathon.fiap.timesheet.application.core.ports.out;

import com.hackathon.fiap.timesheet.application.core.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeOutputPort {
    Employee save(Employee employee);

    void delete(Long employeeId);

    Optional<Employee> get(Long employeeId);

    Boolean exists(Long employeeId);

    List<Employee> listEmployees();
}
