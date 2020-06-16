package ru.akaleganov.job4j_url_shortcut.service.mapper;

import org.springframework.stereotype.Service;
import ru.akaleganov.job4j_url_shortcut.domain.Url;
import ru.akaleganov.job4j_url_shortcut.service.dto.UrlDTO;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UrlMapper implements Mapper<UrlDTO, Url>{
    private final UserMapper userMapper;

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
    public UrlDTO toDtoNonUser(Url url) {
        UrlDTO urlDTO = new UrlDTO();
        urlDTO.setId(url.getId());
        urlDTO.setOrigin(url.getOrigin());
        urlDTO.setShortCut(url.getShortCut());
        return urlDTO;
    }
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
