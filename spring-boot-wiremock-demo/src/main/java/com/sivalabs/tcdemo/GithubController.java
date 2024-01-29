package com.sivalabs.tcdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class GithubController {

    private final GithubService githubService;

    @GetMapping("/users/{username}")
    public GitHubUser getGithubUserProfile(@PathVariable String username) {
        // Validación del formato del nombre de usuario
        if (!isValidUsername(username)) {
            throw new IllegalArgumentException("Nombre de usuario no válido");
        }
        return githubService.getGithubUserProfile(username);
    }

    private boolean isValidUsername(String username) {
        // Implementa tu lógica de validación aquí, por ejemplo, verificar el formato del nombre de usuario
        // Devuelve true si el nombre de usuario es válido, false en caso contrario
        // Aquí es donde podrías aplicar las medidas de seguridad adicionales mencionadas anteriormente
        return true;
    }
}
