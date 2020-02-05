package br.com.lucasromagnoli.javaee.underpinning.domain.repository.jdbc.joinable.mapper;

import br.com.lucasromagnoli.javaee.underpinning.domain.model.SystemUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author github.com/lucasromagnoli
 * @since 04/02/2020
 */
public class SystemUserSimpleRowMapper implements RowMapper<SystemUser> {

    @Override
    public SystemUser mapRow(ResultSet resultSet, int lineIndex) throws SQLException {
        SystemUser systemUser = new SystemUser();
        systemUser.setId(resultSet.getLong("ID_SYSTEM_USER"));
        systemUser.setUsername(resultSet.getString("USERNAME"));

        return systemUser;
    }
}
