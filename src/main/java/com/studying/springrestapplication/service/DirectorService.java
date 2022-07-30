package com.studying.springrestapplication.service;

import com.studying.springrestapplication.model.entity.Director;

import java.util.Set;

public interface DirectorService {
    void checkIfDirectorsExist(Set<Director> directors);
}
