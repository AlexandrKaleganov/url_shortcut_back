package ru.akaleganov.job4j_url_shortcut.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity(name = "roles")
public class Roles extends MappedSuperClass implements GrantedAuthority {
    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Roles() {
    }

    public Roles(Long id) {
        this.setId(id);
    }
    public Roles(Long id, String name) {
        this.setId(id);
        this.setName(name);
    }
    public Roles(String name) {
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
        Roles roles = (Roles) o;
        return Objects.equals(name, roles.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Roles{" +
                "name='" + name + '\'' +
                '}';
    }
}
