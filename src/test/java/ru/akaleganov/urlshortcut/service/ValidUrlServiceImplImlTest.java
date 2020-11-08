package ru.akaleganov.urlshortcut.service;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.urlshortcut.repository.UrlRepository;
import ru.akaleganov.urlshortcut.service.dto.UserDTO;
import ru.akaleganov.urlshortcut.service.user.uservalibimpl.ValidUserServiceIsDomainValid;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * The type Url valid service test.
 */
@DisplayName("тестирование: UrlValidService")
@TestPropertySource(locations = "classpath:application-h2.properties")
@SpringBootTest
class ValidUrlServiceImplImlTest {
    @Autowired
    private ValidUserServiceIsDomainValid urlValidService;
    @Autowired
    private UrlRepository urlRepository;

    /**
     * Is valid domain test.
     */
    @Test
    @DisplayName("тестиование метода проверки формата домена из URL")
    public void isValidDomainTest() {
        UserDTO res = this.urlValidService.isValid(new UserDTO().setDomain("sdsdsd.ru"));
        System.out.println(res);
        assertThat(res.getErrorMessage() == null,
                Is.is(true));
    }

}
