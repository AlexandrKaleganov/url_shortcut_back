package ru.akaleganov.job4j_url_shortcut.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "url")
public class Url extends MappedSuperClass {
    @Column(name = "origin")
    private String origin;
    @Column(name =  "new_origin")
    private String newOrigin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

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
                Objects.equals(newOrigin, url.newOrigin) &&
                Objects.equals(user, url.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, newOrigin, user);
    }

    @Override
    public String toString() {
        return "Url{" +
                "origin='" + origin + '\'' +
                ", newOrigin='" + newOrigin + '\'' +
                ", user=" + user +
                '}';
    }
}
