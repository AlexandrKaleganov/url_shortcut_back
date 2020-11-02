package ru.akaleganov.url_shortcut.service.mapper;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.url_shortcut.domain.Url;
import ru.akaleganov.url_shortcut.service.dto.UrlDTO;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * The type Url mapper test.
 */
@DisplayName("тестирование: UrlMapperTest")
@TestPropertySource(locations = "classpath:application-h2.properties")
@SpringBootTest
class UrlMapperTest {
    @Autowired
    private UrlMapper urlMapper;

    /**
     * To dto.
     */
    @Test
    @DisplayName("тестирование: urlToDto")
    void toDto() {
        Url url = new Url();
        url.setShortCut("origin");
        UrlDTO urlDTO = this.urlMapper.toDto(url);
        assertThat(urlDTO.getShortCut(), Is.is(url.getShortCut()));
    }

    /**
     * Test to dto.
     */
    @Test
    @DisplayName("тестирование: urlToDtoList")
    void testToDto() {
        Url url = new Url();
        url.setShortCut("origin");
        List<UrlDTO> urlDTOs = this.urlMapper.toDto(Collections.singletonList(url));
        assertThat(urlDTOs.get(0).getShortCut(), Is.is(url.getShortCut()));
    }

    /**
     * To entity.
     */
    @Test
    @DisplayName("тестирование: urlToEntityList")
    void toEntity() {
        UrlDTO urlDTO = new UrlDTO();
        urlDTO.setShortCut("origin");
        List<Url> urls = this.urlMapper.toEntity(Collections.singletonList(urlDTO));
        assertThat(urls.get(0).getShortCut(), Is.is(urlDTO.getShortCut()));
    }

    /**
     * Test to entity.
     */
    @Test
    @DisplayName("тестирование: urlToEntity")
    void testToEntity() {
        UrlDTO urlDTO = new UrlDTO();
        urlDTO.setShortCut("origin");
        Url url = this.urlMapper.toEntity(urlDTO);
        assertThat(url.getShortCut(), Is.is(urlDTO.getShortCut()));
    }
}
