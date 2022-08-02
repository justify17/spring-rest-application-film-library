package com.studying.springrestapplication.service.impl;

import com.studying.springrestapplication.model.entity.Producer;
import com.studying.springrestapplication.model.repository.ProducerRepository;
import com.studying.springrestapplication.service.ProducerService;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl extends FilmCrewMemberServiceImpl<Producer, ProducerRepository>
        implements ProducerService {

    public ProducerServiceImpl(ProducerRepository filmCrewMemberRepository) {
        super(filmCrewMemberRepository);
    }
}
