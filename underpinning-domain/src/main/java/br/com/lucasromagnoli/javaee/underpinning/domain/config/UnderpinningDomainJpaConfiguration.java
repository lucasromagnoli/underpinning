package br.com.lucasromagnoli.javaee.underpinning.domain.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author github.com/lucasromagnoli
 * @since 04/02/2020
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {
        UnderpinningDomainConfigurationParameters.PACKAGE_REPOSITORY_JPA
})
public class UnderpinningDomainJpaConfiguration {

    @Bean
    public JpaVendorAdapter javaeeUnderpinningJpaVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);
        vendorAdapter.setShowSql(false);
        vendorAdapter.setGenerateDdl(false);
        return vendorAdapter;
    }

    @Bean
    public EntityManagerFactory javaeeCorporativoEntityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setJtaDataSource(dataSource);
        factoryBean.setJpaVendorAdapter(javaeeUnderpinningJpaVendorAdapter());
        factoryBean.setPackagesToScan(UnderpinningDomainConfigurationParameters.PACKAGE_MODEL);
        factoryBean.setPersistenceUnitName(UnderpinningDomainConfigurationParameters.UNDERPINNING_PERSISTENCE_UNIT_NAME);
        return factoryBean.getObject();
    }

}
