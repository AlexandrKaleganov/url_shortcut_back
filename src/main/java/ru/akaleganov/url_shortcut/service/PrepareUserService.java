package ru.akaleganov.url_shortcut.service;

import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.akaleganov.url_shortcut.domain.User;
import ru.akaleganov.url_shortcut.repository.UserRepository;
import ru.akaleganov.url_shortcut.service.dto.UserDTO;
import ru.akaleganov.url_shortcut.service.mapper.UserMapper;
import ru.akaleganov.url_shortcut.service.url.ValidDomainSevice;

import java.util.function.Supplier;

/**
 * сервис для рефакторинга и подготовки пользователя для сохранения
 */
@Service
@AllArgsConstructor
public class PrepareUserService {
    private final ValidDomainSevice urlValidService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;
    private static final Logger LOGGER = Logger.getLogger(PrepareUserService.class);

    /**
     * сохранение  пользователя в бд
     *
     * @param userDTO url
     * @param save    функция сохранения которую надо реализовать
     * @return {@link UserDTO}
     */
    protected UserDTO prepareUserToSave(UserDTO userDTO, Supplier<UserDTO> save) {
        if (!userDTO.getLogin().equals(this.findUserByLogin(userDTO.getLogin()).getLogin())) {
            return this.prepareToUpdate(userDTO, save);
        } else {
            return new UserDTO().setErrorMessage("пользователь с логином " + userDTO.getLogin() + " в базе уже существует");
        }

    }

    /**
     * Prepare to update user dto.
     *
     * @param userDTO the user dto
     * @param save    the save
     * @return the user dto
     */
    public UserDTO prepareToUpdate(UserDTO userDTO, Supplier<UserDTO> save) {
        if (this.urlValidService.isValidDomain(userDTO.getDomain())) {
            if (this.userRepository.findByDomain(userDTO.getDomain()).isPresent()) {
                return new UserDTO().setErrorMessage("Домен  " + userDTO.getDomain() + " уже занят");
            } else {
                return save.get();
            }
        } else {
            return new UserDTO().setErrorMessage("url не прошёл валидацию");
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
     * Encode user.
     *
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
