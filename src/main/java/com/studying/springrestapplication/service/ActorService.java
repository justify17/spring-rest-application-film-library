package com.studying.springrestapplication.service;

import com.studying.springrestapplication.model.entity.Actor;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ActorService {
    void checkIfActorsExist(Collection<Actor> actors);
}
