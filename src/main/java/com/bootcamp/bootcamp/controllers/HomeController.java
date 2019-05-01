package com.bootcamp.bootcamp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
//@RequestMapping(value = "/bootcamp", method = RequestMethod.GET) Użytaczne w przypadku gdy strony posiadają część wspólną w adresie.
public class HomeController {

    //@RequestMapping(value = "/", method = RequestMethod.GET)

//    @GetMapping("/")
//    public String getHome(@RequestParam(name = "imie", defaultValue = "Jan") String firstName,
//                          @RequestParam(name = "nazwisko", defaultValue = "Ktoś") String lastName,
//                          @RequestParam(defaultValue = "25") int wiek,
//                          Model model) { return "..." }

    @GetMapping("/")
    public String getHome() {
//        System.out.println("Witaj " + firstName + " " + lastName + " " + wiek,);
//        if (wiek > 20) {
//            return "redirect:o-nas";
//            return "forward:o-nas"; nie aktualizuje adresu do ktorego uzytkownik jest przekierowywany. Wyswietla "home"
//        }
//        model.addAttribute("nazwaZmiennejWwidoku", firstName);
//        model.addAttribute("lastName", lastName);
        return "home";
    }

    @GetMapping("/logowanie")
    public String loginForm() {
        return "loginform";
    }
}
