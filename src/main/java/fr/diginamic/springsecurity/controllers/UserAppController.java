package fr.diginamic.springsecurity.controllers;

import fr.diginamic.springsecurity.services.CustomUserDetailsService;
import fr.diginamic.springsecurity.entities.UserApp;
import fr.diginamic.springsecurity.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-app")
public class UserAppController {


    private final CustomUserDetailsService userService;

    public UserAppController(CustomUserDetailsService userService) {

        this.userService = userService;
    }

    @GetMapping
    public List<UserApp> getAllUser() throws Exception {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    @ResponseBody
    public String createUser(@ModelAttribute UserApp user) throws Exception {
        userService.createUser(
                user.getUsername(),
                user.getPassword()
        );
        return "Utilisateur créé";
    }

    @PutMapping(path = "/update/{id}")
    public UserApp updateUser(@PathVariable("id") Integer userId, @RequestBody UserApp userApp) throws Exception {
        return userService.getUserById(userId).map(existingUser -> {
            existingUser.setUsername(userApp.getUsername());
            existingUser.setPassword(userApp.getPassword());
            return userService.updateUser(existingUser);
        }).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec cet id " + userId));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable("id") Integer userId) throws Exception {
        userService.deleteUser(userId);
    }
}
