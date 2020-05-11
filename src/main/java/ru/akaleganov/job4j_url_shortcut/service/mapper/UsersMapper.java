package ru.akaleganov.job4j_url_shortcut.service.mapper;

import org.springframework.stereotype.Service;
import ru.akaleganov.job4j_url_shortcut.domain.Users;
import ru.akaleganov.job4j_url_shortcut.service.dto.UsersDTO;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * маппер для пользователей
 */
@Service
public class UsersMapper {

    public List<UsersDTO> usersToUsersDTOs(List<Users> users) {
        return users.stream()
                .filter(Objects::nonNull)
                .map(this::usersToUsersDTO)
                .collect(Collectors.toList());
    }

    public UsersDTO usersToUsersDTO(Users user) {
        return new UsersDTO(user);
    }

    public List<Users> UsersDTOsToUsers(List<UsersDTO> UsersDTOs) {
        return UsersDTOs.stream()
                .filter(Objects::nonNull)
                .map(this::UsersDTOToUser)
                .collect(Collectors.toList());
    }

    public Users UsersDTOToUser(UsersDTO usersDTO) {
        if (usersDTO == null) {
            return null;
        } else {
            Users user = new Users();
            user.setId(usersDTO.getId());
            user.setLogin(usersDTO.getLogin());
            user.setFirstName(usersDTO.getFirstName());
            user.setMiddleName(usersDTO.getMiddleName());
            user.setLastName(usersDTO.getLastName());
            user.setRoles(usersDTO.getRoles());
            return user;
        }
    }

}
