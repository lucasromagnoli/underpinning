package br.com.lucasromagnoli.javaee.underpinning.domain.repository.jdbc.joinable.extractor;

import br.com.lucasromagnoli.javaee.underpinning.commons.support.BooleanSupport;
import br.com.lucasromagnoli.javaee.underpinning.domain.model.SystemRole;
import br.com.lucasromagnoli.javaee.underpinning.domain.model.SystemUser;
import br.com.lucasromagnoli.javaee.underpinning.domain.repository.jdbc.joinable.mapper.SystemRoleSimpleRowMapper;
import br.com.lucasromagnoli.javaee.underpinning.domain.repository.jdbc.joinable.mapper.SystemUserSimpleRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author github.com/lucasromagnoli
 * @since 04/02/2020
 */
public class SystemUserResultSetExtractor implements ResultSetExtractor<SystemUser> {

    @Override
    public SystemUser extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        SystemUser systemUser = null;
        List<SystemRole> systemRoles = new LinkedList<>();
        int lineIndex = 0 ;

        SystemUserSimpleRowMapper systemUserSimpleRowMapper = new SystemUserSimpleRowMapper();
        SystemRoleSimpleRowMapper systemRoleSimpleRowMapper = new SystemRoleSimpleRowMapper();

        while (resultSet.next()) {
            if (BooleanSupport.isNull(systemUser)) {
                systemUser = systemUserSimpleRowMapper.mapRow(resultSet, lineIndex);
            }
            systemRoles.add(systemRoleSimpleRowMapper.mapRow(resultSet, lineIndex));
            lineIndex++;
        }

        systemUser.setRoles(systemRoles);
        return systemUser;
    }
}
