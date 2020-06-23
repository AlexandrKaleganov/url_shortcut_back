package ru.akaleganov.job4j_url_shortcut.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akaleganov.job4j_url_shortcut.domain.Statistic;
import ru.akaleganov.job4j_url_shortcut.domain.Url;
import ru.akaleganov.job4j_url_shortcut.repository.StatisticRepository;
import ru.akaleganov.job4j_url_shortcut.repository.UrlRepository;

@Service
public class StatisticService {

    private final StatisticRepository statisticRepository;

    public StatisticService(StatisticRepository statisticRepository, UrlRepository urlRepository) {
        this.statisticRepository = statisticRepository;
    }

    /**
     * @param url {@link Url()}
     */
    @Transactional
    public Statistic setStatisticByIdUrl(Url url) {
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
    public Statistic addNewStatisticByUrl(Url url) {
        return this.statisticRepository.save(new Statistic(url, 1L));
    }

    /**
     * добавить данные в статистику по url
     *
     * @param id {@link Statistic#getId()}
     */
    public Statistic findById(Long id) {
        return this.statisticRepository.findById(id).orElse(new Statistic());
    }
}
