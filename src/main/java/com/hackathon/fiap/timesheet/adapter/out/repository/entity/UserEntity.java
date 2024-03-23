package com.hackathon.fiap.timesheet.adapter.out.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "User")
@Table(name = "user")
public class UserEntity implements UserDetails {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(nullable = false, length = 2000)
    private String password;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_Id", referencedColumnName = "employee_Id")
    private EmployeeEntity employee;

    @Column(nullable = false, columnDefinition = "boolean default true")
    private Boolean active;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
