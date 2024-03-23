package com.hackathon.fiap.timesheet.security;

import com.hackathon.fiap.timesheet.application.core.ports.in.UserInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticationService implements UserDetailsService {
    private final UserInputPort userInputPort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userInputPort.findByUserName(username);
    }
}
