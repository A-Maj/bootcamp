package com.bootcamp.bootcamp.controllers;

import com.bootcamp.bootcamp.model.Trainer;
import com.bootcamp.bootcamp.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/trenerzy")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;

    @GetMapping("")
    public String getTrainers(Model model) {

        model.addAttribute("trainerList", trainerService.getAllTrainers());


        return "trainer";
    }

    // localhost:8080/trenerzy/{id}
    // np. localhost:8080/trenerzy/1, localhost:8080/trenerzy/2, localhost:8080/trenerzy/3 itd

    @GetMapping("/{id}")
    public String showTrainer(@PathVariable(name = "id") long id, Model model) {

        Optional<Trainer> trainer = trainerService.getTrainer(id);


        if (trainer.isPresent()) {
            model.addAttribute("trainer", trainer.get());
            return "trainer-details";
        } else {
            return "redirect:";
        }


    }
}