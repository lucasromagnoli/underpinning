package br.com.lucasromagnoli.javaee.underpinning.domain.config;

/**
 * @author github.com/lucasromagnoli
 * @since 04/02/2020
 */
public class UnderpinningDomainConfigurationParameters {


    private UnderpinningDomainConfigurationParameters() {}

    public static final String PACKAGE_REPOSITORY_JPA               = "br.com.lucasromagnoli.javaee.underpinning.domain.repository.jpa";
    public static final String PACKAGE_REPOSITORY_JDBC              = "br.com.lucasromagnoli.javaee.underpinning.domain.repository.jdbc.impl";
    public static final String PACKAGE_SERVICE                      = "br.com.lucasromagnoli.javaee.underpinning.domain.service";
    public static final String PACKAGE_MODEL                        = "br.com.lucasromagnoli.javaee.underpinning.domain.model";

    public static final String UNDERPINNING_PERSISTENCE_UNIT_NAME   = "javaeeUnderpinning";
}
