package com.studying.springrestapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studying.springrestapplication.dto.ErrorResponse;
import com.studying.springrestapplication.dto.JwtRequest;
import com.studying.springrestapplication.dto.JwtResponse;
import com.studying.springrestapplication.dto.RefreshJwtRequest;
import com.studying.springrestapplication.service.AuthenticationService;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthenticationService authenticationService;

    @Test
    void whenAuthentication_AndValidRequest_thenExpected2xxResponse() throws Exception {
        JwtRequest jwtRequest = new JwtRequest("user", "password");
        String inputJson = objectMapper.writeValueAsString(jwtRequest);

        JwtResponse jwtResponse = new JwtResponse("Access Token", "Refresh Token");
        String outputJson = objectMapper.writeValueAsString(jwtResponse);

        when(authenticationService.getJwtIfSuccessfulLogin(jwtRequest)).thenReturn(jwtResponse);

        mockMvc.perform(post("/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(content().json(outputJson))
                .andExpect(status().isOk());

        verify(authenticationService, times(1)).getJwtIfSuccessfulLogin(jwtRequest);
    }

    @Test
    void whenAuthentication_AndInvalidRequest_thenExpected4xxResponse() throws Exception {
        JwtRequest jwtRequest = new JwtRequest("", "password");
        String inputJson = objectMapper.writeValueAsString(jwtRequest);

        List<String> messages = List.of("The field 'username' must not be empty");

        ErrorResponse errorResponse = new ErrorResponse("Bad Request", messages);
        String outputJson = objectMapper.writeValueAsString(errorResponse);

        mockMvc.perform(post("/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(content().json(outputJson))
                .andExpect(status().isBadRequest());

        verify(authenticationService, never()).getJwtIfSuccessfulLogin(jwtRequest);
    }

    @Test
    void whenGetNewAccessToken_AndValidRequest_thenExpected2xxResponse() throws Exception {
        RefreshJwtRequest refreshJwtRequest = new RefreshJwtRequest("Refresh Token");
        String inputJson = objectMapper.writeValueAsString(refreshJwtRequest);

        JwtResponse jwtResponse = new JwtResponse("New Access Token", "");
        String outputJson = objectMapper.writeValueAsString(jwtResponse);

        when(authenticationService.getNewAccessTokenUsingRefreshToken(refreshJwtRequest)).thenReturn(jwtResponse);

        mockMvc.perform(post("/auth/newAccessToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(content().json(outputJson))
                .andExpect(status().isOk());

        verify(authenticationService, times(1)).getNewAccessTokenUsingRefreshToken(refreshJwtRequest);
    }

    @Test
    void whenGetNewAccessToken_AndInvalidRequest_thenExpected4xxResponse() throws Exception {
        RefreshJwtRequest refreshJwtRequest = new RefreshJwtRequest("");
        String inputJson = objectMapper.writeValueAsString(refreshJwtRequest);

        List<String> messages = List.of("The field 'refreshToken' must not be empty");

        ErrorResponse errorResponse = new ErrorResponse("Bad Request", messages);
        String outputJson = objectMapper.writeValueAsString(errorResponse);

        mockMvc.perform(post("/auth/newAccessToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(content().json(outputJson))
                .andExpect(status().isBadRequest());

        verify(authenticationService, never()).getNewAccessTokenUsingRefreshToken(refreshJwtRequest);
    }

    @Test
    void whenGetNewJwt_AndValidRequest_thenExpected2xxResponse() throws Exception {
        RefreshJwtRequest refreshJwtRequest = new RefreshJwtRequest("Refresh Token");
        String inputJson = objectMapper.writeValueAsString(refreshJwtRequest);

        JwtResponse jwtResponse = new JwtResponse("New Access Token", "New Refresh Token");
        String outputJson = objectMapper.writeValueAsString(jwtResponse);

        when(authenticationService.getNewJwtUsingRefreshToken(refreshJwtRequest)).thenReturn(jwtResponse);

        mockMvc.perform(post("/auth/newJwt")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(content().json(outputJson))
                .andExpect(status().isOk());

        verify(authenticationService, times(1)).getNewJwtUsingRefreshToken(refreshJwtRequest);
    }

    @Test
    void whenGetNewJwt_AndInvalidRequest_thenExpected4xxResponse() throws Exception {
        RefreshJwtRequest refreshJwtRequest = new RefreshJwtRequest("");
        String inputJson = objectMapper.writeValueAsString(refreshJwtRequest);

        List<String> messages = List.of("The field 'refreshToken' must not be empty");

        ErrorResponse errorResponse = new ErrorResponse("Bad Request", messages);
        String outputJson = objectMapper.writeValueAsString(errorResponse);

        mockMvc.perform(post("/auth/newJwt")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(content().json(outputJson))
                .andExpect(status().isBadRequest());

        verify(authenticationService, never()).getNewJwtUsingRefreshToken(refreshJwtRequest);
    }
}