package ru.akaleganov.urlshortcut.service.url;

import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.service.dto.UrlDTO;

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
