package fr.diginamic.springsecurity.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "article")
@Data
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserApp auteur;


    public Article (String title, String content) {
        this.title = title;
        this.content = content;
    }
}
