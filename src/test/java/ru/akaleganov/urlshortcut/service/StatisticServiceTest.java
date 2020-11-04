package ru.akaleganov.urlshortcut.service;

import org.hamcrest.MatcherAssert;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.urlshortcut.domain.Statistic;
import ru.akaleganov.urlshortcut.domain.Url;
import ru.akaleganov.urlshortcut.repository.UrlRepository;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The type Statistic service test.
 */
@DisplayName("тестирование: StatisticServiceTest")
@TestPropertySource(locations = "classpath:application-h2.properties")
@SpringBootTest
class StatisticServiceTest {
    @Autowired
    StatisticService statisticService;
    @Autowired
    UrlService urlService;
    @Autowired
    private UrlRepository urlRepository;
    private final static Logger log = LoggerFactory.getLogger(StatisticServiceTest.class);

    /**
     * Sets statistic by id url.
     */
    @Test
    @DisplayName("тестирование: Обновление статистики")
    void setStatisticByIdUrl() {
        Url url = new Url();
        url.setOrigin("https://sdsdsdsssss.ru");
        url.setShortCut("sdfsdfdsfdsfdfss");
        url = this.urlRepository.save(url);
        this.statisticService.createNewStatistic(url);
        Statistic statistic = statisticService.findStatisticByUrlId(url.getId());
        MatcherAssert.assertThat(statistic.getCount(), Is.is(0L));
        MatcherAssert.assertThat(this.statisticService.findById(statistic.getId()).getCount(), Is.is(0L));
        this.statisticService.setStatisticByIdUrl(url.getId());
        Statistic statistic2 = statisticService.findStatisticByUrlId(url.getId());
        MatcherAssert.assertThat(statistic2.getCount(), Is.is(1L));
        MatcherAssert.assertThat(this.statisticService.findById(statistic2.getId()).getCount(), Is.is(1L));
    }

    /**
     * Test set statistic by id url multi thread.
     *
     * @throws CloneNotSupportedException the clone not supported exception
     */
    @Test
    @DisplayName("тестирование: Обновление статистики в многопоточке")
    void testSetStatisticByIdUrlMultiThread() throws CloneNotSupportedException {
        Url url = new Url();
        url.setOrigin("https://sdsdassd888888dddddssfdksdsssss.ru");
        url.setShortCut("sdfsdfdssdsffdssadafdfss");
        url = this.urlRepository.save(url);
        this.statisticService.createNewStatistic(url);
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        Long urlId = url.getId();
        log.error(urlId.toString());
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10000; i++) {
            service.execute(() -> {
                this.statisticService.setStatisticByIdUrl(urlId);
                countDownLatch.countDown();
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        Statistic statistic = this.statisticService.findStatisticByUrlId(url.getId());
        MatcherAssert.assertThat(statistic.getCount(), Is.is(10000L));
    }


    /**
     * Test set statistic by id url single thread.
     *
     * @throws CloneNotSupportedException the clone not supported exception
     */
    @Test
    @DisplayName("тестирование: Обновление статистики последовательно")
    void testSetStatisticByIdUrlSingleThread() throws CloneNotSupportedException {
        Url url = new Url();
        url.setOrigin("https://sdsdksajdkjassfsfdksdsssss.ru");
        url.setShortCut("sdfsdfdssdsffdssadafdfss");
        url = this.urlRepository.save(url);
        Long finalUrl = url.getId();
        this.statisticService.createNewStatistic(url);
        for (int i = 0; i < 10_000; i++) {
            this.statisticService.setStatisticByIdUrl(finalUrl);
        }
        this.statisticService.setStatisticByIdUrl(finalUrl);
        Statistic statistic = this.statisticService.findStatisticByUrlId(url.getId());
        MatcherAssert.assertThat(statistic.getCount(), Is.is(10001L));
    }
}
