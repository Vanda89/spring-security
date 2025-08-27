package fr.diginamic.springsecurity.services;

import fr.diginamic.springsecurity.entities.UserApp;
import fr.diginamic.springsecurity.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserApp> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<UserApp> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public void createUser(String username, String password) {
        userRepository.save(new UserApp(username, password));
    }

    public UserApp updateUser(UserApp userApp) {
        return userRepository.save(userApp);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User non trouvé avec l'username : " + username));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }

}
