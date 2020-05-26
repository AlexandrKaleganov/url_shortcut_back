package ru.akaleganov.job4j_url_shortcut.repository;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.job4j_url_shortcut.domain.Role;

import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("тестирование: модель ролей")
@TestPropertySource(locations = "classpath:application-h2.properties")
@SpringBootTest
class RoleRepositoryTest {
    @Autowired
    RoleRepository roleRepository;

    @Test
    @DisplayName("тестирование: добавление роли в бд")
    public void addRole() {
        Role res = this.roleRepository.save(new Role("ADMIN"));
        assertThat(res.getId() != null, Is.is(true));
    }
    @Test
    @DisplayName("тестирование: поиск роли по id")
    public void getByIdRole() {
        Role res = this.roleRepository.save(new Role("ADMIN"));
        assertThat(this.roleRepository.findById(res.getId()).isPresent(), Is.is(true));
    }
}
