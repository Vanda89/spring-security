package fr.diginamic.springsecurity.controllers;

import fr.diginamic.springsecurity.entities.Article;
import fr.diginamic.springsecurity.entities.UserApp;
import fr.diginamic.springsecurity.repositories.ArticleRepository;
import fr.diginamic.springsecurity.repositories.UserRepository;
import fr.diginamic.springsecurity.services.ArticleService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/article")
public class ArticleController {
    private final ArticleRepository articleRepository;
    private final ArticleService articleService;
    private final UserRepository userRepository;

    public ArticleController(ArticleRepository articleRepository, ArticleService articleService, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.articleService = articleService;
        this.userRepository = userRepository;
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("article", new Article());
        return "add-article";
    }

    @PostMapping("/new")
    public String addArticle(@ModelAttribute Article article, Model model, Authentication auth) throws Exception {
        try {
            String username = ((UserDetails) auth.getPrincipal()).getUsername();
            UserApp user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

            if (articleRepository.findByTitle(article.getTitle()).isPresent()) {
                model.addAttribute("error", "Un article avec ce titre existe déjà");
                return "add-article";
            }

            article.setAuteur(user);
            articleService.create(article);
            return "redirect:/article/list";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "add-article";
        }
    }

    @GetMapping("/list")
    public String listArticles(Model model) {
        model.addAttribute("articles", articleService.getAll());
        return "list";
    }

    @PutMapping("/update/{id}")
    public Article updateArticle(
            @PathVariable("id") Integer articleId,
            @RequestParam String title,
            @RequestParam String content) {

        return articleService.getById(articleId)
                .map(existingArticle -> {
                    existingArticle.setTitle(title);
                    existingArticle.setContent(content);
                    return articleService.updateArticle(articleId, existingArticle);
                })
                .orElseThrow(() -> new RuntimeException("Article non trouvé"));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteArticle(@PathVariable("id") Integer articleId) {
        articleService.deleteArticle(articleId);
    }
}
