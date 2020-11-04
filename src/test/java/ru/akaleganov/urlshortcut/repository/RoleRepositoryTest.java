package ru.akaleganov.urlshortcut.repository;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.urlshortcut.domain.Role;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * The type Role repository test.
 */
@DisplayName("тестирование: модель ролей")
@TestPropertySource(locations = "classpath:application-h2.properties")
@SpringBootTest
class RoleRepositoryTest {
    /**
     * The Role repository.
     */
    @Autowired
    RoleRepository roleRepository;

    /**
     * Add role.
     */
    @Test
    @DisplayName("тестирование: добавление роли в бд")
    public void addRole() {
        Role res = this.roleRepository.save(new Role("ADMIN"));
        assertThat(res.getId() != null, Is.is(true));
    }

    /**
     * Gets by id role.
     */
    @Test
    @DisplayName("тестирование: поиск роли по id")
    public void getByIdRole() {
        Role res = this.roleRepository.save(new Role("ADMIN"));
        assertThat(this.roleRepository.findById(res.getId()).isPresent(), Is.is(true));
    }
}
