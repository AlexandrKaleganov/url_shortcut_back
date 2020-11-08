package ru.akaleganov.urlshortcut.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import ru.akaleganov.urlshortcut.domain.Statistic;
import ru.akaleganov.urlshortcut.domain.Url;
import ru.akaleganov.urlshortcut.service.dto.UrlDTO;
import ru.akaleganov.urlshortcut.service.url.UrlFilter;


/**
 * The type Url service.
 */
public interface UrlService {

    /**
     * добалвение урл в бд
     *
     * @param url       урл
     * @param userLogin логин пользователя
     * @return {@link UrlDTO}
     */
    UrlDTO addUrl(String url, String userLogin);

    /**
     * Find url by short cut url dto.
     *
     * @param shortCut короткая ссылка
     * @return получить урл
     */
    UrlDTO findUrlByShortCut(String shortCut);

    /**
     * поиск урла по id
     *
     * @param id {@link Url#getId()}
     * @return the url dto
     */
    UrlDTO findUrlById(Long id);

    /**
     * удаление урла по id
     *
     * @param id {@link Url#getId()}
     */
    void deleteUrlById(Long id);

    /**
     * получить список урл c пагинацией и фильтрами
     *
     * @param pageable  the pageable
     * @param urlFilter the url filter
     * @return the page
     */
    Page<UrlDTO> findAllURl(Pageable pageable, UrlFilter urlFilter);

    Statistic gtStat(Url url);
}
