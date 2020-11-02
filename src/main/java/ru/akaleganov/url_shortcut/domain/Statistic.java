package ru.akaleganov.url_shortcut.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * сбор и хранение статистики
 */
@Entity(name = "statistic")
public class Statistic extends MappedSuperClass {
    @Column(name = "count")
    private Long count;

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
     * @param user the user
     */
    public void setUrl(Url user) {
        this.url = user;
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
