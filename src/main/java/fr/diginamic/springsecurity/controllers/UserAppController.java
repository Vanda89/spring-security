package fr.diginamic.springsecurity.controllers;

import fr.diginamic.springsecurity.services.CustomUserDetailsService;
import fr.diginamic.springsecurity.entities.UserApp;
import fr.diginamic.springsecurity.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-app")
public class UserAppController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserDetailsService userService;

    public UserAppController(UserRepository userRepository, PasswordEncoder passwordEncoder, CustomUserDetailsService userService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping
    public List<UserApp> getAllUser() throws Exception {
        return userService.getAllUsers();
    }

    @PostMapping("/add")
    public void createUser(@RequestParam String username, @RequestParam String password) throws Exception {
        userService.createUser(username, passwordEncoder.encode(password));
    }

    @PutMapping(path = "/update/{id}")
    public UserApp updateUser(@PathVariable Integer id, @RequestBody UserApp userApp) throws Exception {
        return userService.getUserById(id).map(existingUser -> {
            existingUser.setUsername(userApp.getUsername());
            existingUser.setPassword(userApp.getPassword());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec cet id " + id));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Integer id) throws Exception {
        userService.deleteUser(id);
    }
}
