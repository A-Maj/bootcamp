package com.bootcamp.bootcamp.controllers;

import com.bootcamp.bootcamp.model.Contact;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    @GetMapping("/kontakt")
    public String getContact(Model model) {

        model.addAttribute("contact", new Contact());
        return "contact";
    }

    //@ModelAttribute odnosi się do całego modelu.
    @PostMapping("/wyslij")
    public String sent(@ModelAttribute Contact contact, Model model) {
        model.addAttribute("isSent", true);
        return "contact";
    }
}
