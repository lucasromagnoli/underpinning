package br.com.lucasromagnoli.javaee.underpinning.domain.service;

import br.com.lucasromagnoli.javaee.underpinning.commons.exception.UnderpinningAuthenticationFail;
import br.com.lucasromagnoli.javaee.underpinning.domain.model.SystemUser;
import br.com.lucasromagnoli.javaee.underpinning.domain.repository.jpa.SystemUserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author github.com/lucasromagnoli
 * @since 04/02/2020
 */
@Service
public class SystemUserService {

    @Autowired
    SystemUserJpaRepository systemUserJpaRepository;

    public SystemUser findToAuthenticate (String username) throws UnderpinningAuthenticationFail {
        return systemUserJpaRepository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new UnderpinningAuthenticationFail("No users were found with this username"));
    };
}
