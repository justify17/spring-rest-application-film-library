package com.studying.springrestapplication.service;

import com.studying.springrestapplication.model.entity.Screenwriter;

import java.util.Set;

public interface ScreenwriterService {
    void checkIfScreenwritersExist(Set<Screenwriter> screenwriters);
}
