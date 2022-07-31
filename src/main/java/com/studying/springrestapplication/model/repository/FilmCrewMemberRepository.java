package com.studying.springrestapplication.model.repository;

import com.studying.springrestapplication.model.entity.FilmCrewMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface FilmCrewMemberRepository<T extends FilmCrewMember> extends JpaRepository<T, Long> {
    T findByFirstNameAndLastName(String firstName, String lastName);
}
