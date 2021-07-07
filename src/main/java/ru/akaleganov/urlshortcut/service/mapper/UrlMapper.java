package ru.akaleganov.urlshortcut.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import ru.akaleganov.urlshortcut.domain.Url;
import ru.akaleganov.urlshortcut.service.dto.UrlDTO;

/**
 * The type Url mapper.
 */
@Service
public class UrlMapper implements Mapper<UrlDTO, Url> {

    private final UserMapper userMapper;

    /**
     * Instantiates a new Url mapper.
     *
     * @param userMapper
     *         the user mapper
     */
    public UrlMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UrlDTO toDto(Url url) {
        UrlDTO urlDTO = new UrlDTO();
        urlDTO.setId(url.getId());
        urlDTO.setOrigin(url.getOrigin());
        urlDTO.setShortCut(url.getShortCut());
        urlDTO.setUser(this.userMapper.userToUserDTO(url.getUser()));
        return urlDTO;
    }

    /**
     * To dto non user url dto.
     *
     * @param url
     *         the url
     *
     * @return the url dto
     */
    public UrlDTO toDtoNonUser(Url url) {
        UrlDTO urlDTO = new UrlDTO();
        urlDTO.setId(url.getId());
        urlDTO.setOrigin(url.getOrigin());
        urlDTO.setShortCut(url.getShortCut());
        return urlDTO;
    }

    /**
     * To dt non usero list.
     *
     * @param urls
     *         the urls
     *
     * @return the list
     */
    public List<UrlDTO> toDtNonUsero(List<Url> urls) {
        return urls.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public List<UrlDTO> toDto(List<Url> urls) {
        return urls.stream().map(this::toDtoNonUser).collect(Collectors.toList());
    }

    @Override
    public Url toEntity(UrlDTO urlDTO) {
        Url url = new Url();
        url.setOrigin(urlDTO.getOrigin());
        url.setShortCut(urlDTO.getShortCut());
        url.setUser(this.userMapper.userDTOToUser(urlDTO.getUser()));
        return url;
    }

    @Override
    public List<Url> toEntity(List<UrlDTO> urlDTOS) {
        return urlDTOS.stream().map(this::toEntity).collect(Collectors.toList());
    }

}
