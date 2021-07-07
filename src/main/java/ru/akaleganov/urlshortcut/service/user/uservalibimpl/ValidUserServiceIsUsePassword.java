package ru.akaleganov.urlshortcut.service.user.uservalibimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.repository.UserRepository;
import ru.akaleganov.urlshortcut.service.dto.UserDTO;
import ru.akaleganov.urlshortcut.service.user.ValidUserService;

/**
 * Class ValidUserServiceIsUserByLogininData валидация и хеширование пароля
 *
 * @author Kaleganov Alexander
 * @since 08 нояб. 20
 */
@Component
@RequiredArgsConstructor
public class ValidUserServiceIsUsePassword implements ValidUserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * проверяет есть ли у ходящего пользователя пароль если есть то хеширует его, если нет то срабатывает логика:
     * обновление ползователя сначит необходимо получить пользователя из бд по логину и вставить его пароль в текущего
     *
     * @param userDTO
     *         {@link UserDTO}
     *
     * @return {@link UserDTO#getErrorMessage()} == null or {@link UserDTO#getErrorMessage()} !=null
     */
    @Override
    public UserDTO isValid(UserDTO userDTO) {
        if (userDTO.getPwd() != null && !userDTO.getPwd().equals("")) {
            userDTO.setPwd(this.bCryptPasswordEncoder.encode(userDTO.getPwd()));
        } else {
            User res = this.userRepository.findByLogin(userDTO.getLogin()).orElse(new User());
            if (res.getId() != null) {
                userDTO.setPwd(res.getPwd());
            } else {
                userDTO.setErrorMessage(
                        "Не удалось обновить пользователя, с логином " + userDTO.getLogin()
                                + " пользователь отсутствует в бд");
            }
        }
        return userDTO;
    }

}
