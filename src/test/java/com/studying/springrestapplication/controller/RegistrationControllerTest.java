package com.studying.springrestapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studying.springrestapplication.dto.ErrorResponse;
import com.studying.springrestapplication.dto.UserDto;
import com.studying.springrestapplication.exception.UsernameIsTakenException;
import com.studying.springrestapplication.service.RegistrationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RegistrationService registrationService;

    @Test
    void whenRegistration_AndValidRequest_thenExpected2xxResponse() throws Exception {
        UserDto userDto = new UserDto("user", "password");
        String inputJson = objectMapper.writeValueAsString(userDto);

        when(registrationService.isUserExisting(userDto)).thenReturn(false);

        mockMvc.perform(post("/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isOk());

        verify(registrationService, times(1)).isUserExisting(userDto);
        verify(registrationService, times(1)).registerUser(userDto);
    }

    @Test
    void whenRegistration_AndValidRequest_AndUserExists_thenExpected4xxResponse() throws Exception {
        UserDto userDto = new UserDto("busyUsername", "password");
        String inputJson = objectMapper.writeValueAsString(userDto);

        when(registrationService.isUserExisting(userDto)).thenReturn(true);

        List<String> messages = List.of("User with username 'busyUsername' already exists");

        ErrorResponse errorResponse = new ErrorResponse("Bad Request", messages);
        String outputJson = objectMapper.writeValueAsString(errorResponse);

        mockMvc.perform(post("/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof UsernameIsTakenException))
                .andExpect(content().json(outputJson))
                .andExpect(status().isBadRequest());

        verify(registrationService, times(1)).isUserExisting(userDto);
        verify(registrationService, never()).registerUser(userDto);
    }

    @Test
    void whenRegistration_AndInvalidRequest_thenExpected4xxResponse() throws Exception {
        UserDto userDto = new UserDto("user", "");
        String inputJson = objectMapper.writeValueAsString(userDto);

        List<String> messages = List.of(
                "The field 'password' must not be empty",
                "The field 'password' must contain at least 4 characters and contain only numbers or Latin letters");

        ErrorResponse errorResponse = new ErrorResponse("Bad Request", messages);
        String outputJson = objectMapper.writeValueAsString(errorResponse);

        mockMvc.perform(post("/registration")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(content().json(outputJson))
                .andExpect(status().isBadRequest());

        verify(registrationService, never()).isUserExisting(userDto);
        verify(registrationService, never()).registerUser(userDto);
    }
}