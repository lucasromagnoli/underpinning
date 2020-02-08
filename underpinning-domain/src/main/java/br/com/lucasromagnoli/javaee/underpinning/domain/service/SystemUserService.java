package br.com.lucasromagnoli.javaee.underpinning.domain.service;

import br.com.lucasromagnoli.javaee.underpinning.domain.model.SystemUser;
import br.com.lucasromagnoli.javaee.underpinning.domain.repository.jdbc.SystemUserJdbcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author github.com/lucasromagnoli
 * @since 04/02/2020
 */
@Service
public class SystemUserService {

    @Autowired
    SystemUserJdbcRepository systemUserJdbcRepository;

    public SystemUser findToAuthenticate (String username, String password) {
        return systemUserJdbcRepository.findSystemUserByUsernameAndPassword(username, password)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    };
}
