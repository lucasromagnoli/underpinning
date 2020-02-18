package br.com.lucasromagnoli.javaee.underpinning.rest.security.jwt;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

/**
 * @author github.com/lucasromagnoli
 * @since 05/02/2020
 */
public class JwtAuthenticatedUser extends User {

    private final Long id;

    public JwtAuthenticatedUser(Long id, String username, List<SimpleGrantedAuthority> roles) {
        super(username, "", roles);
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }
}
