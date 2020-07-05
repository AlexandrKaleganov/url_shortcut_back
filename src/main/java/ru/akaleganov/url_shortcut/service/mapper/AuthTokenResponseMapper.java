package ru.akaleganov.url_shortcut.service.mapper;

import org.springframework.stereotype.Service;
import ru.akaleganov.url_shortcut.domain.Role;
import ru.akaleganov.url_shortcut.domain.User;
import ru.akaleganov.url_shortcut.service.dto.AuthTokenResponseDTO;

import java.util.stream.Collectors;

@Service
public class AuthTokenResponseMapper {

    public AuthTokenResponseDTO toDTO(String jwtToken, User users) {
        AuthTokenResponseDTO authTokenResponseDTO = new AuthTokenResponseDTO();
        authTokenResponseDTO.setJwtToken(jwtToken);
        authTokenResponseDTO.setRoles(users.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        authTokenResponseDTO.setUsername(users.getLogin());
        authTokenResponseDTO.setDomain(users.getDomain());
        return authTokenResponseDTO;
    }
}
