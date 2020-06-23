package ru.akaleganov.job4j_url_shortcut.domain;

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

    public Statistic() {
    }

    public Statistic(Url url, Long count) {
        this.url = url;
        this.count = count;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long origin) {
        this.count = origin;
    }

    public Url getUrl() {
        return url;
    }

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
