package ru.akaleganov.urlshortcut.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * The type Mapped super class.
 */
@MappedSuperclass
public class MappedSuperClass {
    /**
     * идентификатор объекта в бд
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Instantiates a new Mapped super class.
     */
    public MappedSuperClass() {
    }
}

