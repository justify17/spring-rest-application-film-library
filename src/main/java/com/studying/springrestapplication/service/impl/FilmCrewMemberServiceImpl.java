package com.studying.springrestapplication.service.impl;

import com.studying.springrestapplication.model.entity.FilmCrewMember;
import com.studying.springrestapplication.model.repository.FilmCrewMemberRepository;
import com.studying.springrestapplication.service.FilmCrewMemberService;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
public abstract class FilmCrewMemberServiceImpl<T extends FilmCrewMember, R extends FilmCrewMemberRepository<T>>
        implements FilmCrewMemberService<T> {

    private final R filmCrewMemberRepository;

    @Override
    public void checkIfFilmCrewMembersExist(Collection<T> filmCrewMembers) {
        for (T filmCrewMember : filmCrewMembers) {
            String firstName = filmCrewMember.getFirstName();
            String lastName = filmCrewMember.getLastName();

            FilmCrewMember existingFilmCrewMember = filmCrewMemberRepository.findByFirstNameAndLastName(firstName, lastName);

            if (existingFilmCrewMember != null) {
                filmCrewMember.setId(existingFilmCrewMember.getId());
            } else {
                filmCrewMemberRepository.save(filmCrewMember);
            }
        }
    }
}
