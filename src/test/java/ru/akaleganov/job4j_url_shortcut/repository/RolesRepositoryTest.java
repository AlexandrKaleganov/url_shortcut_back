package ru.akaleganov.job4j_url_shortcut.repository;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.job4j_url_shortcut.domain.Roles;

import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("тестирование: модель ролей")
@TestPropertySource(locations = "classpath:application-h2.properties")
@SpringBootTest
class RolesRepositoryTest {
    @Autowired
    RolesRepository rolesRepository;

    @Test
    @DisplayName("тестирование: добавление роли в бд")
    public void addRole() {
        Roles res = this.rolesRepository.save(new Roles("ADMIN"));
        assertThat(res.getId() != null, Is.is(true));
    }
    @Test
    @DisplayName("тестирование: поиск роли по id")
    public void getByIdRole() {
        Roles res = this.rolesRepository.save(new Roles("ADMIN"));
        assertThat(this.rolesRepository.findById(res.getId()).isPresent(), Is.is(true));
    }
}
