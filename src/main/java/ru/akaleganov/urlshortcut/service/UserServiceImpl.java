package ru.akaleganov.urlshortcut.service;

import java.util.Collections;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akaleganov.urlshortcut.domain.Role;
import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.repository.UserRepository;
import ru.akaleganov.urlshortcut.service.dto.UserDTO;
import ru.akaleganov.urlshortcut.service.mapper.UserMapper;
import ru.akaleganov.urlshortcut.service.user.PrepareUserServiceImpl;
import ru.akaleganov.urlshortcut.service.util.RandomGenerator;

/**
 * The type User service.
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserMapper userMapper;

    private final RandomGenerator randomGenerator;

    private final PrepareUserServiceImpl prepareUserServices;

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
    public Page<UserDTO> findAll(UserFilter criteria, Pageable pageable) {
        return this.userRepository.findAll(criteria.buildCriteria(), pageable).map(this.userMapper::userToUserDTO);
    }

    /**
     * добавление пользователя по url сгенерируем пользователя и при возврате засетаем туда пароль сгенерированный
     *
     * @param url
     *         ссылка на домен, каждый пользователь может дбавить только один домен
     *
     * @return {@link UserDTO}
     */
    @Transactional(rollbackFor = Exception.class)
    public UserDTO createUsersByUrl(String url) {
        UserDTO userDTO = new UserDTO().setDomain(url);
        userDTO.setLogin(this.randomGenerator.generateLogin());
        String generatePass = this.randomGenerator.generatePassword();
        userDTO.setPwd(generatePass);
        userDTO.setRoles(Collections.singletonList(new Role(2L)));
        userDTO = this.prepareUserServices.prepareUserToSave(userDTO);
        return userDTO.getErrorMessage() != null ? userDTO : this.save(userDTO).setPwd(generatePass);
    }

    /**
     * Save user dto.
     *
     * @param userDTO
     *         the user dto
     *
     * @return the user dto
     */
    public UserDTO save(UserDTO userDTO) {
        User user = this.userMapper.userDTOToUser(userDTO);
        LOGGER.info("сохраняем пользователя = ");
        LOGGER.info(user.toString());
        return userDTO.setId(this.userRepository.save(user).getId());
    }

    /**
     * создание пользователя через админку
     *
     * @param userDTO
     *         {@link UserDTO}
     *
     * @return {@link UserDTO}
     */
    @Transactional(rollbackFor = Exception.class)
    public UserDTO create(UserDTO userDTO) {
        UserDTO result = this.prepareUserServices.prepareUserToSave(userDTO);
        return result.getErrorMessage() != null ? result : this.save(result);
    }

    /**
     * получить пользователя по логину
     *
     * @param login
     *         логин пользователя
     *
     * @return {@link UserDTO}
     */
    public UserDTO getUserByLogin(String login) {
        return this.userMapper.userToUserDTO(this.userRepository.findByLogin(login).orElse(new User()));
    }

    /**
     * обновление данных текущего пользователя
     *
     * @param userDTO
     *         {@link UserDTO}
     *
     * @return {@link UserDTO}
     */
    public UserDTO updateUser(UserDTO userDTO) {
        return this.create(userDTO);
    }

}
