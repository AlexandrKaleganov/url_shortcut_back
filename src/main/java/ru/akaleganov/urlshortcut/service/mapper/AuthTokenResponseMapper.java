package ru.akaleganov.urlshortcut.service.mapper;

import org.springframework.stereotype.Service;
import ru.akaleganov.urlshortcut.domain.Role;
import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.service.dto.AuthTokenResponseDTO;

import java.util.stream.Collectors;

/**
 * The type Auth token response mapper.
 */
@Service
public class AuthTokenResponseMapper {

    /**
     * To dto auth token response dto.
     *
     * @param jwtToken the jwt token
     * @param users    the users
     * @return the auth token response dto
     */
    public AuthTokenResponseDTO toDTO(String jwtToken, User users) {
        AuthTokenResponseDTO authTokenResponseDTO = new AuthTokenResponseDTO();
        authTokenResponseDTO.setJwtToken(jwtToken);
        authTokenResponseDTO.setRoles(users.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        authTokenResponseDTO.setUsername(users.getLogin());
        authTokenResponseDTO.setDomain(users.getDomain());
        return authTokenResponseDTO;
    }
}
