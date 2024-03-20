package com.hackathon.fiap.timesheet.application.core.usecase;

import com.hackathon.fiap.timesheet.application.core.contants.EmployeeRole;
import com.hackathon.fiap.timesheet.application.core.domain.Employee;
import com.hackathon.fiap.timesheet.application.core.exptions.EmployeeNotFound;
import com.hackathon.fiap.timesheet.application.core.exptions.InvalidFormat;
import com.hackathon.fiap.timesheet.application.core.ports.in.EmployeeInputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.EmployeeOutputPort;

import java.util.List;
import java.util.regex.Pattern;

public class EmployeeUseCase implements EmployeeInputPort {
    private final EmployeeOutputPort employeeOutputPort;

    public EmployeeUseCase(EmployeeOutputPort employeeOutputPort) {
        this.employeeOutputPort = employeeOutputPort;
    }

    @Override
    public Employee create(String name, EmployeeRole role) {
        validateEmployee(name);
        Employee employee = new Employee();
        employee.setName(name);
        employee.setRole(role);
        employee.setActive(true);
        return employeeOutputPort.save(employee);
    }

    @Override
    public Employee update(Long employeeId, String name, EmployeeRole role, Boolean active) {
        validateEmployee(name);
        Employee employee = get(employeeId);
        employee.setName(name);
        employee.setRole(role);
        employee.setActive(active);
        return employeeOutputPort.save(employee);
    }

    @Override
    public void delete(Long employeeId) {
        if(!employeeOutputPort.exists(employeeId)) throw new EmployeeNotFound("Employee not found");
        employeeOutputPort.delete(employeeId);
    }

    @Override
    public Employee get(Long employeeId) {
        return employeeOutputPort.get(employeeId).orElseThrow(() -> new  EmployeeNotFound("Employee not found"));
    }

    @Override
    public List<Employee> listEmployees() {
        return employeeOutputPort.listEmployees();
    }

    private void validateEmployee(String name) {
        if(!isValidName(name)) throw new InvalidFormat("Invalid name");
    }

    private static boolean isValidName(String name) {
        String regex = "^[a-zA-Z]+(?:\\s[a-zA-Z]+)*$";
        return Pattern.matches(regex, name);
    }
}
