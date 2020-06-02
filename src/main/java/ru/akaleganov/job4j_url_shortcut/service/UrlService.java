package ru.akaleganov.job4j_url_shortcut.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.akaleganov.job4j_url_shortcut.domain.Url;
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
     *
     * @param url       урл
     * @param userLogin логин пользователя
     * @return {@link UrlDTO}
     */
    public UrlDTO addUrl(String url, String userLogin) {
        return this.prepareUrlService.prepareToSave(url, userLogin,
                (rsl) -> {
            return urlMapper.toDto(this.urlRepository.save(rsl));
                });
    }

    /**
     * @param shortCut короткая ссылка
     * @return получить урл
     */
    public UrlDTO findUrlByShortCut(String shortCut) {
        return this.urlMapper.toDtoNonUser(this.urlRepository.findFirstByShortCut(shortCut).orElse(new Url()));
    }

    /**
     * поиск урла по id
     * @param id {@link Url#getId()}
     */
    public UrlDTO findUrlById(Long id) {
        return this.urlMapper.toDto(this.urlRepository.findById(id).orElse(new Url()));
    }

    /**
     * удаление урла по id
     * @param id {@link Url#getId()}
     */
    public void deleteUrlById(Long id) {
        this.urlRepository.deleteById(id);
    }

    /**
     * получить список урл c пагинацией и фильтрами
     */
    public Page<UrlDTO> findAllURl(Pageable pageable, UrlFilter urlFilter) {
        return  this.urlRepository.findAll(urlFilter.buildCriteria(), pageable).map(this.urlMapper::toDto);
    }
}
