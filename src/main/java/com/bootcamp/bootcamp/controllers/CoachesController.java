package com.bootcamp.bootcamp.controllers;

import com.bootcamp.bootcamp.model.Coach;
import com.bootcamp.bootcamp.service.CoachesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
@RequestMapping("/trenerzy")
public class CoachesController {

    @Autowired
    private CoachesService coachesService;

    @GetMapping("")
    public String getCoaches(Model model) {

        model.addAttribute("coachesList", coachesService.getCoaches());

        System.out.println(coachesService.getCoaches());
        return "coaches";
    }

    //localhost:8080/trenerzy/{id}
    //np. localhost:8080/trenerzy/1
    @GetMapping("/{id}")
    public String showCoach(@PathVariable(name = "id") long id, Model model) {

        Optional<Coach> coach = coachesService.showCoach(id);
        if(coach.isPresent()) {
            model.addAttribute("coach", coach.get());
            return "coach";
        } else {
            return "redirect:";
        }

    }


}
