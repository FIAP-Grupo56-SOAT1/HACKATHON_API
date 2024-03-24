package com.hackathon.fiap.timesheet.application.core.usecase;

import com.hackathon.fiap.timesheet.adapter.out.repository.entity.UserEntity;
import com.hackathon.fiap.timesheet.application.core.domain.User;
import com.hackathon.fiap.timesheet.application.core.ports.out.GenerateTokenOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.GetSubjectOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.GetUserByTokenOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.UsernamePasswordAuthenticationTokenOutputPort;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AutenticationUseCaseUnitTest {
    @Mock
    private GenerateTokenOutputPort generateTokenOutputPort;
    @Mock
    private GetSubjectOutputPort getSubjectOutputPort;
    @Mock
    private GetUserByTokenOutputPort getUserByTokenOutputPort;
    @Mock
    private UsernamePasswordAuthenticationTokenOutputPort usernamePasswordAuthenticationTokenOutputPort;
    @InjectMocks
    private AutenticationUseCase autenticationUseCase;
    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void getUsernamePasswordAuthenticationToken() {
        // Arrange
        String userId = "testUser";
        String password = "testPassword";
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userId, password);

        when(usernamePasswordAuthenticationTokenOutputPort.getUsernamePasswordAuthenticationToken(userId, password)).thenReturn(token);

        // Act
        UsernamePasswordAuthenticationToken result = autenticationUseCase.getUsernamePasswordAuthenticationToken(userId, password);

        // Assert
        assertEquals(token, result);
    }

    @Test
    void generateTokenJwt() {
        // Arrange
        UserEntity user = new UserEntity();
        String token = "testToken";

        when(generateTokenOutputPort.generateTokenJwt(user)).thenReturn(token);

        // Act
        String result = autenticationUseCase.generateTokenJwt(user);

        // Assert
        assertEquals(token, result);
    }

    @Test
    void getSubject() {
        // Arrange
        String tokenJWT = "testToken";
        String subject = "testSubject";

        when(getSubjectOutputPort.getSubject(tokenJWT)).thenReturn(subject);

        // Act
        String result = autenticationUseCase.getSubject(tokenJWT);

        // Assert
        assertEquals(subject, result);
    }

    @Test
    void getUserByToken() {
        // Arrange
        HttpServletRequest request = mock(HttpServletRequest.class);
        User user = new User();

        when(getUserByTokenOutputPort.getUserByToken(request)).thenReturn(user);

        // Act
        User result = autenticationUseCase.getUserByToken(request);

        // Assert
        assertEquals(user, result);
    }
}