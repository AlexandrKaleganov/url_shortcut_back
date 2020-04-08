package ru.akaleganov.job4j_url_shortcut.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "roles")
public class Roles extends AllMappedClass {
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
