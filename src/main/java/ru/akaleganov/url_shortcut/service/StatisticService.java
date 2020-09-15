package ru.akaleganov.url_shortcut.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.akaleganov.url_shortcut.domain.Statistic;
import ru.akaleganov.url_shortcut.domain.Url;
import ru.akaleganov.url_shortcut.repository.StatisticRepository;
import ru.akaleganov.url_shortcut.repository.UrlRepository;
import ru.akaleganov.url_shortcut.service.dto.StatisticDTO;
import ru.akaleganov.url_shortcut.service.mapper.StatisticMapper;

@Service
public class StatisticService {

    private final StatisticRepository statisticRepository;
    private final StatisticMapper statisticMapper;

    public StatisticService(StatisticRepository statisticRepository, UrlRepository urlRepository, StatisticMapper statisticMapper) {
        this.statisticRepository = statisticRepository;
        this.statisticMapper = statisticMapper;
    }

    /**
     * получить список урл c пагинацией и фильтрами
     */
    @Transactional
    public Page<StatisticDTO> findAllStatistic(Pageable pageable) {
        return this.statisticRepository.findAll(pageable).map(this.statisticMapper::toDto);
    }

    /**
     * @param url {@link Url()}
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public  Statistic setStatisticByIdUrl(Url url) {
        Statistic statistic = this.statisticRepository.findFirstByUrlId(url.getId()).orElse(new Statistic());
        if (statistic.getId() != null) {
            statistic.setCount(statistic.getCount() + 1);
            return statistic;
        } else {
            return this.addNewStatisticByUrl(url);
        }
    }

    /**
     * добавить данные в статистику по url
     *
     * @param url {@link Url}
     */
    @Transactional
    public Statistic addNewStatisticByUrl(Url url) {
        return this.statisticRepository.save(new Statistic(url, 1L));
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
