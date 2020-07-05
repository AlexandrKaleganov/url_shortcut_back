package ru.akaleganov.url_shortcut.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "url")
public class Url extends MappedSuperClass {
    @Column(name = "origin")
    private String origin;
    @Column(name = "short_cut")
    private String shortCut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Url() {
    }

    public Url(String origin, String shortCut, User user) {
        this.origin = origin;
        this.shortCut = shortCut;
        this.user = user;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getShortCut() {
        return shortCut;
    }

    public void setShortCut(String shortCut) {
        this.shortCut = shortCut;
    }

    public User getUser() {
        return user;
    }

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
