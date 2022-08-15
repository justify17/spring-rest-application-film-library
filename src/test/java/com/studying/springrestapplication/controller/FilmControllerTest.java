package com.studying.springrestapplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studying.springrestapplication.dto.ErrorResponse;
import com.studying.springrestapplication.dto.FilmDto;
import com.studying.springrestapplication.model.enumeration.Language;
import com.studying.springrestapplication.service.FilmService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class FilmControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FilmService filmService;

    @Test
    void whenGetFilms_AndRequestIsFine_thenExpected2xxResponse() throws Exception {
        List<FilmDto> films = List.of(new FilmDto("Film", LocalDate.now(), 130, Language.ENGLISH));
        String outputJson = objectMapper.writeValueAsString(films);

        when(filmService.getAllFilms()).thenReturn(films);

        mockMvc.perform(get("/films")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(outputJson))
                .andExpect(status().isOk());

        verify(filmService, times(1)).getAllFilms();
    }

    @Test
    void whenGetFilms_AndRequestIsFine_AndFilmsNotFound_thenExpected2xxResponse() throws Exception {
        List<FilmDto> films = Collections.emptyList();

        when(filmService.getAllFilms()).thenReturn(films);

        mockMvc.perform(get("/films")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(filmService, times(1)).getAllFilms();
    }

    @Test
    void whenGetFilm_AndRequestIsFine_thenExpected2xxResponse() throws Exception {
        Long id = 1L;

        FilmDto filmDto = new FilmDto("Film", LocalDate.now(), 130, Language.ENGLISH);
        String outputJson = objectMapper.writeValueAsString(filmDto);

        when(filmService.getFilmById(id)).thenReturn(filmDto);

        mockMvc.perform(get("/films/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(outputJson))
                .andExpect(status().isOk());

        verify(filmService, times(1)).getFilmById(id);
    }

    @Test
    void whenCreateFilm_AndValidRequest_thenExpected2xxResponse() throws Exception {
        FilmDto filmDto = new FilmDto("New Film", LocalDate.now(), 130, Language.ENGLISH);
        String inputJson = objectMapper.writeValueAsString(filmDto);

        mockMvc.perform(post("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isOk());

        verify(filmService, times(1)).saveFilm(filmDto);
    }

    @Test
    void whenCreateFilm_AndInvalidRequest_thenExpected4xxResponse() throws Exception {
        FilmDto filmDto = new FilmDto("", null, 0, null);
        String inputJson = objectMapper.writeValueAsString(filmDto);

        List<String> messages = List.of(
                "The field 'title' must not be empty",
                "The field 'releaseDate' is required",
                "The field 'runningTime' is required and must be positive",
                "The field 'language' is required");

        ErrorResponse errorResponse = new ErrorResponse("Bad Request", messages);
        String outputJson = objectMapper.writeValueAsString(errorResponse);

        mockMvc.perform(post("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(content().json(outputJson))
                .andExpect(status().isBadRequest());

        verify(filmService, never()).saveFilm(filmDto);
    }

    @Test
    void whenUpdateFilm_AndValidRequest_thenExpected2xxResponse() throws Exception {
        FilmDto filmDto = new FilmDto("Updated Film", LocalDate.now(), 130, Language.ENGLISH);
        filmDto.setId(1L);

        String inputJson = objectMapper.writeValueAsString(filmDto);

        mockMvc.perform(put("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(status().isOk());

        verify(filmService, times(1)).updateFilm(filmDto);
    }

    @Test
    void whenUpdateFilm_AndInvalidRequest_thenExpected4xxResponse() throws Exception {
        FilmDto filmDto = new FilmDto("", null, 0, null);
        filmDto.setId(1L);

        String inputJson = objectMapper.writeValueAsString(filmDto);

        List<String> messages = List.of(
                "The field 'title' must not be empty",
                "The field 'releaseDate' is required",
                "The field 'runningTime' is required and must be positive",
                "The field 'language' is required");

        ErrorResponse errorResponse = new ErrorResponse("Bad Request", messages);
        String outputJson = objectMapper.writeValueAsString(errorResponse);

        mockMvc.perform(put("/films")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson))
                .andExpect(content().json(outputJson))
                .andExpect(status().isBadRequest());

        verify(filmService, never()).updateFilm(filmDto);
    }

    @Test
    void whenDeleteFilm_AndRequestIsFine_thenExpected2xxResponse() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/films/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(filmService, times(1)).deleteFilmById(id);
    }
}