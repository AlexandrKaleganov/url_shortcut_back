package ru.akaleganov.url_shortcut.service.url;

import ru.akaleganov.url_shortcut.domain.User;
import ru.akaleganov.url_shortcut.service.dto.UrlDTO;

/**
 * Class UrlValidService
 * интерфей для валидации урла
 *
 * @author Kaleganov Alexander
 * @since 31 окт. 20
 */
public interface ValidService {
    UrlDTO isValid(String url, User user);
}
