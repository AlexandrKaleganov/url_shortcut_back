package ru.akaleganov.urlshortcut.service.url.urlvalidimpl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.UrlValidator;
import org.springframework.stereotype.Service;
import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.service.dto.UrlDTO;
import ru.akaleganov.urlshortcut.service.url.ValidUrlService;

/**
 * Class UrlValidServiceIsValidDomain реализация для проверки валидности домена
 *
 * @author Kaleganov Alexander
 * @since 31 окт. 20
 */
@Service
@RequiredArgsConstructor
public class ValidUrlServiceByValidatorApache implements ValidUrlService {

    private final UrlValidator urlValidator;

    /**
     * проверка валидности урл
     *
     * @param url
     *         {@link ru.akaleganov.urlshortcut.domain.Url}
     * @param user
     *         {@link User}
     *
     * @return тру если валидна
     */
    public UrlDTO isValid(String url, User user) {
        return urlValidator.isValid(url) ? new UrlDTO()
                                         : new UrlDTO().setErrorMessage(
                                                 "url не прошёл валидацию сервисом UrlValidator");
    }

}
