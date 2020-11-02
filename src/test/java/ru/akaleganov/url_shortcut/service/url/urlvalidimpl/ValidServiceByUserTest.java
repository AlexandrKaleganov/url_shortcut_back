package ru.akaleganov.url_shortcut.service.url.urlvalidimpl;

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
@DisplayName("тестирование: UrlValidServiceByUserTest")
class ValidServiceByUserTest {

    private final ValidServiceByUser urlValidServiceByUser =
            new ValidServiceByUser();

    @Test
    @DisplayName("проверка урл содержится ли в нём домен пользователя")
    public void isUrlValidServiceByUser() {
        User user = new User();
        user.setDomain("google.ru");
        assertNotNull(
                this.urlValidServiceByUser.isValid("asdas", user)
                .getErrorMessage());
        assertNull(
                this.urlValidServiceByUser.isValid("https://google.ru", user)
                .getErrorMessage());
    }

}
