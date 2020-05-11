package ru.akaleganov.job4j_url_shortcut.service.dto;

import ru.akaleganov.job4j_url_shortcut.domain.Roles;
import ru.akaleganov.job4j_url_shortcut.domain.Users;

import java.util.ArrayList;
import java.util.List;

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
}
