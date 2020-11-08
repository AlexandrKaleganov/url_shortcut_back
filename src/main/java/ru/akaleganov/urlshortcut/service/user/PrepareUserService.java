package ru.akaleganov.urlshortcut.service.user;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.repository.UserRepository;
import ru.akaleganov.urlshortcut.service.dto.UserDTO;
import ru.akaleganov.urlshortcut.service.mapper.UserMapper;

import java.util.List;
import java.util.function.Supplier;

/**
 * сервис для рефакторинга и подготовки пользователя для сохранения
 */
public interface PrepareUserService {
    /**
     * сохранение  пользователя в бд
     *
     * @param userDTO url
     * @return {@link UserDTO}
     */
     UserDTO prepareUserToSave(UserDTO userDTO);


    /**
     * Prepare to update user dto.
     *
     * @param userDTO the user dto
     * @param save    the save
     * @return the user dto
     */
     UserDTO prepareToUpdate(UserDTO userDTO, Supplier<UserDTO> save);

    /**
     * подготовка пользователя к обновлению (проверка паролей)
     *
     * @param userDTO {@link UserDTO}
     * @return {@link User}
     */
     User prepareUser(UserDTO userDTO);

    /**
     * Encode user.
     *
     * @param userDTO {@link UserDTO}
     * @return {@link User}
     */
     User encode(UserDTO userDTO);

}
