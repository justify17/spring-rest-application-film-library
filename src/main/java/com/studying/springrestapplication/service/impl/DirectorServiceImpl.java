package com.studying.springrestapplication.service.impl;

import com.studying.springrestapplication.model.entity.Director;
import com.studying.springrestapplication.model.repository.DirectorRepository;
import com.studying.springrestapplication.service.DirectorService;
import org.springframework.stereotype.Service;

@Service
public class DirectorServiceImpl extends FilmCrewMemberServiceImpl<Director, DirectorRepository>
        implements DirectorService {

    public DirectorServiceImpl(DirectorRepository filmCrewMemberRepository) {
        super(filmCrewMemberRepository);
    }
}
