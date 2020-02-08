package br.com.lucasromagnoli.javaee.underpinning.rest.security.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @author github.com/lucasromagnoli
 * @since 05/02/2020
 */
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    public JwtAuthenticationToken(JwtAuthenticatedUser jwtAuthenticatedUser) {
        super(jwtAuthenticatedUser, jwtAuthenticatedUser.getPassword(), jwtAuthenticatedUser.getAuthorities());
    }

}
