package io.github.jorgelr0610.crud_ciclismo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){

        return "home"; // Poner el templates/home.html
    }
    
}
