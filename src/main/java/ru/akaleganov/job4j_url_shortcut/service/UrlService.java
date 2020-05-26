package ru.akaleganov.job4j_url_shortcut.service;

import org.springframework.stereotype.Service;

@Service
public class UrlService {

    /**
     * проверка url на валидность
     *
     * @param url домен пользователя
     * @return true если валидный, false   если нет
     */
    public boolean isValidUrl(String url) {
        return url.matches("^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$");
    }

}
