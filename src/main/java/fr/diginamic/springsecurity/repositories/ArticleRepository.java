package fr.diginamic.springsecurity.repositories;

import fr.diginamic.springsecurity.entities.Article;
import fr.diginamic.springsecurity.entities.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Integer> {
    public Optional<Article> findByTitle(String title);


}
