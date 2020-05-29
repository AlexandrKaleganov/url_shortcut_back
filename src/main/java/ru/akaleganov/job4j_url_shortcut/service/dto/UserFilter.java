package ru.akaleganov.job4j_url_shortcut.service.dto;

import org.springframework.data.jpa.domain.Specification;
import ru.akaleganov.job4j_url_shortcut.domain.User;

import java.io.Serializable;
import java.util.Objects;

public class UserFilter implements Serializable {
    private String login;
    private String firstName;
    private String url;

    public Specification<User> buildCriteria() {
        Specification<User> criteria = null;
        if (this.login != null) {
            criteria = (user, cq, cb) -> cb.equal(user.get("login"), this.login);
        }
        if (this.firstName != null) {
            if (criteria == null) {
                criteria = (user, cq, cb) -> cb.equal(user.get("firstName"), this.firstName);
            } else {
                criteria = criteria.and((user, cq, cb) -> cb.equal(user.get("firstName"), this.firstName));
            }
        }
        if (this.url != null) {
            if (criteria == null) {
                criteria = (user, cq, cb) -> cb.equal(user.get("url"), this.url);
            } else {
                criteria = criteria.and((user, cq, cb) -> cb.equal(user.get("url"), this.url));
            }
        }
        return criteria;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFilter that = (UserFilter) o;
        return Objects.equals(login, that.login) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, firstName, url);
    }

    @Override
    public String toString() {
        return "UserFilter{" +
                "login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
