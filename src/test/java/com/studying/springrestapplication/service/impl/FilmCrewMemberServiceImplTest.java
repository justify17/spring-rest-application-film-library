package com.studying.springrestapplication.service.impl;

import com.studying.springrestapplication.model.entity.FilmCrewMember;
import com.studying.springrestapplication.model.repository.FilmCrewMemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class FilmCrewMemberServiceImplTest {

    private FilmCrewMemberServiceImpl<FilmCrewMember, FilmCrewMemberRepository<FilmCrewMember>> filmCrewMemberService;

    @Mock
    private FilmCrewMemberRepository<FilmCrewMember> filmCrewMemberRepository;

    @BeforeEach
    void init() {
        filmCrewMemberService = new FilmCrewMemberServiceImpl<>(filmCrewMemberRepository) {
            @Override
            public void checkIfFilmCrewMembersExist(Collection<FilmCrewMember> filmCrewMembers) {
                super.checkIfFilmCrewMembersExist(filmCrewMembers);
            }
        };
    }

    @Test
    void checkIfFilmCrewMembersExist_AndFilmCrewMembersExist_SetIdForFilmCrewMembers() {
        FilmCrewMember member = new FilmCrewMember("Member Name", "Member Surname");
        List<FilmCrewMember> members = List.of(member);

        FilmCrewMember memberFromDb = new FilmCrewMember("Member Name", "Member Surname");
        memberFromDb.setId(10L);

        when(filmCrewMemberRepository.findByFirstNameAndLastName("Member Name", "Member Surname")).thenReturn(memberFromDb);

        filmCrewMemberService.checkIfFilmCrewMembersExist(members);

        assertEquals(10L, member.getId());
    }

    @Test
    void checkIfFilmCrewMembersExist_AndFilmCrewMembersDoNotExist_SaveFilmCrewMembers() {
        FilmCrewMember member = new FilmCrewMember("Member Name", "Member Surname");
        List<FilmCrewMember> members = List.of(member);

        when(filmCrewMemberRepository.findByFirstNameAndLastName("Member Name", "Member Surname")).thenReturn(null);

        filmCrewMemberService.checkIfFilmCrewMembersExist(members);

        assertNull(member.getId());

        verify(filmCrewMemberRepository, times(1)).save(member);
    }
}