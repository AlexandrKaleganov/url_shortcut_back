package ru.akaleganov.job4j_url_shortcut.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akaleganov.job4j_url_shortcut.domain.Role;
import ru.akaleganov.job4j_url_shortcut.domain.User;
import ru.akaleganov.job4j_url_shortcut.repository.UserRepository;
import ru.akaleganov.job4j_url_shortcut.service.dto.UserDTO;
import ru.akaleganov.job4j_url_shortcut.service.mapper.UserMapper;
import ru.akaleganov.job4j_url_shortcut.service.util.RandomGenerator;

import java.util.Collections;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;
    private final RandomGenerator randomGenerator;
    private final PrepareUserService prepareUserService;

    UserService(UserRepository userRepository,
                BCryptPasswordEncoder encoder, UserMapper userMapper, RandomGenerator randomGenerator,
                PrepareUserService prepareUserService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = encoder;
        this.userMapper = userMapper;
        this.randomGenerator = randomGenerator;
        this.prepareUserService = prepareUserService;
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
        UserDTO userDTO = new UserDTO().setDomain(url);
        userDTO.setLogin(this.randomGenerator.generateLogin());
        userDTO.setPwd(this.randomGenerator.generatePassword());
        userDTO.setRoles(Collections.singletonList(new Role(2L)));
        return this.prepareUserService.prepareUserToSave(userDTO, () -> {
            User user = this.userMapper.userDTOToUser(userDTO);
            user.setPwd(this.bCryptPasswordEncoder.encode(user.getPwd()));
            this.userRepository.save(user);
            userDTO.setId(user.getId());
            return userDTO;
        });
    }

    /**
     * создание пользователя через админку
     *
     * @param userDTO {@link UserDTO}
     * @return {@link UserDTO}
     */
    public UserDTO create(UserDTO userDTO) {
        return this.prepareUserService.prepareUserToSave(userDTO, () ->{
            User us = this.prepareUserService.encode(userDTO);
            User res  = this.userRepository.save(us);
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
        if (!oldUSer.getDomain().equals(userDTO.getDomain())) {
            return this.prepareUserService.prepareUserToSave(userDTO, () -> this.userMapper.userToUserDTO(this.userRepository.save(
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
