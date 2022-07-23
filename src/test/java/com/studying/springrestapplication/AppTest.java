package com.studying.springrestapplication;

import com.studying.springrestapplication.model.entity.Director;
import com.studying.springrestapplication.model.entity.Film;
import com.studying.springrestapplication.model.repository.FilmRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class AppTest {
    @SpyBean
    private FilmRepository filmRepository;

    @Test
    void test() {
        Film film = new Film();
        film.setTitle("New Film 2");

        Director director = new Director("Roma","Goncharov");
        Director director1 = new Director("Vasya","Mayorov");

        film.addDirector(director);
        film.addDirector(director1);

        filmRepository.save(film);
    }

    @Test
    void test2(){
        List<Film> films = filmRepository.findAll();
        System.out.println();
    }
}
