package br.com.lucasromagnoli.javaee.underpinning.domain.repository.jdbc.impl;

import br.com.lucasromagnoli.javaee.underpinning.domain.model.SystemUser;
import br.com.lucasromagnoli.javaee.underpinning.domain.repository.jdbc.SystemUserJdbcRepository;
import br.com.lucasromagnoli.javaee.underpinning.domain.repository.jdbc.joinable.extractor.SystemUserResultSetExtractor;
import br.com.lucasromagnoli.javaee.underpinning.domain.repository.jdbc.joinable.mapper.SystemUserSimpleRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author github.com/lucasromagnoli
 * @since 04/02/2020
 */
@Repository
public class SystemUserJdbcRepositoryImpl implements SystemUserJdbcRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // TODO: Criar classe support para criar as query
    // TODO: Pensar em uma maneira inteligente de reutilizar e organizar as query
    private String sqlFindUserByUsernameAndPassword =
            "SELECT DISTINCT USER.ID_SYSTEM_USER," +
                    "       USER.USERNAME," +
                    "       ROLES.NAME " +
                    "FROM UND_SYSTEM_USER USER " +
                    "INNER JOIN UND_SYSTEM_USER_ROLES USER_PIVOT_ROLES" +
                    "    ON USER_PIVOT_ROLES.ID_SYSTEM_USER = USER.ID_SYSTEM_USER " +
                    "INNER JOIN UND_SYSTEM_ROLES ROLES" +
                    "    ON ROLES.ID_SYSTEM_ROLES = ROLES.ID_SYSTEM_ROLES " +
                    "WHERE USER.USERNAME = ? AND USER.PASSWORD = ?";

    @Override
    public Optional<SystemUser> findSystemUserByUsernameAndPassword(String username, String password) {
        SystemUser systemUser = jdbcTemplate
                .query(sqlFindUserByUsernameAndPassword, new Object[] {username, password}, new SystemUserResultSetExtractor());

        return Optional.ofNullable(systemUser);
    }
}
