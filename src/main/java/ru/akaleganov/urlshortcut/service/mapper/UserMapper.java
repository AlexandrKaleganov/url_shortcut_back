package ru.akaleganov.urlshortcut.service.mapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.service.dto.UserDTO;

/**
 * маппер для пользователей
 */
@Service
public class UserMapper {

    /**
     * Users to users dt os list.
     *
     * @param users
     *         the users
     *
     * @return the list
     */
    public List<UserDTO> usersToUsersDTOs(List<User> users) {
        return users.stream()
                    .filter(Objects::nonNull)
                    .map(this::userToUserDTO)
                    .collect(Collectors.toList());
    }

    /**
     * User to user dto user dto.
     *
     * @param user
     *         the user
     *
     * @return the user dto
     */
    public UserDTO userToUserDTO(User user) {
        return new UserDTO(user);
    }

    /**
     * Users dt os to users list.
     *
     * @param userDTOS
     *         the users dt os
     *
     * @return the list
     */
    public List<User> usersDTOsToUsers(List<UserDTO> userDTOS) {
        return userDTOS.stream()
                       .filter(Objects::nonNull)
                       .map(this::userDTOToUser)
                       .collect(Collectors.toList());
    }

    /**
     * User dto to user user.
     *
     * @param userDTO
     *         the user dto
     *
     * @return the user
     */
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
            user.setDomain(userDTO.getDomain());
            user.setPwd(userDTO.getPwd());
            return user;
        }
    }

}
