package ru.akaleganov.urlshortcut.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akaleganov.urlshortcut.domain.Statistic;
import ru.akaleganov.urlshortcut.domain.Url;
import ru.akaleganov.urlshortcut.repository.UrlRepository;
import ru.akaleganov.urlshortcut.service.dto.UrlDTO;
import ru.akaleganov.urlshortcut.service.mapper.UrlMapper;
import ru.akaleganov.urlshortcut.service.url.PrepareUrlService;
import ru.akaleganov.urlshortcut.service.url.UrlFilter;


/**
 * The type Url service.
 */
@Service
@AllArgsConstructor
public class UrlServiceImpl implements UrlService {
    private final PrepareUrlService prepareUrlServiceImpl;
    private final UrlRepository urlRepository;
    private final UrlMapper urlMapper;
    private final StatisticService statisticService;

    /**
     * добалвение урл в бд
     *
     * @param url       урл
     * @param userLogin логин пользователя
     * @return {@link UrlDTO}
     */
    @Transactional
    public UrlDTO addUrl(String url, String userLogin) {
        UrlDTO urlDTO = this.prepareUrlServiceImpl.prepareToSave(url, userLogin);
        if (urlDTO.getErrorMessage() == null) {
            Url res = this.urlRepository.save(this.urlMapper.toEntity(urlDTO));
            this.statisticService.createNewStatistic(res);
            return this.urlMapper.toDto(res);
        }
        return urlDTO;
    }

    /**
     * Find url by short cut url dto.
     *
     * @param shortCut короткая ссылка
     * @return получить урл
     */
    @Transactional
    public UrlDTO findUrlByShortCut(String shortCut) {
        Url url = this.urlRepository.findFirstByShortCut(shortCut).orElse(new Url());
        if (url.getId() != null) {
            this.statisticService.setStatisticByIdUrl(url.getId());
            return this.urlMapper.toDto(url);
        }
        return this.urlMapper.toDto(url).setErrorMessage(String.format("Короткая ссылка %s не зарегистрирована", shortCut));
    }

    /**
     * поиск урла по id
     *
     * @param id {@link Url#getId()}
     * @return the url dto
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
     *
     * @param pageable  the pageable
     * @param urlFilter the url filter
     * @return the page
     */
    @Transactional
    public Page<UrlDTO> findAllURl(Pageable pageable, UrlFilter urlFilter) {
        return this.urlRepository.findAll(urlFilter.buildCriteria(), pageable).map(this.urlMapper::toDto);
    }
    public Statistic gtStat(Url url) {
        return this.statisticService.findStatisticByUrlId(url.getId());
    }
}
