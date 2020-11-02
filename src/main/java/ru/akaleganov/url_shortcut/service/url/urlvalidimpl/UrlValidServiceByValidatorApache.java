package ru.akaleganov.url_shortcut.service.url.urlvalidimpl;

import lombok.AllArgsConstructor;
import org.apache.commons.validator.UrlValidator;
import org.springframework.stereotype.Service;
import ru.akaleganov.url_shortcut.domain.User;
import ru.akaleganov.url_shortcut.service.dto.UrlDTO;
import ru.akaleganov.url_shortcut.service.url.ValidService;

/**
 * Class UrlValidServiceIsValidDomain
 * реализация для проверки валидности домена
 *
 * @author Kaleganov Alexander
 * @since 31 окт. 20
 */
@Service
@AllArgsConstructor
public class UrlValidServiceByValidatorApache implements ValidService {
    private final UrlValidator urlValidator;

    /**
     * проверка валидности урл
     *
     * @param url  {@link ru.akaleganov.url_shortcut.domain.Url}
     * @param user {@link User}
     * @return тру если валидна
     */
    public UrlDTO isValid(String url, User user) {
        return urlValidator.isValid(url)? new UrlDTO():
                new UrlDTO().setErrorMessage("url не прошёл валидацию сервисом UrlValidator");
    }
}
