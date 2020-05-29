package ru.akaleganov.job4j_url_shortcut.service;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.akaleganov.job4j_url_shortcut.domain.User;
import ru.akaleganov.job4j_url_shortcut.repository.UserRepository;
import ru.akaleganov.job4j_url_shortcut.service.dto.UserDTO;
import ru.akaleganov.job4j_url_shortcut.service.mapper.UserMapper;

import java.util.function.Supplier;

/**
 * сервис для рефакторинга и подготовки пользователя для сохранения
 */
@Service
public class PrepareUserService {
    private final UrlService urlService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;
    private static final Logger LOGGER = Logger.getLogger(PrepareUserService.class);

    public PrepareUserService(UrlService urlService, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserMapper userMapper) {
        this.urlService = urlService;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
    }

    /**
     * сохранение  пользователя в бд
     *
     * @param userDTO  url
     * @param save функция сохранения которую надо реализовать
     * @return {@link UserDTO}
     */
    protected UserDTO prepareUserToSave(UserDTO userDTO, Supplier<UserDTO> save) {
        if (!userDTO.getLogin().equals(this.findUserByLogin(userDTO.getLogin()).getLogin())) {
            if (this.urlService.isValidUrl(userDTO.getUrl())) {
                if (this.userRepository.findByUrl(userDTO.getUrl()).isPresent()) {
                    return new UserDTO().setErrorMessage("url  " + userDTO.getUrl() + " уже занят");
                } else {
                    return save.get();
                }
            } else {
                return new UserDTO().setErrorMessage("url не прошёл валидацию");
            }
        } else {
            return new UserDTO().setErrorMessage("пользователь с логином " + userDTO.getLogin() + " в базе уже существует");
        }

    }

    /**
     * подготовка пользователя к обновлению (проверка паролей)
     *
     * @param userDTO {@link UserDTO}
     * @return {@link User}
     */
    protected User prepareUser(UserDTO userDTO) {
        if (userDTO.getPwd() != null && !userDTO.getPwd().equals("")) {
            return this.encode(userDTO);
        } else {
            userDTO.setPwd(this.findUserByLogin(userDTO.getLogin()).getPwd());
            return this.userMapper.userDTOToUser(userDTO);
        }

    }

    /**
     * @param userDTO {@link UserDTO}
     * @return {@link User}
     */
    protected User encode(UserDTO userDTO) {
        if (userDTO.getPwd() != null) {
            userDTO.setPwd(this.bCryptPasswordEncoder.encode(userDTO.getPwd()));
        }
        LOGGER.info(userDTO.toString());
        return this.userMapper.userDTOToUser(userDTO);
    }

    /**
     * получить пользователя по логину
     *
     * @param login логин пользователя
     * @return {@link User}
     */
    private User findUserByLogin(String login) {
        return this.userRepository.findByLogin(login).orElse(new User());
    }

}
