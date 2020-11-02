package ru.akaleganov.url_shortcut.service.dto;

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

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets url dto.
     *
     * @return the url dto
     */
    public UrlDTO getUrlDTO() {
        return urlDTO;
    }

    /**
     * Sets url dto.
     *
     * @param urlDTO the url dto
     */
    public void setUrlDTO(UrlDTO urlDTO) {
        this.urlDTO = urlDTO;
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
     * @param count the count
     */
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
