package ru.akaleganov.urlshortcut.service.user;

import ru.akaleganov.urlshortcut.service.dto.UserDTO;

/**
 * The type Url valid service.
 */
public interface ValidUserService {

    /**
     * проверка пользователя перед созранением
     *
     * @param userDTO
     *         the user dto
     *
     * @return true если валидный, false   если нет
     */
    UserDTO isValid(UserDTO userDTO);

}
