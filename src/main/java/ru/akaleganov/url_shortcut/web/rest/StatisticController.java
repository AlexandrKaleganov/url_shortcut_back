package ru.akaleganov.url_shortcut.web.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.akaleganov.url_shortcut.service.StatisticService;
import ru.akaleganov.url_shortcut.service.dto.StatisticDTO;

@RestController
@RequestMapping("/api")
public class StatisticController {
    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/statistic")
    public ResponseEntity<Page<StatisticDTO>> findAllUrl(Pageable pageable) {
        return ResponseEntity.ok().body(this.statisticService.findAllStatistic(pageable));
    }
}


