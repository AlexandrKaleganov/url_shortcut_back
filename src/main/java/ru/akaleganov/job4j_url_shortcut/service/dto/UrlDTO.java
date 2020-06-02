package ru.akaleganov.job4j_url_shortcut.service.dto;

import java.util.Objects;

/**
 * дто для получения урла
 */
public class UrlDTO {
    /**
     * id
     */
    private Long id;
    /**
     * урл оригинальный
     */
    private String origin;
    /**
     * урл сокращённый
     */
    private String newOrigin;
    /**
     * сообщение об ошибке
     */
    private String errorMessage;
    /**
     * пользователь , которому принадлежит урл
     */
    private UserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getNewOrigin() {
        return newOrigin;
    }

    public void setNewOrigin(String newOrigin) {
        this.newOrigin = newOrigin;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public UrlDTO setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UrlDTO urlDTO = (UrlDTO) o;
        return Objects.equals(id, urlDTO.id) &&
                Objects.equals(origin, urlDTO.origin) &&
                Objects.equals(newOrigin, urlDTO.newOrigin) &&
                Objects.equals(errorMessage, urlDTO.errorMessage) &&
                Objects.equals(user, urlDTO.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, origin, newOrigin, errorMessage, user);
    }

    @Override
    public String toString() {
        return "UrlDTO{" +
                "id=" + id +
                ", origin='" + origin + '\'' +
                ", newOrigin='" + newOrigin + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                ", user=" + user +
                '}';
    }
}
