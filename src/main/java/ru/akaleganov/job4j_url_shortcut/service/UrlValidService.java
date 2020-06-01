package ru.akaleganov.job4j_url_shortcut.service;

import org.apache.commons.validator.UrlValidator;
import org.springframework.stereotype.Service;
import ru.akaleganov.job4j_url_shortcut.repository.UrlRepository;

@Service
public class UrlValidService {
    private final UrlRepository urlRepository;

    public UrlValidService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    /**
     * проверка url на валидность
     *
     * @param domain домен пользователя
     * @return true если валидный, false   если нет
     */
    public boolean isValidDomain(String domain) {
        return domain.matches("^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$");
    }

    /**
     * проверка валидности урл
     *
     * @param url урл
     * @return тру если валидна
     */
    public boolean isValidUrl(String url) {
        UrlValidator urlValidator = new UrlValidator();
        return urlValidator.isValid(url);
    }

    /**
     * проверка содержит ли теущий урл домен пользователя
     *
     * @param url урл
     * @return тру если валидна
     */
    public boolean isDomainContainTheCurrentURL(String url, String domain) {
        return url.contains(new StringBuilder("https://").append(domain));
    }

    /**
     * проверка присутствует ли урл в бд
     *
     * @param url урл
     * @return если урл присутствует в бд то добавлять не будем
     */
    public boolean isContainsUrlToDataBase(String url) {
        return this.urlRepository.findAllByOrigin(url).size() > 0;
    }

    /**
     * проверка присутствует ли новый сгенерированный урл в бд
     *
     * @param newUrl новый сгенерированный урл
     * @return если урл присутствует в бд то добавлять не будем
     */
    public boolean isContainsNewOriginal(String newUrl) {
        return this.urlRepository.findAllByNewOrigin(newUrl).size() > 0;
    }

}
