package com.studying.springrestapplication.service.impl;

import com.studying.springrestapplication.model.entity.Producer;
import com.studying.springrestapplication.model.repository.ProducerRepository;
import com.studying.springrestapplication.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProducerServiceImpl implements ProducerService {
    private final ProducerRepository producerRepository;

    @Override
    public void checkIfProducersExist(Set<Producer> producers) {
        for (Producer producer : producers) {
            Producer existingProducer =
                    producerRepository.findByFirstNameAndLastName(producer.getFirstName(), producer.getLastName());

            if (existingProducer != null) {
                producer.setId(existingProducer.getId());
            } else {
                producerRepository.save(producer);
            }
        }
    }
}
