package ru.akaleganov.url_shortcut.service;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.url_shortcut.domain.Url;
import ru.akaleganov.url_shortcut.repository.UrlRepository;

import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("тестирование: UrlValidService")
@TestPropertySource(locations = "classpath:application-h2.properties")
@SpringBootTest
class UrlValidServiceTest {
    @Autowired
    private UrlValidService urlValidService;
    @Autowired
    private UrlRepository urlRepository;

    @Test
    @DisplayName("тестиование метода проверки формата домена из URL")
    public void isValidDomainTest() {
        assertThat(this.urlValidService.isValidDomain("sdsdsd.ru"),
                Is.is(true));
    }


    @Test
    @DisplayName("тестиование метода проверки формата URL")
    public void isValidUrlTest() {
        assertThat(this.urlValidService.isValidUrl("sdsdsd.ru"),
                Is.is(false));
        assertThat(this.urlValidService.isValidUrl("https://sdsdsd.ru"),
                Is.is(true));
        assertThat(this.urlValidService.isValidUrl("http://sdsdsd.ru"),
                Is.is(true));
    }

    @Test
    @DisplayName("проверка содержит ли теущий урл домен пользователя")
    public void isDomainContainTheCurrentURL() {
        assertThat(this.urlValidService.isDomainContainTheCurrentURL("https://sdsdsd.ru", "sdsdsd.ru"),
                Is.is(true));
        assertThat(this.urlValidService.isDomainContainTheCurrentURL("https://sdsdsd.ru/asdasdasd", "sdsdsd.ru"),
                Is.is(true));
        assertThat(this.urlValidService.isDomainContainTheCurrentURL("https://sdsdsd.ru/asdasdasd", "sdsdsdd.ru"),
                Is.is(false));
    }

    @Test
    @DisplayName("проверка содержит ли теущий урл домен пользователя")
    public void isContainsNewOriginal() {
        assertThat(this.urlValidService.isContainsSortCutToDb("https://sdsdsd.ru"),
                Is.is(false));
        Url url = new Url();
        url.setOrigin("https://sdsdsd.ru");
        url.setShortCut("sdfs");
        this.urlRepository.save(url);

        assertThat(this.urlValidService.isContainsUrlToDataBase("https://sdsdsd.ru"),
                Is.is(true));
        assertThat(this.urlValidService.isContainsSortCutToDb("sdfs"),
                Is.is(true));
        assertThat(this.urlValidService.isContainsUrlToDataBase("https://sddsd.ru"),
                Is.is(false));
        assertThat(this.urlValidService.isContainsSortCutToDb("dfs"),
                Is.is(false));
    }
}
