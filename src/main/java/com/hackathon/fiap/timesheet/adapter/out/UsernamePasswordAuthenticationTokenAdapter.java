package com.hackathon.fiap.timesheet.adapter.out;

import com.hackathon.fiap.timesheet.application.core.ports.out.UsernamePasswordAuthenticationTokenOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class UsernamePasswordAuthenticationTokenAdapter implements UsernamePasswordAuthenticationTokenOutputPort {
    @Override
    public UsernamePasswordAuthenticationToken GetUsernamePasswordAuthenticationToken(String userId, String password) {
        return new UsernamePasswordAuthenticationToken(userId, password);
    }
}
