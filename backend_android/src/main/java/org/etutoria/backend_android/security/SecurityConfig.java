package org.etutoria.backend_android.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    AuthenticationManager authMgr;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws
            Exception {
        http.sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/login","/register/**","/verifyEmail/**").permitAll()

                        .requestMatchers("/allUsers").hasAuthority("ADMIN")
                        .anyRequest().authenticated())
                //addFilterBefore permet d'ajouter un filtre avant un autre filtre cad avant UsernamePasswordAuthenticationFilter pour traiter les requetes d'authentification
                .addFilterBefore(new JWTAuthenticationFilter(authMgr),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new
                JWTAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}




