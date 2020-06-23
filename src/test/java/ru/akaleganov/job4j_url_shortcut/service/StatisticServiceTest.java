package ru.akaleganov.job4j_url_shortcut.service;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.job4j_url_shortcut.domain.Statistic;
import ru.akaleganov.job4j_url_shortcut.domain.Url;
import ru.akaleganov.job4j_url_shortcut.repository.UrlRepository;

@DisplayName("тестирование: StatisticServiceTest")
@TestPropertySource(locations = "classpath:application-h2.properties")
@SpringBootTest
class StatisticServiceTest {
    @Autowired
    private StatisticService statisticService;
    @Autowired
    private UrlRepository urlRepository;

    @Test
    @DisplayName("тестирование: Обновление статистики")
    void setStatisticByIdUrl() {
        Url url = new Url();
        url.setOrigin("https://sdsdsdsssss.ru");
        url.setShortCut("sdfsdfdsfdsfdfss");
        url = this.urlRepository.save(url);
        Statistic statistic = this.statisticService.setStatisticByIdUrl(url);
        MatcherAssert.assertThat(statistic.getCount(), Is.is(1L));
        MatcherAssert.assertThat(this.statisticService.findById(statistic.getId()).getCount(), Is.is(1L));
        Statistic statistic2 = this.statisticService.setStatisticByIdUrl(url);
        MatcherAssert.assertThat(statistic2.getCount(), Is.is(2L));
        MatcherAssert.assertThat(this.statisticService.findById(statistic.getId()).getCount(), Is.is(2L));
    }

}
