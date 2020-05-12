package ru.akaleganov.job4j_url_shortcut.service.dto;

import ru.akaleganov.job4j_url_shortcut.domain.Roles;
import ru.akaleganov.job4j_url_shortcut.domain.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ДТО для ответа и для изменений данных пользователя
 */
public class UsersDTO {
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

    public UsersDTO setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    private List<Roles> roles = new ArrayList<>();

    public UsersDTO(Users users) {
        this.login = users.getLogin();
        this.lastName = users.getLastName();
        this.firstName = users.getFirstName();
        this.middleName = users.getLastName();
        this.url = users.getUrl();
        this.roles = users.getRoles();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public UsersDTO() {
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

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersDTO usersDTO = (UsersDTO) o;
        return Objects.equals(id, usersDTO.id) &&
                Objects.equals(login, usersDTO.login) &&
                Objects.equals(lastName, usersDTO.lastName) &&
                Objects.equals(firstName, usersDTO.firstName) &&
                Objects.equals(middleName, usersDTO.middleName) &&
                Objects.equals(pwd, usersDTO.pwd) &&
                Objects.equals(url, usersDTO.url) &&
                Objects.equals(roles, usersDTO.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, lastName, firstName, middleName, pwd, url, roles);
    }

    @Override
    public String toString() {
        return "UsersDTO{" +
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
