package com.hackathon.fiap.timesheet.application.core.usecase;

import com.hackathon.fiap.timesheet.adapter.out.repository.entity.UserEntity;
import com.hackathon.fiap.timesheet.application.core.domain.User;
import com.hackathon.fiap.timesheet.application.core.ports.in.AutenticationInputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.GenerateTokenOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.GetSubjectOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.GetUserByTokenOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.UsernamePasswordAuthenticationTokenOutputPort;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AutenticationUseCase implements AutenticationInputPort {
    private final  GenerateTokenOutputPort generateTokenOutputPort;
    private final  GetSubjectOutputPort getSubjectOutputPort;
    private final GetUserByTokenOutputPort getUserByTokenOutputPort;

    private final UsernamePasswordAuthenticationTokenOutputPort  usernamePasswordAuthenticationTokenOutputPort;

    public AutenticationUseCase(GenerateTokenOutputPort generateTokenOutputPort,
                                GetSubjectOutputPort getSubjectOutputPort, GetUserByTokenOutputPort getUserByTokenOutputPort, UsernamePasswordAuthenticationTokenOutputPort usernamePasswordAuthenticationTokenOutputPort) {
        this.generateTokenOutputPort = generateTokenOutputPort;
        this.getSubjectOutputPort = getSubjectOutputPort;
        this.getUserByTokenOutputPort = getUserByTokenOutputPort;
        this.usernamePasswordAuthenticationTokenOutputPort = usernamePasswordAuthenticationTokenOutputPort;
    }

    @Override
    public UsernamePasswordAuthenticationToken GetUsernamePasswordAuthenticationToken(String userId, String password) {
        return usernamePasswordAuthenticationTokenOutputPort.GetUsernamePasswordAuthenticationToken(userId,password);
    }

    @Override
    public String GenerateTokenJwt(UserEntity user) {
        return generateTokenOutputPort.GenerateTokenJwt(user);
    }

    @Override
    public String GetSubject(String tokenJWT) {
        return getSubjectOutputPort.GetSubject(tokenJWT);
    }

    @Override
    public User GetUserByToken(HttpServletRequest request) {
        return getUserByTokenOutputPort.GetUserByToken(request);
    }




}
