package br.com.lucasromagnoli.javaee.underpinning.domain.repository.jdbc.impl;

import br.com.lucasromagnoli.javaee.underpinning.domain.model.SystemUser;
import br.com.lucasromagnoli.javaee.underpinning.domain.repository.jdbc.SystemUserJdbcRepository;
import br.com.lucasromagnoli.javaee.underpinning.domain.repository.jdbc.joinable.extractor.SystemUserResultSetExtractor;
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

    /**
     * TODO: Elaborar e implementar uma solução para manipular e organizar as query
     */
    private static StringBuilder sqlFindUserByUsernameAndPassword = new StringBuilder();

    static {
        sqlFindUserByUsernameAndPassword
                .append("SELECT DISTINCT USER.ID_SYSTEM_USER,")
                .append("                USER.USERNAME,")
                .append("                ROLES.NAME ")
                .append("FROM UND_SYSTEM_USER USER ")
                .append("INNER JOIN UND_SYSTEM_USER_ROLES USER_PIVOT_ROLES")
                .append("   ON USER_PIVOT_ROLES.ID_SYSTEM_USER = USER.ID_SYSTEM_USER ")
                .append("INNER JOIN UND_SYSTEM_ROLES ROLES")
                .append("   ON ROLES.ID_SYSTEM_ROLES = ROLES.ID_SYSTEM_ROLES ")
                .append("WHERE USER.USERNAME = ? AND USER.PASSWORD = ?");
    }


    @Override
    public Optional<SystemUser> findSystemUserByUsernameAndPassword(String username, String password) {
        SystemUser systemUser = jdbcTemplate
                .query(sqlFindUserByUsernameAndPassword.toString(), new Object[] {username, password}, new SystemUserResultSetExtractor());

        return Optional.ofNullable(systemUser);
    }
}
