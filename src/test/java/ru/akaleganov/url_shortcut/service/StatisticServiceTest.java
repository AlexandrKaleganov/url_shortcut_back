package ru.akaleganov.url_shortcut.service;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.url_shortcut.domain.Statistic;
import ru.akaleganov.url_shortcut.domain.Url;
import ru.akaleganov.url_shortcut.repository.UrlRepository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@DisplayName("тестирование: StatisticServiceTest")
@TestPropertySource(locations = "classpath:application-h2.properties")
@SpringBootTest
class StatisticServiceTest {
    @Autowired
    private StatisticService statisticService;
    @Autowired
    private UrlRepository urlRepository;
    private final static Logger log = LoggerFactory.getLogger(StatisticServiceTest.class);

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

    @Test
    @DisplayName("тестирование: Обновление статистики в многопоточке")
    void testSetStatisticByIdUrlMultiThread() throws CloneNotSupportedException {
        Url url = new Url();
        url.setOrigin("https://sdsdksajdkjasdksdsssss.ru");
        url.setShortCut("sdfsdfdssdsffdsfdfss");
        url = this.urlRepository.save(url);
        Url finalUrl = url;
        this.statisticService.setStatisticByIdUrl(finalUrl);
        ExecutorService service = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 10_000; i++) {
            Url erll = (Url) finalUrl.clone();
            log.info(erll.toString());
            service.execute(() -> this.statisticService.setStatisticByIdUrl(erll));
        }
        Statistic statistic = this.statisticService.setStatisticByIdUrl(url);
        MatcherAssert.assertThat(statistic.getCount(), Is.is(10002L));
    }


    @Test
    @DisplayName("тестирование: Обновление статистики последовательно")
    void testSetStatisticByIdUrlSingleThread() throws CloneNotSupportedException {
        Url url = new Url();
        url.setOrigin("https://sdsdksajdkjassfsfdksdsssss.ru");
        url.setShortCut("sdfsdfdssdsffdssadafdfss");
        url = this.urlRepository.save(url);
        Url finalUrl = url;
        this.statisticService.setStatisticByIdUrl(finalUrl);
        for (int i = 0; i < 10_000; i++) {

          this.statisticService.setStatisticByIdUrl(finalUrl);
        }
        Statistic statistic = this.statisticService.setStatisticByIdUrl(url);
        MatcherAssert.assertThat(statistic.getCount(), Is.is(10002L));
    }


}
