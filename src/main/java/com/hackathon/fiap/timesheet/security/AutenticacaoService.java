package com.hackathon.fiap.timesheet.security;

import com.hackathon.fiap.timesheet.adapter.out.repository.UserRepository;
import com.hackathon.fiap.timesheet.adapter.out.repository.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> usuario =  repository.findById(username);
        if(usuario.isPresent())
            return usuario.get();

        throw new UsernameNotFoundException("");
    }
}
