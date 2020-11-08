package ru.akaleganov.urlshortcut.service.dto;

import ru.akaleganov.urlshortcut.domain.Role;
import ru.akaleganov.urlshortcut.domain.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ДТО для ответа и для изменений данных пользователя
 */
public class UserDTO implements Serializable {
    private Long id;
    private String login;
    private String lastName;
    private String firstName;
    private String middleName;
    private String pwd;
    private String domain;
    private String errorMessage;

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
    public UserDTO setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    private List<Role> roles = new ArrayList<>();

    /**
     * Instantiates a new User dto.
     *
     * @param user the user
     */
    public UserDTO(User user) {
        if (user != null) {
            this.id = user.getId();
            this.login = user.getLogin();
            this.lastName = user.getLastName();
            this.firstName = user.getFirstName();
            this.middleName = user.getLastName();
            this.domain = user.getDomain();
            this.roles = user.getRoles();
        }
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
     * @param domain the domain
     * @return the domain
     */
    public UserDTO setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    /**
     * Instantiates a new User dto.
     */
    public UserDTO() {
    }

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
     * @return this
     */
    public UserDTO setId(Long id) {
        this.id = id;
        return this;
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
     * @param login the login
     */
    public UserDTO setLogin(String login) {
        this.login = login;
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
     * @param lastName the last name
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
     * @param firstName the first name
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
     * @param middleName the middle name
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
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
     * @param pwd the pwd
     */
    public UserDTO setPwd(String pwd) {
        this.pwd = pwd;
        return this;
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
     * @param roles the roles
     */
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id)
                && Objects.equals(login, userDTO.login)
                && Objects.equals(lastName, userDTO.lastName)
                && Objects.equals(firstName, userDTO.firstName)
                && Objects.equals(middleName, userDTO.middleName)
                && Objects.equals(pwd, userDTO.pwd)
                && Objects.equals(domain, userDTO.domain)
                && Objects.equals(roles, userDTO.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, lastName, firstName, middleName, pwd, domain, roles);
    }

    @Override
    public String toString() {
        return "UserDTO{"
                + "id=" + id
                + ", login='" + login + '\''
                + ", lastName='" + lastName + '\''
                + ", firstName='" + firstName + '\''
                + ", middleName='" + middleName + '\''
                + ", pwd='" + pwd + '\''
                + ", url='" + domain + '\''
                + ", roles=" + roles
                + '}';
    }
}
