package br.com.lucasromagnoli.javaee.underpinning.domain.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author github.com/lucasromagnoli
 * @since 04/02/2020
 */
@Configuration
@ComponentScan(basePackages = {
        UnderpinningDomainConfigurationParameters.PACKAGE_REPOSITORY_JDBC
})
public class UnderpinningDomainJdbcConfiguration {

}
