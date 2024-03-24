package com.hackathon.fiap.timesheet.application.core.usecase;

import com.hackathon.fiap.timesheet.adapter.out.repository.entity.UserEntity;
import com.hackathon.fiap.timesheet.application.core.domain.User;
import com.hackathon.fiap.timesheet.application.core.ports.out.CryptographyOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.EmployeeOutputPort;
import com.hackathon.fiap.timesheet.application.core.ports.out.UserOutputPort;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserUseCaseUnitTest {
    @Mock
    private UserOutputPort userOutputPort;
    @Mock
    private EmployeeOutputPort employeeOutputPort;
    @Mock
    private CryptographyOutputPort cryptographyOutputPort;
    @InjectMocks
    private UserUseCase userUseCase;
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
    void create() {
        // Arrange
        String userId = "test@test.com";
        String password = "Password.1234";
        Long employeeId = 1L;
        User userReturn = new User(userId, employeeId, true);

        when(employeeOutputPort.exists(employeeId)).thenReturn(true);
        when(userOutputPort.exists(userId)).thenReturn(false);
        when(userOutputPort.getByEmployeeId(employeeId)).thenReturn(Optional.empty());
        when(cryptographyOutputPort.encrypt(password)).thenReturn("encryptedPassword");
        when(userOutputPort.save(any(User.class), eq("encryptedPassword"))).thenReturn(userReturn);

        // Act
        User user = userUseCase.create(userId, password, employeeId);

        // Assert
        assertEquals(userId, user.getUserId());
        assertEquals(employeeId, user.getEmployeeId());
        assertTrue(user.getActive());

        verify(userOutputPort).save(any(User.class), eq("encryptedPassword"));
        verify(cryptographyOutputPort).encrypt(password);
    }

    @Test
    void updatePassword() {
        // Arrange
        String userId = "test@test.com";
        String password = "Password.1234";

        when(userOutputPort.exists(userId)).thenReturn(true);
        when(cryptographyOutputPort.encrypt(password)).thenReturn("encryptedPassword");

        // Act
        userUseCase.updatePassword(userId, password);

        // Assert
        verify(userOutputPort).exists(userId);
        verify(cryptographyOutputPort).encrypt(password);
        verify(userOutputPort).setPassword(userId, "encryptedPassword");
    }

    @Test
    void updateStatus() {
        // Arrange
        String userId = "test@test.com";
        Boolean active = true;
        User user = new User(userId, 1L, false);

        when(userOutputPort.get(userId)).thenReturn(Optional.of(user));
        when(userOutputPort.save(user)).thenReturn(user);

        // Act
        User updatedUser = userUseCase.updateStatus(userId, active);

        // Assert
        assertTrue(updatedUser.getActive());
        verify(userOutputPort).save(user);
    }

    @Test
    void delete() {
        // Arrange
        String userId = "test@test.com";

        when(userOutputPort.exists(userId)).thenReturn(true);

        // Act
        userUseCase.delete(userId);

        // Assert
        verify(userOutputPort).delete(userId);
    }

    @Test
    void get() {
        // Arrange
        String userId = "test@test.com";
        User user = new User(userId, 1L, true);

        when(userOutputPort.get(userId)).thenReturn(Optional.of(user));

        // Act
        User retrievedUser = userUseCase.get(userId);

        // Assert
        assertEquals(userId, retrievedUser.getUserId());
    }

    @Test
    void listUsers() {
        // Arrange
        User user1 = new User("test1@test.com", 1L, true);
        User user2 = new User("test2@test.com", 2L, true);
        List<User> users = Arrays.asList(user1, user2);

        when(userOutputPort.listUsers()).thenReturn(users);

        // Act
        List<User> retrievedUsers = userUseCase.listUsers();

        // Assert
        assertEquals(users, retrievedUsers);
    }

    @Test
    void findByUserName() {
        // Arrange
        String userName = "test@test.com";
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(userName);

        when(userOutputPort.findByUserName(userName)).thenReturn(Optional.of(userEntity));

        // Act
        UserEntity retrievedUserEntity = userUseCase.findByUserName(userName);

        // Assert
        assertEquals(userName, retrievedUserEntity.getUserId());
    }
}