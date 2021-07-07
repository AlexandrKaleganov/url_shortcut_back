package ru.akaleganov.urlshortcut.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * The type User.
 */
@Entity(name = "lex_users")
@ToString
public class User extends MappedSuperClass implements UserDetails {

    @Column(name = "login")
    private String login;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "pwd")
    private String pwd;

    @Column(name = "domain")
    private String domain;

    /**
     * Instantiates a new User.
     *
     * @param id
     *         the id
     */
    public User(Long id) {
        this.setId(id);
    }

    /**
     * Instantiates a new User.
     */
    public User() {

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
     * @param url
     *         the url
     */
    public void setDomain(String url) {
        this.domain = url;
    }

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "lex_users_lex_roles", joinColumns = @JoinColumn(name = "lex_users_id"),
               inverseJoinColumns = @JoinColumn(name = "lex_roles_id"))
    private List<Role> roles;

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Gets roles.
     *
     * @return the roles
     */
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * Sets roles.
     *
     * @param roles
     *         the roles
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    /**
     * Sets login.
     *
     * @param login
     *         the login
     *
     * @return the login
     */
    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    /**
     * Gets pwd.
     *
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * Sets pwd.
     *
     * @param pwd
     *         the pwd
     *
     * @return the pwd
     */
    public User setPwd(String pwd) {
        this.pwd = pwd;
        return this;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName
     *         the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
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
     * Gets middle name.
     *
     * @return the middle name
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets middle name.
     *
     * @param middleName
     *         the middle name
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return new ArrayList<>(getRoles());
    }

    @Override
    public String getPassword() {
        return getPwd();
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User users = (User) o;
        return Objects.equals(login, users.login)
                && Objects.equals(lastName, users.lastName)
                && Objects.equals(firstName, users.firstName)
                && Objects.equals(middleName, users.middleName)
                && Objects.equals(pwd, users.pwd)
                && Objects.equals(roles, users.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, lastName, firstName, middleName, pwd, roles);
    }

    @Override
    public String toString() {
        return "Users{"
                + "login='" + login + '\''
                + "id='" + super.getId() + '\''
                + ", lastName='" + lastName + '\''
                + ", firstName='" + firstName + '\''
                + ", middleName='" + middleName + '\''
                + ", url='" + domain + '\''
                + '}';
    }

}
