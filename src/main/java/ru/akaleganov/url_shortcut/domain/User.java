package ru.akaleganov.url_shortcut.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity(name = "lex_user")
public class User extends MappedSuperClass implements UserDetails {
    @Column(name = "login")
    private String login;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "pwd")
    private String pwd;
    @Column(name = "domain")
    private String domain;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String url) {
        this.domain = url;
    }

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "lex_users_lex_roles", joinColumns = @JoinColumn(name = "lex_users_id"),
            inverseJoinColumns = @JoinColumn(name = "lex_roles_id"))
    private List<Role> roles;

    public String getLogin() {
        return login;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }


    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return new ArrayList<>(getRoles());
    }

    @Override
    public String getPassword() {
        return getPwd();
    }

    @Override
    public String getUsername() {
        return getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User users = (User) o;
        return Objects.equals(login, users.login) &&
                Objects.equals(lastName, users.lastName) &&
                Objects.equals(firstName, users.firstName) &&
                Objects.equals(middleName, users.middleName) &&
                Objects.equals(pwd, users.pwd) &&
                Objects.equals(roles, users.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, lastName, firstName, middleName, pwd, roles);
    }

    @Override
    public String toString() {
        return "Users{" +
                "login='" + login + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", url='" + domain + '\'' +
                ", roles=" + roles +
                '}';
    }
}
