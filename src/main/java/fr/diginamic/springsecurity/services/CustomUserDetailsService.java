package fr.diginamic.springsecurity.services;

import fr.diginamic.springsecurity.entities.UserApp;
import fr.diginamic.springsecurity.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public CustomUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserApp> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserApp> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    @Transactional
    public void createUser(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new RuntimeException("Nom d'utilisateur déjà utilisé");
        }

        UserApp user = new UserApp();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User non trouvé avec l'username : " + username));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }

    @Transactional
    public UserApp updateUser(UserApp userApp) {
        return userRepository.save(userApp);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

}
