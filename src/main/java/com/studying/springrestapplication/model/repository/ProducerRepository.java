package com.studying.springrestapplication.model.repository;

import com.studying.springrestapplication.model.entity.Producer;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends FilmCrewMemberRepository<Producer> {
}
