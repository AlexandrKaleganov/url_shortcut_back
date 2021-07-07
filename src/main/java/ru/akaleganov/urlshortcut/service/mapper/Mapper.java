package ru.akaleganov.urlshortcut.service.mapper;

import java.util.List;

/**
 * The interface Mapper.
 *
 * @param <Dto>
 *         the type parameter
 * @param <Entity>
 *         the type parameter
 */
public interface Mapper<Dto, Entity> {

    /**
     * To dto dto.
     *
     * @param entity
     *         the entity
     *
     * @return the dto
     */
    Dto toDto(Entity entity);

    /**
     * To dto list.
     *
     * @param entityList
     *         the entity list
     *
     * @return the list
     */
    List<Dto> toDto(List<Entity> entityList);

    /**
     * To entity entity.
     *
     * @param dto
     *         the dto
     *
     * @return the entity
     */
    Entity toEntity(Dto dto);

    /**
     * To entity list.
     *
     * @param dtoList
     *         the dto list
     *
     * @return the list
     */
    List<Entity> toEntity(List<Dto> dtoList);

}
