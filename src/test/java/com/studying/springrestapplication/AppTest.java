package com.studying.springrestapplication;

import com.studying.springrestapplication.model.entity.Director;
import com.studying.springrestapplication.model.repository.FilmCrewMemberRepository;
import com.studying.springrestapplication.model.repository.FilmRepository;
import com.studying.springrestapplication.service.FilmCrewMemberService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class AppTest {
    @SpyBean
    private FilmRepository filmRepository;

    @SpyBean
    private FilmCrewMemberService<Director> directorService;

    @Test
    void test() {
        
    }
}
