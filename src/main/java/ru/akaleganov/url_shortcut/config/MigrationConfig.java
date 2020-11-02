package ru.akaleganov.url_shortcut.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.sql.DataSource;

/**
 * Class MigrationConfig
 * настрокая ликвидбейса
 *
 * @author Kaleganov Alexander
 * @since 29 окт. 20
 */
@Configuration
public class MigrationConfig {
    /**
     * Liquibase spring liquibase.
     *
     * @param dataSource the data source
     * @return the spring liquibase
     */
    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:db/changelog-master.xml");
        liquibase.setContexts("mysql, postgres");
        liquibase.setShouldRun(true);
        return liquibase;
    }
}
