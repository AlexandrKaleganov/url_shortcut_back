package ru.akaleganov.urlshortcut.service.user;

import org.springframework.stereotype.Service;
import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.service.dto.UserDTO;

/**
 * The type Url valid service.
 */
public interface ValidUserService {

    /**
     * проверка пользователя перед созранением
     *
     * @return true если валидный, false   если нет
     */
     UserDTO isValid(UserDTO userDTO);

}
