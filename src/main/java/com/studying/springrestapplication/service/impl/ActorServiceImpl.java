package com.studying.springrestapplication.service.impl;

import com.studying.springrestapplication.model.entity.Actor;
import com.studying.springrestapplication.model.repository.ActorRepository;
import com.studying.springrestapplication.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {
    private final ActorRepository actorRepository;

    @Override
    public void checkIfActorsExist(Collection<Actor> actors) {
        for (Actor actor : actors) {
            Actor existingActor = actorRepository.findByFirstNameAndLastName(actor.getFirstName(), actor.getLastName());

            if (existingActor != null) {
                actor.setId(existingActor.getId());
            } else {
                actorRepository.save(actor);
            }
        }
    }
}
