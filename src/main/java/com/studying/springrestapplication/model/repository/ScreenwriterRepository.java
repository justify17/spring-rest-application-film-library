package com.studying.springrestapplication.model.repository;

import com.studying.springrestapplication.model.entity.Screenwriter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenwriterRepository extends JpaRepository<Screenwriter, Long> {
}
