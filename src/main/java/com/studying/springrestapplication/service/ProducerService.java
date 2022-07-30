package com.studying.springrestapplication.service;

import com.studying.springrestapplication.model.entity.Producer;

import java.util.Set;

public interface ProducerService {
    void checkIfProducersExist(Set<Producer> producers);
}
