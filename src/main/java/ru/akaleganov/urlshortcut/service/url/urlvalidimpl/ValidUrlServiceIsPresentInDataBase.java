package ru.akaleganov.urlshortcut.service.url.urlvalidimpl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.repository.UrlRepository;
import ru.akaleganov.urlshortcut.service.dto.UrlDTO;
import ru.akaleganov.urlshortcut.service.url.ValidUrlService;

/**
 * Class UrlValidServiceIsValidDomain реализация для проверки содержится ли урл в бд
 *
 * @author Kaleganov Alexander
 * @since 31 окт. 20
 */
@Service
@RequiredArgsConstructor
public class ValidUrlServiceIsPresentInDataBase implements ValidUrlService {

    private final UrlRepository urlRepository;

    /**
     * содержит ли урл в бд
     *
     * @param url
     *         {@link ru.akaleganov.urlshortcut.domain.Url}
     * @param user
     *         {@link User}
     *
     * @return {@link UrlDTO#getErrorMessage()} == null or {@link UrlDTO#getErrorMessage()} != null
     */
    public UrlDTO isValid(String url, User user) {
        return this.urlRepository.findAllByOrigin(url).size() > 0
               ? new UrlDTO().setErrorMessage(
                "Url уже содержится в бд") : new UrlDTO();
    }

}
