package fr.diginamic.springsecurity.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto {
    @NotBlank(message = "Titre obligatoire")
    @Size(min = 3, max = 20, message = "Le titre de l'article doit faire entre 3 et 20 caractères")
    private String title;
    @NotBlank(message = "Contenu obligatoire")
    @Size(min = 6, message = "Le descriptif du contenu de l'article doit avoir au moins 6 caractères")
    private String content;
}