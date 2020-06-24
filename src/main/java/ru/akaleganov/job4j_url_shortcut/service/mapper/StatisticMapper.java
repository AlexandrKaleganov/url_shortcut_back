package ru.akaleganov.job4j_url_shortcut.service.mapper;

import org.springframework.stereotype.Service;
import ru.akaleganov.job4j_url_shortcut.domain.Statistic;
import ru.akaleganov.job4j_url_shortcut.service.dto.StatisticDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticMapper implements Mapper<StatisticDTO, Statistic> {
    private final UrlMapper urlMapper;

    public StatisticMapper(UrlMapper userMapper) {
        this.urlMapper = userMapper;
    }

    @Override
    public StatisticDTO toDto(Statistic statistic) {
        StatisticDTO statisticDTO = new StatisticDTO();
        statisticDTO.setId(statistic.getId());
        statisticDTO.setCount(statistic.getCount());
        statisticDTO.setUrlDTO(this.urlMapper.toDtoNonUser(statistic.getUrl()));
        return statisticDTO;
    }


    @Override
    public List<StatisticDTO> toDto(List<Statistic> statistic) {
        return statistic.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Statistic toEntity(StatisticDTO statisticDTO) {
        Statistic statistic = new Statistic();
        statistic.setId(statisticDTO.getId());
        statistic.setCount(statisticDTO.getCount());
        statistic.setUrl(this.urlMapper.toEntity(statisticDTO.getUrlDTO()));
        return statistic;
    }

    @Override
    public List<Statistic> toEntity(List<StatisticDTO> statisticDTOS) {
        return statisticDTOS.stream().map(this::toEntity).collect(Collectors.toList());
    }


}
