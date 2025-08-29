package fr.diginamic.springsecurity.dtos;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    @NotBlank(message = "Username obligatoire")
    @Size(min = 3, max = 20, message = "Le username doit faire entre 3 et 20 caractères")
    private String username;

    @NotBlank(message = "Mot de passe obligatoire")
    @Size(min = 6, message = "Le mot de passe doit avoir au moins 6 caractères")
    private String password;
}