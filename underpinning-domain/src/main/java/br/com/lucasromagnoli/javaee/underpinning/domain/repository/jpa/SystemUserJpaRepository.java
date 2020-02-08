package br.com.lucasromagnoli.javaee.underpinning.domain.repository.jpa;

import br.com.lucasromagnoli.javaee.underpinning.domain.model.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author github.com/lucasromagnoli
 * @since 04/02/2020
 */
@Repository
public interface SystemUserJpaRepository extends JpaRepository<SystemUser, Long> {

}
