package com.hackathon.fiap.timesheet.adapter.out.repository.entity;

import com.hackathon.fiap.timesheet.application.core.constant.EmployeeRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Employee")
@Table(name = "employee")
public class EmployeeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_Id")
    private Long employeeId;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private EmployeeRole role;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean active;
}
