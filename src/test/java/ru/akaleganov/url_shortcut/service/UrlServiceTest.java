package ru.akaleganov.url_shortcut.service;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.url_shortcut.domain.Role;
import ru.akaleganov.url_shortcut.repository.RoleRepository;
import ru.akaleganov.url_shortcut.service.dto.UrlDTO;
import ru.akaleganov.url_shortcut.service.dto.UserDTO;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * The type Url service test.
 */
@DisplayName("тестирование: URLService")
@TestPropertySource(locations = "classpath:application-h2.properties")
@SpringBootTest
class UrlServiceTest {
    @Autowired
    private UrlService urlService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    /**
     * Add test.
     */
    @Test
    @DisplayName("тестирование: добавление   URL в бд")
    public void addTest() {
        this.roleRepository.save(new Role(2L, "USER"));
        UserDTO user = this.userService.createUsersByUrl("alexmur07.ru");
        UrlDTO urlDTO = this.urlService.addUrl("https://alexmur07.ru/ddskr", user.getLogin());
        assertThat(urlDTO.getId() != null, Is.is(true));
    }

    /**
     * Add error test.
     */
    @Test
    @DisplayName("тестирование: добавление  URL в бд ErrorMEssage")
    public void addErrorTest() {
        this.roleRepository.save(new Role(2L, "USER"));
        UserDTO user = this.userService.createUsersByUrl("alexmur08.ru");
        this.urlService.addUrl("https://alexmur08.ru/ddskr2", user.getLogin());
        UrlDTO urlDTO2 = this.urlService.addUrl("https://alexmur08.ru/ddskr2", user.getLogin());
        assertThat(urlDTO2.getId() != null, Is.is(false));
        assertThat(urlDTO2.getErrorMessage(), Is.is("данный url  уже содержится в бд"));
    }

    /**
     * Remove test.
     */
    @Test
    @DisplayName("тестирование: удаление URL из бд")
    public void removeTest() {
        this.roleRepository.save(new Role(2L, "USER"));
        UserDTO user = this.userService.createUsersByUrl("alexmur09.ru");
        UrlDTO urlDTO = this.urlService.addUrl("https://alexmur09.ru/ddskr3", user.getLogin());
        this.urlService.deleteUrlById(urlDTO.getId());
        assertThat(this.urlService.findUrlById(urlDTO.getId()).getId() == null, Is.is(true));
    }

    /**
     * Find url by short cut test.
     */
    @Test
    @DisplayName("тестирование: поиск URL по shortCut")
    public void findUrlByShortCutTest() {
        this.roleRepository.save(new Role(2L, "USER"));
        UserDTO user = this.userService.createUsersByUrl("alexmur10.ru");
        UrlDTO urlDTO = this.urlService.addUrl("https://alexmur10.ru/ddskr3", user.getLogin());
        UrlDTO rsl = this.urlService.findUrlByShortCut(urlDTO.getShortCut());
        assertThat(rsl.getOrigin(), Is.is("https://alexmur10.ru/ddskr3"));
    }
}
