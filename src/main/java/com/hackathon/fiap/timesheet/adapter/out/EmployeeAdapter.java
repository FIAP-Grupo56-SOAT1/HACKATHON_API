package com.hackathon.fiap.timesheet.adapter.out;


import com.hackathon.fiap.timesheet.application.core.domain.Employee;
import com.hackathon.fiap.timesheet.application.core.ports.out.EmployeeOutputPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EmployeeAdapter implements EmployeeOutputPort{

    @Override
    public Employee save(Employee employee) {
        return null;
    }

    @Override
    public void delete(Long employeeId) {

    }

    @Override
    public Optional<Employee> get(Long employeeId) {
        return Optional.empty();
    }

    @Override
    public Boolean exists(Long employeeId) {
        return null;
    }

    @Override
    public Optional<List<Employee>> listEmployees() {
        return Optional.empty();
    }
}
