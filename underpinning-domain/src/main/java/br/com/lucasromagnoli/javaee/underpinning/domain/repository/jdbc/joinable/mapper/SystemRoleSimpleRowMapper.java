package br.com.lucasromagnoli.javaee.underpinning.domain.repository.jdbc.joinable.mapper;

import br.com.lucasromagnoli.javaee.underpinning.domain.model.SystemRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author github.com/lucasromagnoli
 * @since 04/02/2020
 */
public class SystemRoleSimpleRowMapper implements RowMapper<SystemRole> {

    @Override
    public SystemRole mapRow(ResultSet resultSet, int lineIndex) throws SQLException {
        SystemRole systemRole = new SystemRole();

        systemRole.setName(resultSet.getString("NAME"));
        return systemRole;
    }
}
