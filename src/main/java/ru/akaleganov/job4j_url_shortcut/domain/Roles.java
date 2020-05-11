package ru.akaleganov.job4j_url_shortcut.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private List<Users> users;
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "name='" + name + '\'' +
                '}';
    }
}
