package ru.akaleganov.job4j_url_shortcut.service.dto;

import java.util.Objects;

/**
 * дто для получения урла
 */
public class StatisticDTO {
    /**
     * id
     */
    private Long id;
    /**
     * урл оригинальный
     */
    private UrlDTO urlDTO;
    /**
     * урл сокращённый
     */
    private Long count;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UrlDTO getUrlDTO() {
        return urlDTO;
    }

    public void setUrlDTO(UrlDTO urlDTO) {
        this.urlDTO = urlDTO;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticDTO that = (StatisticDTO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(urlDTO, that.urlDTO) &&
                Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, urlDTO, count);
    }

    @Override
    public String toString() {
        return "StatisticDTO{" +
                "id=" + id +
                ", origin=" + urlDTO +
                ", count=" + count +
                '}';
    }
}
