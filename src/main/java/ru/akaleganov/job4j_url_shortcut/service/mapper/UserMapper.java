package ru.akaleganov.job4j_url_shortcut.service.mapper;

import org.springframework.stereotype.Service;
import ru.akaleganov.job4j_url_shortcut.domain.User;
import ru.akaleganov.job4j_url_shortcut.service.dto.UserDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * маппер для пользователей
 */
@Service
public class UserMapper {

    public List<UserDTO> usersToUsersDTOs(List<User> users) {
        return users.stream()
                .filter(Objects::nonNull)
                .map(this::userToUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO userToUserDTO(User user) {
        return new UserDTO(user);
    }

    public List<User> UsersDTOsToUsers(List<UserDTO> UsersDTOs) {
        return UsersDTOs.stream()
                .filter(Objects::nonNull)
                .map(this::userDTOToUser)
                .collect(Collectors.toList());
    }

    public User userDTOToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        } else {
            User user = new User();
            user.setId(userDTO.getId());
            user.setLogin(userDTO.getLogin());
            user.setFirstName(userDTO.getFirstName());
            user.setMiddleName(userDTO.getMiddleName());
            user.setLastName(userDTO.getLastName());
            user.setRoles(userDTO.getRoles());
            user.setUrl(userDTO.getUrl());
            user.setPwd(userDTO.getPwd());
            return user;
        }
    }
}
