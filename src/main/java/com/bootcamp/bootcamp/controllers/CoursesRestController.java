package com.bootcamp.bootcamp.controllers;

import com.bootcamp.bootcamp.model.CourseEdition;
import com.bootcamp.bootcamp.model.User;
import com.bootcamp.bootcamp.service.CourseEditionService;
import com.bootcamp.bootcamp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/client")
public class CoursesRestController {

    @Autowired
    CourseEditionService courseEditionService;

    @Autowired
    UserService userService;

    @GetMapping("/kursy")
    public ResponseEntity<List<CourseEdition>> showCourseEditions() {

        log.info("pobranie listy wszystkich kursów");
        List<CourseEdition> courseEditions = courseEditionService.getCoarseEditionSortedByStartDate();
        return ResponseEntity.ok(courseEditions);
    }

    @GetMapping("/kursy/zapisz/{id}")
    public ResponseEntity getForm(@PathVariable long id) {

        CourseEdition courseEdition = courseEditionService.findActive(id);
        if (courseEdition != null) {
            return ResponseEntity.ok(courseEdition);
        }
        return new ResponseEntity("Brak kursu o podanym id", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/kursy/zapisz/{id}")
    public ResponseEntity saveUser(@RequestBody User user, @PathVariable long id) {

        try {
            userService.save(user);
        } catch (Exception e) {
            log.error("Error", e);
            return new ResponseEntity("Błąd zapisu użytkownika", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        CourseEdition courseEdition = courseEditionService.findActive(id);
        if (courseEdition != null) {
            return ResponseEntity.ok(courseEdition);
        }
        return new ResponseEntity("Brak kursu o podanym id", HttpStatus.NOT_FOUND);
    }

}
