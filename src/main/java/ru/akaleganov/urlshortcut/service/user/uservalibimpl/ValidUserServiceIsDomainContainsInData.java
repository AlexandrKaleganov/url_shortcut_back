package ru.akaleganov.urlshortcut.service.user.uservalibimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.repository.UserRepository;
import ru.akaleganov.urlshortcut.service.dto.UserDTO;
import ru.akaleganov.urlshortcut.service.user.ValidUserService;

/**
 * Class ValidUserServiceIsUserByLogininData есть  ли пользователь проверим есть ли домен пользователя в бд
 *
 * @author Kaleganov Alexander
 * @since 08 нояб. 20
 */
@Component
@RequiredArgsConstructor
public class ValidUserServiceIsDomainContainsInData implements ValidUserService {

    private final UserRepository userRepository;

    /**
     * есть ли домен пользователя в бд и если есть то идёт проверка на идентичнойть id  если одинаковые то пропускаем
     * это обновление пользователя иначе засетаем ошибку
     *
     * @return {@link UserDTO#getErrorMessage()} == null or {@link UserDTO#getErrorMessage()} !=null
     */
    public UserDTO isValid(UserDTO userDTO) {
        User user = this.userRepository.findByDomain(userDTO.getDomain()).orElse(new User());
        if (user.getLogin() != null && !userDTO.getLogin().equals(user.getLogin())) {
            userDTO.setErrorMessage("Домен  " + userDTO.getDomain() + " уже занят");
        }
        return userDTO;
    }

}
