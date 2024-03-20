package com.hackathon.fiap.timesheet.application.core.ports.in;

import com.hackathon.fiap.timesheet.application.core.contants.EmployeeRole;
import com.hackathon.fiap.timesheet.application.core.domain.Employee;

import java.util.List;

public interface EmployeeInputPort {
    Employee create(String name, EmployeeRole role);
    Employee update(Long employeeId, String name, EmployeeRole role, Boolean active);
    void delete(Long employeeId);
    Employee get(Long employeeId);
    List<Employee> listEmployees();
}
