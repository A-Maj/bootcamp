package com.bootcamp.bootcamp.controllers;

import com.bootcamp.bootcamp.model.Course;
import com.bootcamp.bootcamp.model.CourseEdition;
import com.bootcamp.bootcamp.model.Trainer;
import com.bootcamp.bootcamp.model.User;
import com.bootcamp.bootcamp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseEditionService courseEditionService;

    @Autowired
    private CourseModeService courseModeService;

    @Autowired
    private RoleService roleService;

    @Autowired UserService userService;

    @GetMapping("")
    public String getTrainers(Model model) {

        model.addAttribute("trainersListSortedByLastName", trainerService.getTrainersSortedByLastName());

        return "admin/trainer-list";
    }

    @GetMapping("/dodaj-trenera")
    public String addTrainer(Model model) {
            Trainer trainer = Trainer.builder().build();
            model.addAttribute("trainer", trainer);


        return "admin/add-trainer";
    }

    @PostMapping("/zapisz-trenera")
    public String saveTrainer(@Valid @ModelAttribute Trainer trainer, BindingResult result, Model model) {

        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            return "admin/add-trainer";
        } else {
            trainerService.save(trainer);
            model.addAttribute("trainerIsAdded", true);
            model.addAttribute("trainersListSortedByLastName", trainerService.getTrainersSortedByLastName());
            return "admin/trainer-list";
        }

    }

    @GetMapping("/usun-trenera/{id}")
    public String deleteTrainer(@PathVariable long id, Model model) {
        trainerService.delete(id);
        model.addAttribute("trainersListSortedByLastName", trainerService.getTrainersSortedByLastName());
        model.addAttribute("trainerIsDeleted", true);
        return "admin/trainer-list";
    }

    @GetMapping("edytuj-trenera/{id}")
    public String editTrainer(@PathVariable long id, Model model) {
        Optional<Trainer> trainer = trainerService.getTrainer(id);
        model.addAttribute("trainer", trainer.get());
        return "admin/add-trainer";
    }

    @GetMapping("/kursy")
    public String showCourses(Model model) {

        model.addAttribute("allCourses", courseService.showCourses());
        return "admin/courses";
    }

    @GetMapping("/dodaj-kurs")
    public String addCourse(Model model) {
        Course course = Course.builder().build();
        model.addAttribute("course", course);


        return "admin/add-course";
    }

    @PostMapping("/zapisz-kurs")
    public String saveCourse(@Valid @ModelAttribute Course course, BindingResult result, Model model) {

        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            return "admin/add-course";
        } else {
            courseService.save(course);
            model.addAttribute("courseIsAdded", true);
            model.addAttribute("allCourses", courseService.showCourses());
            return "admin/courses";
        }
    }

    @GetMapping("edytuj-kurs/{id}")
    public String editCourse(@PathVariable long id, Model model) {
        Optional<Course> course = courseService.getCourse(id);
        model.addAttribute("course", course.get());
        return "admin/add-course";
    }

    @GetMapping("/edycje")
    public String showCourseEditions(Model model) {

        model.addAttribute("allEditions", courseEditionService.showCourseEditions());
        return "admin/editions";
    }

    @GetMapping("/dodaj-edycje")
    public String addEdition(Model model) {
        CourseEdition courseEdition = CourseEdition.builder().build();
        model.addAttribute("courseEdition", courseEdition);
        model.addAttribute("allCourses", courseService.showCourses());
        model.addAttribute("modeList", courseModeService.showModes());
        model.addAttribute("trainerList", trainerService.getAllTrainers());


        return "admin/add-edition";
    }

    @PostMapping("/zapisz-edycje")
    public String saveEdition(@Valid @ModelAttribute CourseEdition courseEdition, BindingResult result, Model model) {

        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            errors.forEach(err -> System.out.println(err.getDefaultMessage()));
            return "admin/add-edition";
        }
        else {
            courseEditionService.save(courseEdition);
            model.addAttribute("courseEditionIsAdded", true);
            model.addAttribute("allEditions", courseEditionService.showCourseEditions());

            return "admin/editions";
        }
    }

    @GetMapping("edytuj-edycje/{id}")
    public String editEdition(@PathVariable long id, Model model) {
        Optional<CourseEdition> courseEdition = courseEditionService.getCourseEdition(id);
        model.addAttribute("courseEdition", courseEdition.get());
        model.addAttribute("allCourses", courseService.showCourses());
        model.addAttribute("modeList", courseModeService.showModes());
        return "admin/add-edition";
    }

    @GetMapping("/dodaj-konto")
    public String addUser(Model model) {
        User user = User.builder().build();
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.getAllRoles());


        return "admin/add-user";
    }

    @PostMapping("/dodaj-konto/zapisz")
    public String saveUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "admin/add-user";
        }
        userService.saveUser(user);
        model.addAttribute("user", User.builder().build());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "admin/add-user";
    }


}
