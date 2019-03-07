package com.bootcamp.bootcamp.repository;

import com.bootcamp.bootcamp.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

    List<Coach> findByName(String name);
}
