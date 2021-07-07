package ru.akaleganov.urlshortcut.service;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;
import ru.akaleganov.urlshortcut.domain.User;

/**
 * The type User filter.
 */
public class UserFilter implements Serializable {

    private String login;

    private String firstName;

    private String domain;

    /**
     * Build criteria specification.
     *
     * @return the specification
     */
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
        if (this.domain != null) {
            if (criteria == null) {
                criteria = (user, cq, cb) -> cb.equal(user.get("url"), this.domain);
            } else {
                criteria = criteria.and((user, cq, cb) -> cb.equal(user.get("url"), this.domain));
            }
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
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName
     *         the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets domain.
     *
     * @return the domain
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Sets domain.
     *
     * @param domain
     *         the domain
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserFilter that = (UserFilter) o;
        return Objects.equals(login, that.login)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(domain, that.domain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, firstName, domain);
    }

    @Override
    public String toString() {
        return "UserFilter{"
                + "login='" + login + '\''
                + ", firstName='" + firstName + '\''
                + ", url='" + domain + '\''
                + '}';
    }

}
