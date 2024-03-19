package com.hackathon.fiap.timesheet.dataprovider.repository;


import com.hackathon.fiap.timesheet.dataprovider.repository.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository  extends JpaRepository<UsuarioEntity, Long> {
    UserDetails findByLogin(String login);
}
