package ru.akaleganov.url_shortcut.service;

import org.springframework.data.jpa.domain.Specification;
import ru.akaleganov.url_shortcut.domain.Url;

import java.io.Serializable;
import java.util.Objects;

public class UrlFilter implements Serializable {
    private String login;

    public Specification<Url> buildCriteria() {
        Specification<Url> criteria = null;
        if (this.login != null) {
            criteria = (url, cq, cb) -> cb.equal(url.get("user").get("login"), this.login);
        }
        return criteria;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UrlFilter(String login) {
        this.login = login;
    }

    public UrlFilter() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UrlFilter urlFilter = (UrlFilter) o;
        return Objects.equals(login, urlFilter.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }
}
