package ru.akaleganov.url_shortcut.service.mapper;

import java.util.List;

public interface Mapper<Dto, Entity> {
    Dto toDto(Entity entity);

    List<Dto> toDto(List<Entity> entityList);

    Entity toEntity(Dto dto);

    List<Entity> toEntity(List<Dto> dtoList);
}
