package ru.akaleganov.url_shortcut.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.akaleganov.url_shortcut.domain.Url;
import ru.akaleganov.url_shortcut.repository.UrlRepository;
import ru.akaleganov.url_shortcut.service.dto.UrlDTO;
import ru.akaleganov.url_shortcut.service.mapper.UrlMapper;


@Service
public class UrlService {
    private final PrepareUrlService prepareUrlService;
    private final UrlRepository urlRepository;
    private final UrlMapper urlMapper;
    private final StatisticService statisticService;

    public UrlService(PrepareUrlService prepareUrlService, UrlRepository urlRepository, UrlMapper urlMapper, StatisticService statisticService) {
        this.prepareUrlService = prepareUrlService;
        this.urlRepository = urlRepository;
        this.urlMapper = urlMapper;
        this.statisticService = statisticService;
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
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public UrlDTO findUrlByShortCut(String shortCut) {
        Url url =this.urlRepository.findFirstByShortCut(shortCut).orElse(new Url());
        if(url.getId() != null) {
            this.statisticService.setStatisticByIdUrl(url);
            return this.urlMapper.toDto(url);
        }
        return this.urlMapper.toDto(url).setErrorMessage(String.format("Короткая ссылка %s не зарегистрирована", shortCut));
    }

    /**
     * поиск урла по id
     *
     * @param id {@link Url#getId()}
     */
    public UrlDTO findUrlById(Long id) {
        return this.urlMapper.toDto(this.urlRepository.findById(id).orElse(new Url()));
    }

    /**
     * удаление урла по id
     *
     * @param id {@link Url#getId()}
     */
    public void deleteUrlById(Long id) {
        this.urlRepository.deleteById(id);
    }

    /**
     * получить список урл c пагинацией и фильтрами
     */
    @Transactional
    public Page<UrlDTO> findAllURl(Pageable pageable, UrlFilter urlFilter) {
        return this.urlRepository.findAll(urlFilter.buildCriteria(), pageable).map(this.urlMapper::toDto);
    }
}
