package ru.akaleganov.url_shortcut.service.url.urlvalidimpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.akaleganov.url_shortcut.domain.User;
import ru.akaleganov.url_shortcut.service.dto.UrlDTO;
import ru.akaleganov.url_shortcut.service.url.ValidService;

/**
 * Class UrlValidServiceIsValidDomain
 * содержит ли урл домен пользователя
 *
 * @author Kaleganov Alexander
 * @since 31 окт. 20
 */
@Service
@AllArgsConstructor
public class ValidServiceByUser implements ValidService {
    /**
     * содержит ли урл домен пользователя
     *
     * @param url  {@link ru.akaleganov.url_shortcut.domain.Url}
     * @param user {@link User}
     * @return если нет то вернёт фальш
     */
    public UrlDTO isValid(String url, User user) {
        return url.contains(new StringBuilder("https://").append(user.getDomain()))?
                new UrlDTO(): new UrlDTO().setErrorMessage(
                        "адрес не содержит домена пользователя");
    }
}
