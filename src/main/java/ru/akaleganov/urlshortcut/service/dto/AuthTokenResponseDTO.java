package ru.akaleganov.urlshortcut.service.dto;

import ru.akaleganov.urlshortcut.domain.User;

import java.util.List;

/**
 * ответ когда пользователь авторизован
 */
public class AuthTokenResponseDTO {
    /**
     * токен
     */
    private String jwtToken;
    /**
     * имя пользователя
     */
    private String username;
    private String domain;
    private List<String> roles;

    /**
     * Instantiates a new Auth token response dto.
     */
    public AuthTokenResponseDTO() {
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
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets roles.
     *
     * @return the roles
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * Sets roles.
     *
     * @param roles the roles
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    /**
     * Instantiates a new Auth token response dto.
     *
     * @param jwtToken the jwt token
     * @param user     the user
     */
    public AuthTokenResponseDTO(String jwtToken, User user) {
        this.jwtToken = jwtToken;
        this.domain = user.getDomain();
    }

    /**
     * Gets jwt token.
     *
     * @return the jwt token
     */
    public String getJwtToken() {
        return jwtToken;
    }

    /**
     * Sets jwt token.
     *
     * @param jwtToken the jwt token
     */
    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

}
