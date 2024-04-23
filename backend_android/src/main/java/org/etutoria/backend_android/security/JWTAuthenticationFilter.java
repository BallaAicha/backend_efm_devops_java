package org.etutoria.backend_android.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.etutoria.backend_android.entities.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager)
    {
        super();
        this.authenticationManager = authenticationManager;
    }

    //implementer la methode attemptAuthentication


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
       //cette methode va extraire les informations de l'utilisateur qui sont envoyées dans le corps de la requete
        User user =null;
        try {
            //on utilise l'objet ObjectMapper  pour lire les objets json envoyés dans le corps de la requete et les convertir en objet de type User
            user = new ObjectMapper().readValue(request.getInputStream(), User.class);
        } catch (JsonParseException e) {e.printStackTrace();}
        catch (JsonMappingException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));


    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException
    {
        org.springframework.security.core.userdetails.User springUser =
                (org.springframework.security.core.userdetails.User)
                        authResult.getPrincipal();
        List<String> roles = new ArrayList<>();
        springUser.getAuthorities().forEach(au-> {
            roles.add(au.getAuthority());
        });
        String jwt = JWT.create().
                withSubject(springUser.getUsername()).
                withArrayClaim("roles", roles.toArray(new String[roles.size()])).
                withExpiresAt(new Date(System.currentTimeMillis()+10*24*60*60*1000)).
                sign(Algorithm.HMAC256("usmanembacke@gmail.com"));
        response.addHeader("Authorization", jwt);
    }

}
