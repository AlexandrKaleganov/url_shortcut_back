package ru.akaleganov.urlshortcut.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.akaleganov.urlshortcut.service.dto.UserDTO;

/**
 * The type User service.
 */
public interface UserService {

    /**
     * получить всех пользаков c пагинацией
     *
     * @param criteria
     *         the criteria
     * @param pageable
     *         пагинация
     *
     * @return {@link UserDTO}
     */
    Page<UserDTO> findAll(UserFilter criteria, Pageable pageable);

    /**
     * добавление пользователя по url
     *
     * @param url
     *         ссылка на домен, каждый пользователь может дбавить только один домен
     *
     * @return {@link UserDTO}
     */
    UserDTO createUsersByUrl(String url);

    /**
     * создание пользователя через админку
     *
     * @param userDTO
     *         {@link UserDTO}
     *
     * @return {@link UserDTO}
     */
    UserDTO create(UserDTO userDTO);

    /**
     * получить пользователя по логину
     *
     * @param login
     *         логин пользователя
     *
     * @return {@link UserDTO}
     */
    UserDTO getUserByLogin(String login);

    /**
     * обновление данных текущего пользователя
     *
     * @param userDTO
     *         {@link UserDTO}
     *
     * @return {@link UserDTO}
     */
    UserDTO updateUser(UserDTO userDTO);

}
