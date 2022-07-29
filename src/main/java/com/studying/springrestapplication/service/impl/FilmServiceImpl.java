package com.studying.springrestapplication.service.impl;

import com.studying.springrestapplication.dto.FilmDto;
import com.studying.springrestapplication.mapper.FilmMapper;
import com.studying.springrestapplication.model.entity.Film;
import com.studying.springrestapplication.model.repository.FilmRepository;
import com.studying.springrestapplication.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;
    private final FilmMapper filmMapper;

    @Override
    public void saveFilm(FilmDto filmDto) {
        Film film = filmMapper.filmDtoToFilm(filmDto);

        filmRepository.save(film);
    }
}
