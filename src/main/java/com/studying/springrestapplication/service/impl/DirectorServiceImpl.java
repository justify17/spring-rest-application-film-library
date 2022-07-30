package com.studying.springrestapplication.service.impl;

import com.studying.springrestapplication.model.entity.Director;
import com.studying.springrestapplication.model.repository.DirectorRepository;
import com.studying.springrestapplication.service.DirectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class DirectorServiceImpl implements DirectorService {
    private final DirectorRepository directorRepository;

    @Override
    public void checkIfDirectorsExist(Set<Director> directors) {
        for (Director director : directors) {
            Director existingDirector =
                    directorRepository.findByFirstNameAndLastName(director.getFirstName(), director.getLastName());

            if (existingDirector != null) {
                director.setId(existingDirector.getId());
            } else {
                directorRepository.save(director);
            }
        }
    }
}
