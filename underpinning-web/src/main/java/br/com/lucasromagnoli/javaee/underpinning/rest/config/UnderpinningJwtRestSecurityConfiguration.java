package br.com.lucasromagnoli.javaee.underpinning.rest.config;

import br.com.lucasromagnoli.javaee.underpinning.commons.exception.UnderpinningException;
import br.com.lucasromagnoli.javaee.underpinning.rest.service.UnderpinningJwtSecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author github.com/lucasromagnoli
 * @since 03/02/2020
 */
public abstract class UnderpinningJwtRestSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public UnderpinningJwtSecurityService underpinningJwtSecurityService() throws UnderpinningException {
        return new UnderpinningJwtSecurityService(publicKey(), privateKey());
    }

    public abstract PrivateKey privateKey() throws UnderpinningException;

    public abstract PublicKey publicKey() throws UnderpinningException;
}
