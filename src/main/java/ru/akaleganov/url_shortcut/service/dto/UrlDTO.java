package ru.akaleganov.url_shortcut.service.dto;

import java.util.Objects;

/**
 * дто для получения урла
 *
 * @see UrlDTO#id
 * @see UrlDTO#origin
 * @see UrlDTO#shortCut
 * @see UrlDTO#errorMessage
 * @see UrlDTO#user
 */
public class UrlDTO {
    /**
     * идентификатор
     */
    private Long id;
    /**
     * урл оригинальный
     */
    private String origin;
    /**
     * урл сокращённый
     */
    private String shortCut;
    /**
     * сообщение об ошибке
     */
    private String errorMessage;
    /**
     * пользователь , которому принадлежит урл
     */
    private UserDTO user;

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
     * Gets origin.
     *
     * @return the origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets origin.
     *
     * @param origin the origin
     */
    public UrlDTO setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    /**
     * Gets short cut.
     *
     * @return the short cut
     */
    public String getShortCut() {
        return shortCut;
    }

    /**
     * Sets short cut.
     *
     * @param shortCut the short cut
     */
    public UrlDTO setShortCut(String shortCut) {
        this.shortCut = shortCut;
        return this;
    }

    /**
     * Gets error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets error message.
     *
     * @param errorMessage the error message
     * @return the error message
     */
    public UrlDTO setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public UserDTO getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public UrlDTO setUser(UserDTO user) {
        this.user = user;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UrlDTO urlDTO = (UrlDTO) o;
        return Objects.equals(id, urlDTO.id) &&
                Objects.equals(origin, urlDTO.origin) &&
                Objects.equals(shortCut, urlDTO.shortCut) &&
                Objects.equals(errorMessage, urlDTO.errorMessage) &&
                Objects.equals(user, urlDTO.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, origin, shortCut, errorMessage, user);
    }

    @Override
    public String toString() {
        return "UrlDTO{" +
                "id=" + id +
                ", origin='" + origin + '\'' +
                ", newOrigin='" + shortCut + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", user=" + user +
                '}';
    }
}
