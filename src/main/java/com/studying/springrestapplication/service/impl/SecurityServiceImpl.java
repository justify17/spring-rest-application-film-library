package com.studying.springrestapplication.service.impl;

import com.studying.springrestapplication.dto.UserDto;
import com.studying.springrestapplication.mapper.UserMapper;
import com.studying.springrestapplication.model.entity.User;
import com.studying.springrestapplication.model.repository.UserRepository;
import com.studying.springrestapplication.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.studying.springrestapplication.model.enumeration.Role.ROLE_USER;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public void registerUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);

        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        user.setRole(ROLE_USER);

        userRepository.save(user);
    }
}
