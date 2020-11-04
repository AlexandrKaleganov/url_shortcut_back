package ru.akaleganov.urlshortcut.config;

import org.apache.commons.validator.UrlValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class UtilBeans
 * конфигурационный класс для разичных утилитарных бинов
 *
 *
 * @author Kaleganov Alexander
 * @since 31 окт. 20
 */
@Configuration
public class UtilBeans {

    @Bean
    public UrlValidator getUrlValidator() {
        return new UrlValidator();
    }
}
