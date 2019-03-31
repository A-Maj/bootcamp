package com.bootcamp.bootcamp.service;

import com.bootcamp.bootcamp.model.CourseEdition;
import com.bootcamp.bootcamp.repository.CourseEditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseEditionService {

    @Autowired
    CourseEditionRepository courseEditionRepository;

    public List<CourseEdition> showCourseEditions() {
        return courseEditionRepository.findAll();
    }

    public void save(CourseEdition courseEdition) {

        courseEdition.setActive(true);
        courseEditionRepository.save(courseEdition);
    }

    public Optional<CourseEdition> getCourseEdition(long id) {

        return courseEditionRepository.findById(id);
    }

    public List<CourseEdition> getCoarseEditionSortedByStartDate() {

        return courseEditionRepository.findAllByOrderByStartDate();
    }

//    public List<CourseEdition> courseEditions() {
//        return courseEditionRepository.getCourses();
//    }

    public CourseEdition findActive(long id) {
        return courseEditionRepository.findByIdAndActiveIsTrue(id);
    }
}
