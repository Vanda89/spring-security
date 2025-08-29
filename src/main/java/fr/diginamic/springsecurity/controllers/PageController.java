package fr.diginamic.springsecurity.controllers;

import fr.diginamic.springsecurity.entities.Article;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/register")
    public String addPage() {
        return "register";
    }


}
