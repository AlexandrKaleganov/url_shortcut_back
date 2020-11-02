package ru.akaleganov.url_shortcut.service.url.urlvalidimpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.akaleganov.url_shortcut.domain.Url;
import ru.akaleganov.url_shortcut.domain.User;
import ru.akaleganov.url_shortcut.repository.UrlRepository;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The type Url valid service test.
 */
@DisplayName("тестирование: UrlValidServiceIsPresentByUserTest")
class ValidServiceIsPresentInDataBaseTest {
    private final UrlRepository urlRepository = mock(UrlRepository.class);
    private final ValidServiceIsPresentInDataBase urlValidServiceIsPresentInDataBase =
            new ValidServiceIsPresentInDataBase(urlRepository);

    @Test
    @DisplayName("проверка урл содержится ли уже данный урл в бд")
    public void setUrlValidServiceIsPresentInDataBase() {
      when(urlRepository.findAllByOrigin("https://google.ru")).thenReturn(new ArrayList<>());
      when(urlRepository.findAllByOrigin("https://mail.ru"))
              .thenReturn(new ArrayList<>(Collections.singletonList(new Url())));
        User user = new User();
        user.setDomain("google.ru");
        assertNotNull(
                this.urlValidServiceIsPresentInDataBase.isValid("https://mail.ru", user)
                        .getErrorMessage());
        assertNull(
                this.urlValidServiceIsPresentInDataBase.isValid("https://google.ru", user)
                        .getErrorMessage());
    }

}
