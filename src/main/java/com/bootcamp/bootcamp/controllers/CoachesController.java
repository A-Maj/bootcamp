package com.bootcamp.bootcamp.controllers;

import com.bootcamp.bootcamp.model.Coach;
import com.bootcamp.bootcamp.service.CoachesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CoachesController {

    @Autowired
    private CoachesService coachesService;

    @GetMapping("/nasi-trenerzy")
    public String getCoaches(Model model) {

        model.addAttribute("coachesList", coachesService.getCoaches());
        System.out.println(coachesService.getCoaches());
        return "coaches";
    }


}
