package com.studying.springrestapplication;

import com.studying.springrestapplication.model.entity.Director;
import com.studying.springrestapplication.model.entity.Film;
import com.studying.springrestapplication.model.repository.DirectorRepository;
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

    @SpyBean
    private DirectorRepository directorRepository;

    @Test
    void test() {
        Director director = directorRepository.findByFirstNameAndLastName("Roma","Goncharov");
        System.out.println();
    }
}
