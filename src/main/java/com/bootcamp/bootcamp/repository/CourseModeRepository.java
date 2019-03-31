package com.bootcamp.bootcamp.repository;

import com.bootcamp.bootcamp.model.CourseMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseModeRepository extends JpaRepository<CourseMode, Long> {
}
