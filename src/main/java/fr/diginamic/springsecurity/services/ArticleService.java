package fr.diginamic.springsecurity.services;

import fr.diginamic.springsecurity.entities.Article;
import fr.diginamic.springsecurity.repositories.ArticleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAll() {
        return articleRepository.findAll();
    }

    @Transactional
    public Article create(Article article) {
        if (articleRepository.findByTitle(article.getTitle()).isPresent()) {
            throw new RuntimeException("Title already exists");
        }
        return articleRepository.save(article);
    }

    public Optional<Article> getByTitle(String title) {
        return articleRepository.findByTitle(title);
    }

    public Optional<Article> getById(Integer id) {
        return articleRepository.findById(id);
    }

    @Transactional
    public Article updateArticle(Integer id, Article article) {
        return articleRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(article.getTitle());
                    existing.setContent(article.getContent());
                    return articleRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Article not found"));
    }

    @Transactional
    public void deleteArticle(Integer id) {
        articleRepository.deleteById(id);
    }
}
