package org.etutoria.backend_android.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt =request.getHeader("Authorization");//on recupere le token jwt envoyé dans l'entete de la requete
        //on verifie si le token est null ou s'il ne commence pas par "Bearer "
        if (jwt==null || !jwt.startsWith("Bearer "))
        {
            filterChain.doFilter(request, response);//on passe au filtre suivant
            return;//on sort de la methode car le token est null ou ne commence pas par "Bearer "
        }
        //on cree un objet verifier pour verifier le token jwt envoyé en utilisant l'algorithme HMAC256 et la clé secrete
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecParams.SECRET)).build();
        //enlever le préfixe Bearer du jwt pour avoir le jwt sans le préfixe
        jwt= jwt.substring(7); // 7 caractères dans "Bearer "
        DecodedJWT decodedJWT = verifier.verify(jwt);//on verifie le token jwt et on recupere les informations de l'utilisateur
        String username = decodedJWT.getSubject();//on recupere le nom d'utilisateur
        List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);//on recupere les roles
        //on cree une collection de GrantedAuthority pour stocker les roles de l'utilisateur verifié
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String r : roles) authorities.add(new SimpleGrantedAuthority(r));
        //on cree un objet UsernamePasswordAuthenticationToken qui contient le nom d'utilisateur et les roles , le null est pour le mot de passe
        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(username,null,authorities);
        SecurityContextHolder.getContext().setAuthentication(user);//on stocke l'utilisateur authentifié dans le contexte de sécurité
        filterChain.doFilter(request, response);//on passe au filtre suivant



    }
}
