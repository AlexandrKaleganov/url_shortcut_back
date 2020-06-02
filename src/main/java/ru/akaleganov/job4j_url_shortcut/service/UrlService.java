package ru.akaleganov.job4j_url_shortcut.service;

import org.springframework.stereotype.Service;
import ru.akaleganov.job4j_url_shortcut.repository.UrlRepository;
import ru.akaleganov.job4j_url_shortcut.service.dto.UrlDTO;
import ru.akaleganov.job4j_url_shortcut.service.mapper.UrlMapper;

@Service
public class UrlService {
    private final PrepareUrlService prepareUrlService;
    private final UrlRepository urlRepository;
    private final UrlMapper urlMapper;

    public UrlService(PrepareUrlService prepareUrlService, UrlRepository urlRepository, UrlMapper urlMapper) {
        this.prepareUrlService = prepareUrlService;
        this.urlRepository = urlRepository;
        this.urlMapper = urlMapper;
    }

    /**
     * добалвение урл в бд
     * @param url урл
     * @param userLogin логин пользователя
     * @return {@link UrlDTO}
     */
    public UrlDTO addUrl(String url, String userLogin) {
        return this.prepareUrlService.prepareToSave(url, userLogin,
                (rsl) -> urlMapper.toDto(this.urlRepository.save(rsl)));
    }

}
