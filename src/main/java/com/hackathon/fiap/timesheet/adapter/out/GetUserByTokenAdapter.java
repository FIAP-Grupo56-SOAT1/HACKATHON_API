package com.hackathon.fiap.timesheet.adapter.out;

import com.hackathon.fiap.timesheet.application.core.domain.User;
import com.hackathon.fiap.timesheet.application.core.ports.out.GetSubjectOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.GetUserByTokenOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.UserOutputPort;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class GetUserByTokenAdapter implements GetUserByTokenOutputPort {
    private final GetSubjectOutputPort getSubjectOutputPort;
    private final UserOutputPort userOutputPort;

    @Override
    public User getUserByToken(HttpServletRequest request) {
        var tokenJWT = recuperarToken(request);
        if (tokenJWT != null) {
            var subject = getSubjectOutputPort.getSubject(tokenJWT);
            Optional<User> user = userOutputPort.get(subject);
            return user.orElse(null);
        }
        return null;
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }
        return null;
    }
}
