package fr.diginamic.springsecurity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        Model model) {
        System.out.println();
        if (error != null) {
            model.addAttribute("loginError", "Username or password is incorrect");
        }
        return "login";
    }
}
