package com.hackathon.fiap.timesheet.adapter.out.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "User")
@Table(name = "USER")
public class UserEntity {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(nullable = false, length = 50)
    private String password;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private EmployeeEntity employee;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean active;
}
