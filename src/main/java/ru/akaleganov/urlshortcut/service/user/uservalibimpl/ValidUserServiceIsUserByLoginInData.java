package ru.akaleganov.urlshortcut.service.user.uservalibimpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.repository.UserRepository;
import ru.akaleganov.urlshortcut.service.dto.UserDTO;
import ru.akaleganov.urlshortcut.service.user.ValidUserService;

/**
 * Class ValidUserServiceIsUserByLogininData
 *  есть  ли пользователь в бд
 *
 * @author Kaleganov Alexander
 * @since 08 нояб. 20
 */
@Component
@AllArgsConstructor
public class ValidUserServiceIsUserByLoginInData implements ValidUserService {
    private final UserRepository userRepository;

    /**
     * проверяет есть ли пользователь по логину в бд
     * и если есть то проверяет соответствует ли  id  если да
     * значит это просто обновление пользователя, если нет то засетаем ошибку
     *
     * @param userDTO {@link UserDTO}
     * @return {@link UserDTO#getErrorMessage()} == null or {@link UserDTO#getErrorMessage()} !=null
     */
    @Override
    public UserDTO isValid(UserDTO userDTO) {
       User res =   this.userRepository.findByLogin(userDTO.getLogin()).orElse(new User());
       if (res.getId() != null && !res.getId().equals(userDTO.getId())) {
             userDTO.setErrorMessage("пользователь с логином " + userDTO.getLogin() + " в базе уже существует");
       }
       return userDTO;
    }
}
