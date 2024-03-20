package com.hackathon.fiap.timesheet.adapter.out;


import com.hackathon.fiap.timesheet.adapter.out.repository.EmployeeRepository;
import com.hackathon.fiap.timesheet.adapter.out.repository.entity.EmployeeEntity;
import com.hackathon.fiap.timesheet.adapter.out.repository.mapper.EmployeeEntityMapper;
import com.hackathon.fiap.timesheet.application.core.domain.Employee;
import com.hackathon.fiap.timesheet.application.core.ports.out.EmployeeOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeAdapter implements EmployeeOutputPort {
    private final EmployeeRepository employeeRepository;
    private final EmployeeEntityMapper employeeEntityMapper;

    @Override
    public Employee save(Employee employee) {
        EmployeeEntity employeeEntity = employeeEntityMapper.toEmployeeEntity(employee);
        EmployeeEntity employeeSaved = employeeRepository.save(employeeEntity);
        return employeeEntityMapper.toEmployee(employeeSaved);
    }

    @Override
    public void delete(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public Optional<Employee> get(Long employeeId) {
        return employeeRepository.findById(employeeId).map(employeeEntityMapper::toEmployee);
    }

    @Override
    public Boolean exists(Long employeeId) {
        return employeeRepository.existsById(employeeId);
    }

    @Override
    public List<Employee> listEmployees() {
        return employeeRepository.findAll().stream().map(employeeEntityMapper::toEmployee).toList();
    }
}
