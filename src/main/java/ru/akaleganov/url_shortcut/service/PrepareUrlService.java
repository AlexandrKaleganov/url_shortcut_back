package ru.akaleganov.url_shortcut.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akaleganov.url_shortcut.domain.Url;
import ru.akaleganov.url_shortcut.domain.User;
import ru.akaleganov.url_shortcut.repository.UserRepository;
import ru.akaleganov.url_shortcut.service.dto.UrlDTO;
import ru.akaleganov.url_shortcut.service.util.RandomGenerator;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * сервис для подготовки урл для сохранения
 */
@Service
public class PrepareUrlService {
    private final UrlValidService urlValidService;
    private final UserRepository userRepository;
    private final RandomGenerator randomGenerator;

    public PrepareUrlService(UrlValidService urlValidService, UserRepository userRepository, RandomGenerator randomGenerator) {
        this.urlValidService = urlValidService;
        this.userRepository = userRepository;
        this.randomGenerator = randomGenerator;
    }

    /**
     * рефакторинг валидации
     *
     * @param url       урл
     * @param userLogin логин пользователя
     * @param save      функция сохранения
     * @return {@link UrlDTO}
     */
    @Transactional
    public UrlDTO prepareToSave(String url, String userLogin, Function<Url, UrlDTO> save) {
        return this.prepareUrl(url, userLogin, (errorMessage, rsl) -> {
            if (errorMessage == null) {
                return save.apply(rsl);
            } else {
                return new UrlDTO().setErrorMessage(errorMessage);
            }
        });
    }

    /**
     * урл проходит валидацию
     *
     * @param url       урл
     * @param userLogin логин пользователя
     * @param valid     функция валидации
     * @return {@link ru.akaleganov.url_shortcut.service.dto.UrlDTO}
     */
    private UrlDTO prepareUrl(String url, String userLogin, BiFunction<String, Url, UrlDTO> valid) {
//        if (this.urlValidService.isValidUrl(url)) {
            if (!this.urlValidService.isContainsUrlToDataBase(url)) {
                User user = this.userRepository.findByLogin(userLogin).orElse(new User());
                if (user.getDomain() == null) {
                    return valid.apply("у данного пользователя отсутствует домен, отредактируйте пользователя в меню - Пользователи - редактировать", new Url());
                }
                if (this.urlValidService.isDomainContainTheCurrentURL(url, user.getDomain())) {
                    String shortCut;
                    int k = 0;
                    do
                    {    //генерируем короткую ссылку и проверяем отсутствует ли в бд, количество попыток генерации 50
                        shortCut = this.randomGenerator.generateShortCut();
                        k++;
                    } while (shortCut == null && this.urlValidService.isContainsSortCutToDb(shortCut) || k < 50);
                    if (k <= 50) {
                        return valid.apply(null, new Url(url, shortCut, user));
                    } else {
                        return valid.apply(String.format("За %s  не удалось сгенерировать короткую ссылку", k), new Url());
                    }
                } else {
                    return valid.apply("адрес не содержит домена пользователя", new Url());
                }
            } else {
                return valid.apply("данный url  уже содержится в бд", new Url());
            }
//        } else {
//            return valid.apply("url не прошёл валидацию", new Url());
//        }
    }
}
