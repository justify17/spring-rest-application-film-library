package com.studying.springrestapplication.mapper;

import com.studying.springrestapplication.dto.FilmDto;
import com.studying.springrestapplication.model.entity.Film;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FilmMapper {
    FilmDto filmToFilmDto(Film film);

    Film filmDtoToFilm(FilmDto filmDto);
}
