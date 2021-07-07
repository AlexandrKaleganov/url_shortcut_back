package ru.akaleganov.urlshortcut.service.user;

import java.util.function.Supplier;

import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.service.dto.UserDTO;

/**
 * сервис для рефакторинга и подготовки пользователя для сохранения
 */
public interface PrepareUserService {

    /**
     * сохранение  пользователя в бд
     *
     * @param userDTO
     *         url
     *
     * @return {@link UserDTO}
     */
    UserDTO prepareUserToSave(UserDTO userDTO);

    /**
     * Prepare to update user dto.
     *
     * @param userDTO
     *         the user dto
     * @param save
     *         the save
     *
     * @return the user dto
     */
    UserDTO prepareToUpdate(UserDTO userDTO, Supplier<UserDTO> save);

    /**
     * подготовка пользователя к обновлению (проверка паролей)
     *
     * @param userDTO
     *         {@link UserDTO}
     *
     * @return {@link User}
     */
    User prepareUser(UserDTO userDTO);

    /**
     * Encode user.
     *
     * @param userDTO
     *         {@link UserDTO}
     *
     * @return {@link User}
     */
    User encode(UserDTO userDTO);

}
