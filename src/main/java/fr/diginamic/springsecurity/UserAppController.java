package fr.diginamic.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-app")
public class UserAppController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAppController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<UserApp> getAllUser() throws Exception {
        return userRepository.findAll();
    }

    @PostMapping("/add")
    public UserApp addUser(@RequestBody UserApp userApp) throws Exception {
        userApp.setPassword(passwordEncoder.encode(userApp.getPassword()));
        return userRepository.save(userApp);
    }

    @PutMapping(path = "/update/{id}")
    public UserApp updateUser(@PathVariable Integer id, @RequestBody UserApp userApp) throws Exception {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setUsername(userApp.getUsername());
            existingUser.setPassword(userApp.getPassword());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec cet id " + id));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Integer id) throws Exception {
        userRepository.deleteById(id);
    }
}
