package com.hackathon.fiap.timesheet.application.core.usecase;

import com.hackathon.fiap.timesheet.application.core.constant.EmployeeRole;
import com.hackathon.fiap.timesheet.application.core.domain.Employee;
import com.hackathon.fiap.timesheet.application.core.exptions.EmployeeNotFoundException;
import com.hackathon.fiap.timesheet.application.core.exptions.InvalidFormatException;
import com.hackathon.fiap.timesheet.application.core.ports.in.EmployeeInputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.EmployeeOutputPort;
import com.hackathon.fiap.timesheet.application.core.validator.EmailValidator;

import java.util.List;
import java.util.regex.Pattern;

public class EmployeeUseCase implements EmployeeInputPort {
    private final EmployeeOutputPort employeeOutputPort;
    private static final Pattern EMP_NAME_PATTERN = Pattern.compile("^[a-zA-Z]+(\\s[a-zA-Z]+)?+$");

    public EmployeeUseCase(EmployeeOutputPort employeeOutputPort) {
        this.employeeOutputPort = employeeOutputPort;
    }

    @Override
    public Employee create(String name, String email, EmployeeRole role) {
        validateEmployee(name);
        if (!EmailValidator.isValidEmail(email)) throw new InvalidFormatException("Invalid email");
        Employee employee = new Employee();
        employee.setName(name);
        employee.setEmail(email);
        employee.setRole(role);
        employee.setActive(true);
        return employeeOutputPort.save(employee);
    }

    @Override
    public Employee update(Long employeeId, String name, String email, EmployeeRole role, Boolean active) {
        validateEmployee(name);
        if (!EmailValidator.isValidEmail(email)) throw new InvalidFormatException("Invalid email");
        Employee employee = get(employeeId);
        employee.setName(name);
        employee.setEmail(email);
        employee.setRole(role);
        employee.setActive(active);
        return employeeOutputPort.save(employee);
    }

    @Override
    public void delete(Long employeeId) {
        if(Boolean.FALSE.equals(employeeOutputPort.exists(employeeId))) throw new EmployeeNotFoundException("Employee not found");
        employeeOutputPort.delete(employeeId);
    }

    @Override
    public Employee get(Long employeeId) {
        return employeeOutputPort.get(employeeId).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
    }

    @Override
    public List<Employee> listEmployees() {
        return employeeOutputPort.listEmployees();
    }

    private void validateEmployee(String name) {
        if(!isValidName(name)) throw new InvalidFormatException("Invalid name");
    }

    private static boolean isValidName(String name) {
        return EMP_NAME_PATTERN.matcher(name).matches();
    }
}
