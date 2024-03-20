package com.hackathon.fiap.timesheet.application.core.ports.in;

import com.hackathon.fiap.timesheet.application.core.domain.Employee;

public interface EmployeeInputPort {
    Employee create(String name, String email, String password, String role);
    Employee update(Long employeeId, String name, String email, String password, String role);
    void delete(Long employeeId);
    Employee get(Long employeeId);
    void listEmployees();
}
