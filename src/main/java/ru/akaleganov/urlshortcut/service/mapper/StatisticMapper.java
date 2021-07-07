package ru.akaleganov.urlshortcut.service.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import ru.akaleganov.urlshortcut.domain.Statistic;
import ru.akaleganov.urlshortcut.service.dto.StatisticDTO;

/**
 * The type Statistic mapper.
 */
@Service
public class StatisticMapper implements Mapper<StatisticDTO, Statistic> {

    private final UrlMapper urlMapper;

    /**
     * Instantiates a new Statistic mapper.
     *
     * @param userMapper
     *         the user mapper
     */
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
