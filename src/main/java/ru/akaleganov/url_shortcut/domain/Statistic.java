package ru.akaleganov.url_shortcut.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class StatisticService
 * pojo сбор и хранение статистики
 *
 * @author Kaleganov Alexander
 * @see Statistic#count
 * @see Statistic#url
 * @since 04 нояб. 20
 */
@Entity(name = "statistic")
public class Statistic extends MappedSuperClass {
    /**
     * количество редиректов по данной ссылки
     */
    @Column(name = "count")
    private Long count = 0L;
    /**
     * ссылка на которую собирается статистика
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "url_id", referencedColumnName = "id")
    private Url url;

    /**
     * Instantiates a new Statistic.
     */
    public Statistic() {
    }

    /**
     * Instantiates a new Statistic.
     *
     * @param url   the url
     * @param count the count
     */
    public Statistic(Url url, Long count) {
        this.url = url;
        this.count = count;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public Long getCount() {
        return count;
    }

    /**
     * Sets count.
     *
     * @param origin the origin
     */
    public void setCount(Long origin) {
        this.count = origin;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public Url getUrl() {
        return url;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    public Statistic setUrl(Url url) {
        this.url = url;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistic statistic = (Statistic) o;
        return Objects.equals(count, statistic.count) &&
                Objects.equals(url, statistic.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, url);
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "count=" + count +
                ", url=" + url +
                '}';
    }
}
