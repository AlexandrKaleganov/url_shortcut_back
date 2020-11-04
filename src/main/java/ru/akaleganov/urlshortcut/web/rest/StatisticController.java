package ru.akaleganov.urlshortcut.web.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.akaleganov.urlshortcut.domain.Statistic;
import ru.akaleganov.urlshortcut.domain.Url;
import ru.akaleganov.urlshortcut.service.StatisticService;
import ru.akaleganov.urlshortcut.service.dto.StatisticDTO;

/**
 * The type Statistic controller.
 */
@RestController
@RequestMapping("/api")
public class StatisticController {
    private final StatisticService statisticService;

    /**
     * Instantiates a new Statistic controller.
     *
     * @param statisticService the statistic service
     */
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    /**
     * Find all url response entity.
     *
     * @param pageable the pageable
     * @return the response entity
     */
    @GetMapping("/statistic")
    public ResponseEntity<Page<StatisticDTO>> findAllUrl(Pageable pageable) {
        return ResponseEntity.ok().body(this.statisticService.findAllStatistic(pageable));
    }
    public Statistic findByUrl(Url url) {
        return statisticService.findStatisticByUrlId(url.getId());
    }
}


