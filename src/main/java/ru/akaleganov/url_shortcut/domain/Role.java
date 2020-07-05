package ru.akaleganov.url_shortcut.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Objects;

@Entity(name = "role")
public class Role extends MappedSuperClass implements GrantedAuthority {
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role() {
    }

    public Role(Long id) {
        this.setId(id);
    }

    public Role(Long id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public Role(String name) {
        this.setName(name);
    }


    @Override
    public String getAuthority() {
        return this.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role roles = (Role) o;
        return Objects.equals(name, roles.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                '}';
    }
}
