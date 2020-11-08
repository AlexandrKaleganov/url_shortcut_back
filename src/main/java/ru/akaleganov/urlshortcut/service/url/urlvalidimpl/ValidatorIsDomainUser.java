package ru.akaleganov.urlshortcut.service.url.urlvalidimpl;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.service.dto.UrlDTO;
import ru.akaleganov.urlshortcut.service.url.ValidUrlService;

/**
 * Class ValidatorIsDomainUser
 * проверяет есть ли уданного пользователя домен
 *
 * @author Kaleganov Alexander
 * @since 01 нояб. 20
 */
@Service
@Order(1)
public class ValidatorIsDomainUser implements ValidUrlService {
    /**
     * проверяет есть ли уданного пользователя домен
     * если нету у пользователя домена то нету смысла добавлять
     *
     * @param url  {приходит при добавлении урла}
     * @param user {@link User}
     * @return {@link UrlDTO#getErrorMessage()} == null or {@link UrlDTO#getErrorMessage()} != null
     */
    @Override
    public UrlDTO isValid(String url, User user) {
        return user.getDomain() != null ? new UrlDTO()
                : new UrlDTO().setErrorMessage("у данного пользователя отсутствует домен, "
                + "отредактируйте пользователя в меню - Пользователи - редактировать");
    }
}
