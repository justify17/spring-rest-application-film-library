package com.studying.springrestapplication.service;

import com.studying.springrestapplication.model.entity.FilmCrewMember;

import java.util.Collection;

public interface FilmCrewMemberService<T extends FilmCrewMember> {
    void checkIfFilmCrewMembersExist(Collection<T> filmCrewMembers);
}
