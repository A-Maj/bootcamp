package com.bootcamp.bootcamp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    //@RequestMapping(value = "/o-nas", method = RequestMethod.GET)
    @GetMapping("/o-nas")
    public String getAbout() {
        return "about";
    }
}
