package org.etutoria.backend_android.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityBeansConfiguration {
    //c'est une bonne pratique de placer tous les beans de configuration de sécurité dans une classe de configuration distincte
    //un bean permet de définir un objet qui sera instancié par Spring et qui sera injecté dans d'autres objets , il est un singleton par défaut
    //c'est une interface qui définit une méthode d'authentification qui est utilisée pour authentifier l'utilisateur
    @Bean
    public AuthenticationManager
    authenticationManager(AuthenticationConfiguration config) throws Exception
    {
        return config.getAuthenticationManager();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
