package ru.akaleganov.urlshortcut.service.url.urlvalidimpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.repository.UrlRepository;
import ru.akaleganov.urlshortcut.service.dto.UrlDTO;
import ru.akaleganov.urlshortcut.service.url.ValidService;

/**
 * Class UrlValidServiceIsValidDomain
 * реализация для проверки содержится ли урл в бд
 *
 * @author Kaleganov Alexander
 * @since 31 окт. 20
 */
@Service
@AllArgsConstructor
public class ValidServiceIsPresentInDataBase implements ValidService {
    private final UrlRepository urlRepository;

    /**
     * содержит ли урл в бд
     *
     * @param url  {@link ru.akaleganov.urlshortcut.domain.Url}
     * @param user {@link User}
     * @return если нет то вернёт фальш
     */
    public UrlDTO isValid(String url, User user) {
        return this.urlRepository.findAllByOrigin(url).size() > 0
                ? new UrlDTO().setErrorMessage(
                        "Url уже содержится в бд") : new UrlDTO();
    }
}
