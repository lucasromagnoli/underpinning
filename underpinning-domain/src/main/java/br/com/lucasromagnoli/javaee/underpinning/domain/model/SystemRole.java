package br.com.lucasromagnoli.javaee.underpinning.domain.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author github.com/lucasromagnoli
 * @since 15/02/2020
 */
public interface SystemRole extends GrantedAuthority {
    String getDescription();
}
