package com.studying.springrestapplication.service.impl;

import com.studying.springrestapplication.model.entity.Actor;
import com.studying.springrestapplication.model.repository.ActorRepository;
import org.springframework.stereotype.Service;

@Service
public class ActorServiceImpl extends FilmCrewMemberServiceImpl<Actor, ActorRepository> {

    public ActorServiceImpl(ActorRepository filmCrewMemberRepository) {
        super(filmCrewMemberRepository);
    }
}
