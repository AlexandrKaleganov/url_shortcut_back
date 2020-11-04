package ru.akaleganov.urlshortcut.service.url.urlvalidimpl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.service.dto.UrlDTO;
import ru.akaleganov.urlshortcut.service.url.ValidService;

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
        return user.getDomain() != null ? new UrlDTO() : new UrlDTO()
                .setErrorMessage("у данного пользователя отсутствует домен, "
                        + "отредактируйте пользователя в меню - Пользователи - редактировать");
    }
}
