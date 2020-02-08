package br.com.lucasromagnoli.javaee.underpinning.domain.config.init;

import br.com.lucasromagnoli.javaee.underpinning.domain.config.UnderpinningDomainJdbcConfiguration;
import br.com.lucasromagnoli.javaee.underpinning.domain.config.UnderpinningDomainJpaConfiguration;
import br.com.lucasromagnoli.javaee.underpinning.domain.config.UnderpinningDomainServiceConfiguration;

/**
 * @author github.com/lucasromagnoli
 * @since 04/02/2020
 */
public class UnderpinningDomainInitializer {
    private UnderpinningDomainInitializer() {}

    public static Class<?>[] getRootConfigClasses() {
        return new Class[] {
                UnderpinningDomainJpaConfiguration.class,
                UnderpinningDomainJdbcConfiguration.class,
                UnderpinningDomainServiceConfiguration.class
        };
    }
}
