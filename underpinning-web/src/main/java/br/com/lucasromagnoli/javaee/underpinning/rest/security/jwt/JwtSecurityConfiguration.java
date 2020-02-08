package br.com.lucasromagnoli.javaee.underpinning.rest.security.jwt;

import br.com.lucasromagnoli.javaee.underpinning.commons.exception.UnderpinningException;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author github.com/lucasromagnoli
 * @since 03/02/2020
 */
public abstract class JwtSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtAuthenticationService underpinningJwtSecurityService() throws UnderpinningException {
        return new JwtAuthenticationService(publicKey(), privateKey(), passwordEncoder());
    }

    @Bean
    public JwtGrantAuthorizationFilter jwtGrantAuthorizationFilter() {
        return new JwtGrantAuthorizationFilter();
    }

    @Bean
    public JwtAuthenticationExceptionHandler jwtAuthenticationExceptionHandler() {
        return new JwtAuthenticationExceptionHandler();
    }

    public abstract PrivateKey privateKey() throws UnderpinningException;

    public abstract PublicKey publicKey() throws UnderpinningException;

    public abstract PasswordEncoder passwordEncoder();
}
