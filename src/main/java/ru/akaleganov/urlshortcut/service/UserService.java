package ru.akaleganov.urlshortcut.service;

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
import ru.akaleganov.urlshortcut.service.util.RandomGenerator;

import java.util.Collections;

/**
 * The type User service.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;
    private final RandomGenerator randomGenerator;
    private final PrepareUserService prepareUserService;

    /**
     * Instantiates a new User service.
     *
     * @param userRepository     the user repository
     * @param encoder            the encoder
     * @param userMapper         the user mapper
     * @param randomGenerator    the random generator
     * @param prepareUserService the prepare user service
     */
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
     * получить всех пользаков c пагинацией
     *
     * @param criteria the criteria
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
    @Transactional
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
    @Transactional
    public UserDTO create(UserDTO userDTO) {
        return this.prepareUserService.prepareUserToSave(userDTO, () -> {
                    User us = this.prepareUserService.encode(userDTO);
                    User res = this.userRepository.save(us);
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
        if (oldUSer.getDomain() == null || !oldUSer.getDomain().equals(userDTO.getDomain())) {
            return this.prepareUserService.prepareToUpdate(userDTO, () ->
                    this.userMapper.userToUserDTO(this.userRepository.save(
                            this.prepareUserService.prepareUser(userDTO))));
        } else {
            return this.userMapper.userToUserDTO(this.userRepository.save(
                    this.prepareUserService.prepareUser(userDTO)));
        }
    }

}