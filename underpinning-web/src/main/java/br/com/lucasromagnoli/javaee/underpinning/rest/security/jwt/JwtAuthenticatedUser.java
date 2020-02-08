package br.com.lucasromagnoli.javaee.underpinning.rest.security.jwt;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * @author github.com/lucasromagnoli
 * @since 05/02/2020
 */
public class JwtAuthenticatedUser extends User {

    public JwtAuthenticatedUser(String username, List<SimpleGrantedAuthority> roles ) {
        super(username, "", roles);
    }

}
