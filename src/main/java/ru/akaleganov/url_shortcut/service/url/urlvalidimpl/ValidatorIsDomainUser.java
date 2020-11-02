package ru.akaleganov.url_shortcut.service.url.urlvalidimpl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import ru.akaleganov.url_shortcut.domain.User;
import ru.akaleganov.url_shortcut.service.dto.UrlDTO;
import ru.akaleganov.url_shortcut.service.dto.UserDTO;
import ru.akaleganov.url_shortcut.service.url.ValidService;

/**
 * Class ValidatorIsDomainUser
 * todo: описание
 *
 * @author Kaleganov Alexander
 * @since 01 нояб. 20
 */
@Service
@Order(1)
public class ValidatorIsDomainUser implements ValidService {
    @Override
    public UrlDTO isValid(String url, User user) {
        return user.getDomain()!=null? new UrlDTO(): new UrlDTO()
                .setErrorMessage("у данного пользователя отсутствует домен, " +
                        "отредактируйте пользователя в меню - Пользователи - редактировать");
    }
}
