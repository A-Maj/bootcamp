package com.bootcamp.bootcamp.service;

import com.bootcamp.bootcamp.model.Course;
import com.bootcamp.bootcamp.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public List<Course> showCourses() {
        return courseRepository.findAll();
    }
    public void save(Course course) {
        courseRepository.save(course);
    }

    public Optional<Course> getCourse(long id) {
        //return trainerRepository.getOne(id);
        return courseRepository.findById(id);
    }
}
