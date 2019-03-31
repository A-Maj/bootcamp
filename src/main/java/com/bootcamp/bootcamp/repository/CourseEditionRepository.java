package com.bootcamp.bootcamp.repository;

import com.bootcamp.bootcamp.model.CourseEdition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseEditionRepository extends JpaRepository<CourseEdition, Long> {

    List<CourseEdition> findAllByOrderByStartDate();

//    @Query("SELECT c FROM CourseEdition c where c.active=1")
//    List<CourseEdition> getCourses();

    CourseEdition findByIdAndActiveIsTrue(long id);

    @Query("SELECT c FROM CourseEdition c where c.active=1 and c.id=:id")
    CourseEdition showEditions(@Param("id") long id);
}

