package ru.akaleganov.job4j_url_shortcut.service.dto;

import ru.akaleganov.job4j_url_shortcut.domain.Role;
import ru.akaleganov.job4j_url_shortcut.domain.User;

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
    private String url;
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public UserDTO setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    private List<Role> roles = new ArrayList<>();

    public UserDTO(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.lastName = user.getLastName();
        this.firstName = user.getFirstName();
        this.middleName = user.getLastName();
        this.url = user.getUrl();
        this.roles = user.getRoles();
    }

    public String getUrl() {
        return url;
    }

    public UserDTO setUrl(String url) {
        this.url = url;
        return this;
    }

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) &&
                Objects.equals(login, userDTO.login) &&
                Objects.equals(lastName, userDTO.lastName) &&
                Objects.equals(firstName, userDTO.firstName) &&
                Objects.equals(middleName, userDTO.middleName) &&
                Objects.equals(pwd, userDTO.pwd) &&
                Objects.equals(url, userDTO.url) &&
                Objects.equals(roles, userDTO.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, lastName, firstName, middleName, pwd, url, roles);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", url='" + url + '\'' +
                ", roles=" + roles +
                '}';
    }
}
