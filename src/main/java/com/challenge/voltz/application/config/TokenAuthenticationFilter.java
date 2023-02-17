package com.challenge.voltz.application.config;

import com.challenge.voltz.domain.entities.User;
import com.challenge.voltz.domain.repositories.AuthorizedUserRepository;
import com.challenge.voltz.resources.utils.JWTBuilderTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.token.TokenService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    JWTBuilderTools tokenService;
    @Autowired
    AuthorizedUserRepository authorizedUserRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getTokenFromHeader(request);
        boolean tokenValid = tokenService.isTokenValid(token);
        if (tokenValid) {
            authenticate(token);
        }
        filterChain.doFilter(request, response);
    }


    private void authenticate(String tokenFromHeader) {
        Integer id = tokenService.getTokenId(tokenFromHeader);

        Optional<User> optionalUser = authorizedUserRepository.findById(id);

        if (optionalUser.isPresent()) {

            User user = optionalUser.get();

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        }
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7);
    }

}