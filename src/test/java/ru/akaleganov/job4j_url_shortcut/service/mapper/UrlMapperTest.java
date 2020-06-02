package ru.akaleganov.job4j_url_shortcut.service.mapper;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.job4j_url_shortcut.domain.Url;
import ru.akaleganov.job4j_url_shortcut.service.dto.UrlDTO;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("тестирование: UrlMapperTest")
@TestPropertySource(locations = "classpath:application-h2.properties")
@SpringBootTest
class UrlMapperTest {
    @Autowired
    private UrlMapper urlMapper;

    @Test
    @DisplayName("тестирование: urlToDto")
    void toDto() {
        Url url = new Url();
        url.setNewOrigin("origin");
        UrlDTO urlDTO = this.urlMapper.toDto(url);
        assertThat(urlDTO.getNewOrigin(), Is.is(url.getNewOrigin()));
    }

    @Test
    @DisplayName("тестирование: urlToDtoList")
    void testToDto() {
        Url url = new Url();
        url.setNewOrigin("origin");
        List<UrlDTO> urlDTOs = this.urlMapper.toDto(Collections.singletonList(url));
        assertThat(urlDTOs.get(0).getNewOrigin(), Is.is(url.getNewOrigin()));
    }

    @Test
    @DisplayName("тестирование: urlToEntityList")
    void toEntity() {
        UrlDTO urlDTO = new UrlDTO();
        urlDTO.setNewOrigin("origin");
        List<Url> urls = this.urlMapper.toEntity(Collections.singletonList(urlDTO));
        assertThat(urls.get(0).getNewOrigin(), Is.is(urlDTO.getNewOrigin()));
    }

    @Test
    @DisplayName("тестирование: urlToEntity")
    void testToEntity() {
        UrlDTO urlDTO = new UrlDTO();
        urlDTO.setNewOrigin("origin");
        Url url = this.urlMapper.toEntity(urlDTO);
        assertThat(url.getNewOrigin(), Is.is(urlDTO.getNewOrigin()));
    }
}
