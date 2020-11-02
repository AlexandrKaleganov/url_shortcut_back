package ru.akaleganov.url_shortcut.service.url.urlvalidimpl;


import org.apache.commons.validator.UrlValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.akaleganov.url_shortcut.domain.User;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * The type Url valid service test.
 */
@DisplayName("тестирование: UrlValidServiceByUrlValidatorApache")
class UrlValidServiceByValidatorApacheTest {

    private final UrlValidServiceByValidatorApache urlValidServiceByUrlValidatorApache =
            new UrlValidServiceByValidatorApache(new UrlValidator());

    @Test
    @DisplayName("проверка урл через UrlValidator apache")
    public void isValidDomain() {
        assertNotNull(
                this.urlValidServiceByUrlValidatorApache.isValid("asdas", new User())
                        .getErrorMessage());
        assertNull(
                this.urlValidServiceByUrlValidatorApache.isValid("http://google.ru", new User())
                        .getErrorMessage());
    }

}
