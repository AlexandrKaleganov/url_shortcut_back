package ru.akaleganov.urlshortcut.service.user.uservalibimpl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.akaleganov.urlshortcut.service.dto.UserDTO;
import ru.akaleganov.urlshortcut.service.user.ValidUserService;

/**
 * The type Url valid service.
 */
@Component
@Order(1)
public class ValidUserServiceIsDomainValid implements ValidUserService {

    /**
     * проверка домен на валидность
     *
     * @return {@link UserDTO#getErrorMessage()} == null or {@link UserDTO#getErrorMessage()} !=null
     */
    public UserDTO isValid(UserDTO userDTO) {
        return userDTO.getDomain() != null
                && userDTO.getDomain().matches("^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$")
                ? userDTO : userDTO.setErrorMessage("Домен не прошёл валидацию");
    }
}
