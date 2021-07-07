package ru.akaleganov.urlshortcut.service.url;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;
import ru.akaleganov.urlshortcut.domain.Url;

/**
 * The type Url filter.
 */
public class UrlFilter implements Serializable {

    private String login;

    /**
     * Build criteria specification.
     *
     * @return the specification
     */
    public Specification<Url> buildCriteria() {
        Specification<Url> criteria = null;
        if (this.login != null) {
            criteria = (url, cq, cb) -> cb.equal(url.get("user").get("login"), this.login);
        }
        return criteria;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param login
     *         the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Instantiates a new Url filter.
     *
     * @param login
     *         the login
     */
    public UrlFilter(String login) {
        this.login = login;
    }

    /**
     * Instantiates a new Url filter.
     */
    public UrlFilter() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UrlFilter urlFilter = (UrlFilter) o;
        return Objects.equals(login, urlFilter.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

}
