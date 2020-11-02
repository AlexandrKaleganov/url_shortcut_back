package ru.akaleganov.url_shortcut.domain;

import javax.persistence.*;
import java.util.Objects;

/**
 * The type Url.
 */
@Entity(name = "url")
public class Url extends MappedSuperClass {
    @Column(name = "origin")
    private String origin;
    @Column(name = "short_cut")
    private String shortCut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    /**
     * Instantiates a new Url.
     */
    public Url() {
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Url url = new Url();
        url.setUser(this.user);
        url.setOrigin(this.origin);
        url.setShortCut(this.shortCut);
        url.setId(this.getId());
        return url;
    }

    /**
     * Instantiates a new Url.
     *
     * @param origin   the origin
     * @param shortCut the short cut
     * @param user     the user
     */
    public Url(String origin, String shortCut, User user) {
        this.origin = origin;
        this.shortCut = shortCut;
        this.user = user;
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
    public void setOrigin(String origin) {
        this.origin = origin;
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
    public void setShortCut(String shortCut) {
        this.shortCut = shortCut;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Url url = (Url) o;
        return Objects.equals(origin, url.origin) &&
                Objects.equals(shortCut, url.shortCut) &&
                Objects.equals(user, url.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, shortCut, user);
    }

    @Override
    public String toString() {
        return "Url{" +
                "origin='" + origin + '\'' +
                ", newOrigin='" + shortCut + '\'' +
                ", user=" + user +
                '}';
    }
}
