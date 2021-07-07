package ru.akaleganov.urlshortcut.service.url;

import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.service.dto.UrlDTO;

/**
 * Class PrepareUrlService интерфейс для подготовки длинного урла к сохранению 🚀🚀🚀 💥💥💥
 *
 * @author Kaleganov Alexander
 * @since 31 окт. 20
 */
public interface PrepareUrlService {

    /**
     * Prepare to save url dto.
     *
     * @param url
     *         новый урл для сохранения
     * @param userLogin
     *         {@link User#getLogin()}
     *
     * @return {@link UrlDTO} возможно будет пустым и с заполненным еррором
     */
    UrlDTO prepareToSave(String url, String userLogin);

}
