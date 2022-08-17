package com.studying.springrestapplication.service.impl;

import com.studying.springrestapplication.dto.UserDto;
import com.studying.springrestapplication.mapper.UserMapper;
import com.studying.springrestapplication.model.entity.User;
import com.studying.springrestapplication.model.enumeration.Role;
import com.studying.springrestapplication.model.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class RegistrationServiceImplTest {

    @InjectMocks
    private RegistrationServiceImpl registrationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @Test
    void registerUser() {
        UserDto userDto = new UserDto("User", "Password");
        User user = new User("User", "Password", null);

        when(userMapper.userDtoToUser(userDto)).thenReturn(user);
        when(passwordEncoder.encode("Password")).thenReturn("Encrypted Password");

        registrationService.registerUser(userDto);

        assertEquals("Encrypted Password", user.getPassword());
        assertEquals(Role.ROLE_USER, user.getRole());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void isUserExisting_AndUserExists_ReturnTrue() {
        UserDto userDto = new UserDto("User", "Password");
        User user = new User("User", "Password", Role.ROLE_USER);

        when(userRepository.findByUsername("User")).thenReturn(user);

        assertTrue(registrationService.isUserExisting(userDto));
    }

    @Test
    void isUserExisting_AndUserDoesNotExist_ReturnFalse() {
        UserDto userDto = new UserDto("User", "Password");

        when(userRepository.findByUsername("User")).thenReturn(null);

        assertFalse(registrationService.isUserExisting(userDto));
    }
}