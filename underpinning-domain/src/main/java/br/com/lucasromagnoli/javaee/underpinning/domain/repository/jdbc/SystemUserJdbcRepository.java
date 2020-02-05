package br.com.lucasromagnoli.javaee.underpinning.domain.repository.jdbc;

import br.com.lucasromagnoli.javaee.underpinning.domain.model.SystemUser;

import java.util.Optional;

/**
 * @author github.com/lucasromagnoli
 * @since 04/02/2020
 */
public interface SystemUserJdbcRepository {

    Optional<SystemUser> findSystemUserByUsernameAndPassword(String username, String password);
}
