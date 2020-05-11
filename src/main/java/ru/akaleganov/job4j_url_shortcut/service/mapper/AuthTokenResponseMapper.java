package ru.akaleganov.job4j_url_shortcut.service.mapper;

import org.springframework.stereotype.Service;
import ru.akaleganov.job4j_url_shortcut.domain.Roles;
import ru.akaleganov.job4j_url_shortcut.domain.Users;
import ru.akaleganov.job4j_url_shortcut.service.dto.AuthTokenResponseDTO;

import java.util.stream.Collectors;

@Service
public class AuthTokenResponseMapper {

    public AuthTokenResponseDTO toDTO(String jwtToken, Users users) {
        AuthTokenResponseDTO authTokenResponseDTO = new AuthTokenResponseDTO();
        authTokenResponseDTO.setJwtToken(jwtToken);
        authTokenResponseDTO.setRoles(users.getRoles().stream().map(Roles::getName).collect(Collectors.toList()));
        authTokenResponseDTO.setUsername(users.getLogin());
        return authTokenResponseDTO;
    }
}
