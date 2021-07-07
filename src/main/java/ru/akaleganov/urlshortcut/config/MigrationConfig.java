package ru.akaleganov.urlshortcut.config;

import javax.sql.DataSource;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class MigrationConfig настрокая ликвидбейса
 *
 * @author Kaleganov Alexander
 * @since 29 окт. 20
 */
@Configuration
public class MigrationConfig {

    /**
     * Liquibase spring liquibase.
     *
     * @param dataSource
     *         the data source
     *
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
