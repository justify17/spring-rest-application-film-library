package com.studying.springrestapplication.model.repository;

import com.studying.springrestapplication.model.entity.Director;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends FilmCrewMemberRepository<Director> {
}
