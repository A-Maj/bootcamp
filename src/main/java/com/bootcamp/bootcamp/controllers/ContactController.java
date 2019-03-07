package com.bootcamp.bootcamp.controllers;

import com.bootcamp.bootcamp.model.Contact;
import com.bootcamp.bootcamp.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping("/kontakt")
    public String getContact(Model model) {

        model.addAttribute("contact", new Contact());
        return "contact";
    }

    //@ModelAttribute odnosi się do całego modelu.
    @PostMapping("/wyslij")
    public String sent(@ModelAttribute Contact contact, Model model) {
        model.addAttribute("isSent", true);
        model.addAttribute("contact", new Contact());
        contactService.saveContact(contact);
        return "contact";
    }
}
