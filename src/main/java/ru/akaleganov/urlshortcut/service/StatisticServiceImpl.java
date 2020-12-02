package ru.akaleganov.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akaleganov.urlshortcut.domain.Statistic;
import ru.akaleganov.urlshortcut.domain.Url;
import ru.akaleganov.urlshortcut.repository.StatisticRepository;
import ru.akaleganov.urlshortcut.service.dto.StatisticDTO;
import ru.akaleganov.urlshortcut.service.mapper.StatisticMapper;

/**
 * The type Statistic service.
 */
@Service
@AllArgsConstructor
public class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository statisticRepository;
    private final StatisticMapper statisticMapper;
    private final JdbcTemplate jdbcTemplate;
    private final Object lock = new Object();

    /**
     * получить список урл c пагинацией и фильтрами
     *
     * @param pageable the pageable
     * @return the page
     */
    @Transactional(rollbackFor = Exception.class)
    public Page<StatisticDTO> findAllStatistic(Pageable pageable) {
        return this.statisticRepository.findAll(pageable).map(this.statisticMapper::toDto);
    }

    /**
     * Sets statistic by id url.
     *
     * @param urId {@link Url()}
     */
    public void setStatisticByIdUrl(Long urId) {
        this.jdbcTemplate.update("update  statistic set count = count + 1"
                + " where url_id = ?", urId);
    }

    /**
     * найти статистику по конкретному урлу
     * @param urlId {@link Url#getId()}
     * @return {@link Statistic}
     */
    public Statistic findStatisticByUrlId(Long urlId) {
        return this.statisticRepository.findFirstByUrlId(urlId).orElse(new Statistic());
    }

    /**
     * добавить данные в статистику по url
     *
     * @param url {@link Url}
     * @return the statistic
     */
    @Transactional(rollbackFor = Exception.class)
    public Statistic addNewStatisticByUrl(Url url) {
        return this.statisticRepository.save(new Statistic(url, 1L));
    }

    public void createNewStatistic(Url url) {
        this.statisticRepository.save(new Statistic(url, 0L));
    }

    /**
     * получить объект статистики по id
     *
     * @param id {@link Statistic#getId()}
     * @return {@link Statistic}
     */
    public Statistic findById(Long id) {
        return this.statisticRepository.findById(id).orElse(new Statistic());
    }
}
