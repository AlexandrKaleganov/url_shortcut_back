package ru.akaleganov.urlshortcut.service.url.urlvalidimpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.service.dto.UrlDTO;
import ru.akaleganov.urlshortcut.service.url.ValidUrlService;

/**
 * Class UrlValidServiceIsValidDomain
 * содержит ли урл домен пользователя
 *
 * @author Kaleganov Alexander
 * @since 31 окт. 20
 */
@Service
@AllArgsConstructor
public class ValidUrlServiceByUser implements ValidUrlService {
    /**
     * содержит ли урл домен пользователя
     *
     * @param url  {@link ru.akaleganov.urlshortcut.domain.Url}
     * @param user {@link User}
     * @return {@link UrlDTO#getErrorMessage()} == null or {@link UrlDTO#getErrorMessage()} != null
     */
    public UrlDTO isValid(String url, User user) {
        return url.contains(user.getDomain())
                ? new UrlDTO() : new UrlDTO().setErrorMessage(
                "адрес не содержит домена пользователя");
    }
}
