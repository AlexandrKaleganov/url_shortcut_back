package ru.akaleganov.urlshortcut.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.akaleganov.urlshortcut.domain.Statistic;
import ru.akaleganov.urlshortcut.domain.Url;
import ru.akaleganov.urlshortcut.service.dto.StatisticDTO;

/**
 * Class StatisticService
 * управление сервисом статистики
 *
 * @author Kaleganov Alexander
 * @since 04 нояб. 20
 */
public interface StatisticService {
    /**
     * получить список урл c пагинацией и фильтрами
     *
     * @param pageable the pageable
     * @return the page
     */
    Page<StatisticDTO> findAllStatistic(Pageable pageable);

    /**
     * Sets statistic by id url.
     *
     * @param urId {@link Url()}
     */
    void setStatisticByIdUrl(Long urId);

    Statistic findStatisticByUrlId(Long urlId);

    /**
     * добавить данные в статистику по url
     *
     * @param url {@link Url}
     * @return the statistic
     */
    Statistic addNewStatisticByUrl(Url url);

    void createNewStatistic(Url url);

    /**
     * получить объект статистики по id
     *
     * @param id {@link Statistic#getId()}
     * @return {@link Statistic}
     */
    Statistic findById(Long id);
}
