package fr.diginamic.springsecurity.repositories;

import fr.diginamic.springsecurity.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Integer> {
    public Optional<UserApp> findByUsername(String username);

}
