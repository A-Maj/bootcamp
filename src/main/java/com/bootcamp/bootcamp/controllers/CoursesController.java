package com.bootcamp.bootcamp.controllers;

import com.bootcamp.bootcamp.model.CourseEdition;
import com.bootcamp.bootcamp.model.User;
import com.bootcamp.bootcamp.service.CourseEditionService;
import com.bootcamp.bootcamp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class CoursesController {

    @Autowired
    CourseEditionService courseEditionService;

    @Autowired
    UserService userService;

    @GetMapping("/kursy")
    public String showCourseEditions(Model model) {

        model.addAttribute("allEditions", courseEditionService.getCoarseEditionSortedByStartDate());
        return "coursesList";
    }

    @GetMapping("/kursy/zapisz/{id}")
    public String getForm(@PathVariable long id, Model model) {

//        CourseEdition courseEdition = courseEditionService.findActive(id);

//        if (courseEdition == null) {
//            model.addAttribute("editionClosed", true);
//        }
//
//        else {
//            model.addAttribute("edition", courseEditionService.getCourseEdition(id).get());
//            model.addAttribute("user", User.builder().build());
//
//        }

        model.addAttribute("edition", courseEditionService.findActive(id));
        model.addAttribute("user", User.builder().build());
        return "sign";
    }


    @PostMapping("/kursy/zapisz/{id}")
    public String saveUser(@Valid @ModelAttribute User user, BindingResult result, @PathVariable long id, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("edition", courseEditionService.getCourseEdition(id).get());
            return "sign";
        }
        userService.save(user);
        model.addAttribute("edition", courseEditionService.getCourseEdition(id).get());
        model.addAttribute("user", User.builder().build());
        return "sign";
    }
}
