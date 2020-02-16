package br.com.lucasromagnoli.javaee.underpinning.domain.model;

import java.util.List;

/**
 * @author github.com/lucasromagnoli
 * @since 15/02/2020
 */
public interface SystemUser {
    Long getId();
    String getUsername();
    String getPassword();
    List<? extends SystemRole> getRoles();
}
