package fr.diginamic.springsecurity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserApp, Integer> {
    public Optional<UserApp> findByUsername(String username);

}
