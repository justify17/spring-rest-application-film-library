package com.studying.springrestapplication.service.impl;

import com.studying.springrestapplication.model.entity.Screenwriter;
import com.studying.springrestapplication.model.repository.ScreenwriterRepository;
import com.studying.springrestapplication.service.ScreenwriterService;
import org.springframework.stereotype.Service;

@Service
public class ScreenwriterServiceImpl extends FilmCrewMemberServiceImpl<Screenwriter, ScreenwriterRepository>
        implements ScreenwriterService {

    public ScreenwriterServiceImpl(ScreenwriterRepository filmCrewMemberRepository) {
        super(filmCrewMemberRepository);
    }
}
