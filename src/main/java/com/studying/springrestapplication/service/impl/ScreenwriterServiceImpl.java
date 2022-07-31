package com.studying.springrestapplication.service.impl;

import com.studying.springrestapplication.model.entity.Screenwriter;
import com.studying.springrestapplication.model.repository.ScreenwriterRepository;
import org.springframework.stereotype.Service;

@Service
public class ScreenwriterServiceImpl extends FilmCrewMemberServiceImpl<Screenwriter, ScreenwriterRepository> {

    public ScreenwriterServiceImpl(ScreenwriterRepository filmCrewMemberRepository) {
        super(filmCrewMemberRepository);
    }
}
