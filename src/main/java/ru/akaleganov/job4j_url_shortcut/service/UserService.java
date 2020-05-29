package ru.akaleganov.job4j_url_shortcut.service;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akaleganov.job4j_url_shortcut.domain.Role;
import ru.akaleganov.job4j_url_shortcut.domain.User;
import ru.akaleganov.job4j_url_shortcut.repository.UserRepository;
import ru.akaleganov.job4j_url_shortcut.service.dto.UserDTO;
import ru.akaleganov.job4j_url_shortcut.service.dto.UserFilter;
import ru.akaleganov.job4j_url_shortcut.service.mapper.UserMapper;
import ru.akaleganov.job4j_url_shortcut.service.util.RandomGeneratorLoginPass;

import java.util.Collections;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;
    private final RandomGeneratorLoginPass randomGeneratorLoginPass;
    private static final Logger LOGGER = Logger.getLogger(UserService.class);
    private final PrepareUserService prepareUserService;

    UserService(UserRepository userRepository,
                BCryptPasswordEncoder encoder, UserMapper userMapper, RandomGeneratorLoginPass randomGeneratorLoginPass,
                PrepareUserService prepareUserService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = encoder;
        this.userMapper = userMapper;
        this.randomGeneratorLoginPass = randomGeneratorLoginPass;
        this.prepareUserService = prepareUserService;
    }

    /**
     * получить всех пользаков c пагинацией  (гадо пдумать как реализовать)
     *
     * @param pageable пагинация
     * @return {@link UserDTO}
     */
    public Page<UserDTO> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable).map(this.userMapper::userToUserDTO);
    }

    /**
     * получить всех пользаков c пагинацией  (гадо пдумать как реализовать)
     *
     * @param pageable пагинация
     * @return {@link UserDTO}
     */
    public Page<UserDTO> findAll(UserFilter criteria, Pageable pageable) {
        return this.userRepository.findAll(criteria.buildCriteria(), pageable).map(this.userMapper::userToUserDTO);
    }
    /**
     * добавление пользователя по url
     *
     * @param url ссылка на домен, каждый пользователь может дбавить только один домен
     * @return {@link UserDTO}
     */
    public UserDTO createUsersByUrl(String url) {
        return this.prepareUserService.saveUser(url, () -> {
            User users = new User();
            users.setUrl(url);
            users.setRoles(Collections.singletonList(new Role(2L)));
            users.setLogin(this.randomGeneratorLoginPass.generateLogin());
            users.setPwd(this.randomGeneratorLoginPass.generatePassword());
            UserDTO result = this.userMapper.userToUserDTO(users);
            result.setPwd(users.getPwd());
            users.setPwd(this.bCryptPasswordEncoder.encode(users.getPwd()));
            this.userRepository.save(users);
            result.setId(users.getId());
            return result;
        });
    }

    /**
     * создание пользователя через админку
     *
     * @param userDTO {@link UserDTO}
     * @return {@link UserDTO}
     */
    public UserDTO create(UserDTO userDTO) {
        return this.prepareUserService.saveUser(userDTO.getUrl(), () ->{
            User us = this.prepareUserService.encode(userDTO);
            LOGGER.info("us = " + us.toString());
            User res  = this.userRepository.save(us);
            LOGGER.info("res = " + res.toString());
            return this.userMapper.userToUserDTO(res);
                }

        );
    }

    /**
     * получить пользователя по логину
     *
     * @param login логин пользователя
     * @return {@link UserDTO}
     */
    public UserDTO getUserByLogin(String login) {
        return this.userMapper.userToUserDTO(this.userRepository.findByLogin(login).orElse(new User()));
    }

    /**
     * обновление данных текущего пользователя
     *
     * @param userDTO {@link UserDTO}
     * @return {@link UserDTO}
     */
    public UserDTO updateUser(UserDTO userDTO) {
        User oldUSer = this.userRepository.findByLogin(userDTO.getLogin()).orElse(new User());
        if (!oldUSer.getUrl().equals(userDTO.getUrl())) {
            LOGGER.info("oldUSer обнова " + oldUSer);
            LOGGER.info("userDTO обнова " + userDTO);
            return this.prepareUserService.saveUser(userDTO.getUrl(), () -> this.userMapper.userToUserDTO(this.userRepository.save(
                    this.prepareUserService.prepareUser(userDTO))));
        } else {
            return this.userMapper.userToUserDTO(this.userRepository.save(
                    this.prepareUserService.prepareUser(userDTO)));
        }
    }

    /**
     * тестирование транзакций
     * @param lastName новое отчество
     * @param id пользователя, которому надо изменить отчество
     */
    @Transactional
    public void setLastName(String lastName, Long id) {
        User user = this.userRepository.findById(id).get();
        user.setLastName(lastName);
    }
}
