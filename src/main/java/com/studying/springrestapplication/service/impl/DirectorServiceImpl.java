package com.studying.springrestapplication.service.impl;

import com.studying.springrestapplication.model.entity.Director;
import com.studying.springrestapplication.model.repository.DirectorRepository;
import org.springframework.stereotype.Service;

@Service
public class DirectorServiceImpl extends FilmCrewMemberServiceImpl<Director, DirectorRepository> {

    public DirectorServiceImpl(DirectorRepository filmCrewMemberRepository) {
        super(filmCrewMemberRepository);
    }
}
