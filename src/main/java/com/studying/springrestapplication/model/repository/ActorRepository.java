package com.studying.springrestapplication.model.repository;

import com.studying.springrestapplication.model.entity.Actor;
import org.springframework.stereotype.Repository;

@Repository
public interface ActorRepository extends FilmCrewMemberRepository<Actor> {
}
