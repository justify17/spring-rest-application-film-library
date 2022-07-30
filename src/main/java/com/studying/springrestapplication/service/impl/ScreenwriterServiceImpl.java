package com.studying.springrestapplication.service.impl;

import com.studying.springrestapplication.model.entity.Screenwriter;
import com.studying.springrestapplication.model.repository.ScreenwriterRepository;
import com.studying.springrestapplication.service.ScreenwriterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ScreenwriterServiceImpl implements ScreenwriterService {
    private final ScreenwriterRepository screenwriterRepository;

    @Override
    public void checkIfScreenwritersExist(Set<Screenwriter> screenwriters) {
        for (Screenwriter screenwriter : screenwriters) {
            Screenwriter existingScreenwriter =
                    screenwriterRepository.findByFirstNameAndLastName(screenwriter.getFirstName(), screenwriter.getLastName());

            if (existingScreenwriter != null) {
                screenwriter.setId(existingScreenwriter.getId());
            } else {
                screenwriterRepository.save(screenwriter);
            }
        }
    }
}
