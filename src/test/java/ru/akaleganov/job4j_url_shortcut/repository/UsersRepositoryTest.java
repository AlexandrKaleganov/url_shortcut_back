package ru.akaleganov.job4j_url_shortcut.repository;

import org.apache.log4j.Logger;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.job4j_url_shortcut.domain.Roles;
import ru.akaleganov.job4j_url_shortcut.domain.Users;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;


@DisplayName("тестирование: модель пользователи")
@TestPropertySource(locations = "classpath:application-h2.properties")
@SpringBootTest
class UsersRepositoryTest {
    private static final Logger LOGGER = Logger.getLogger(UsersRepositoryTest.class);
    @Autowired
    RolesRepository rolesRepository;
    @Autowired
    UsersRepository usersRepository;

    @Test
    @DisplayName("тестирование: добавление пользователия в  бд")
    public void addUser() {
        Roles roles = this.rolesRepository.save(new Roles("ADMIN2"));
        assertThat(roles.getId() != null, Is.is(true));
        LOGGER.info("roles = " + roles.toString());
        Users users = new Users();
        users.setLogin("test");
        users.setPwd("pwd");
        users.setRoles(Collections.singletonList(roles));
        Users usersResult = this.usersRepository.save(users);
        assertThat(usersResult.getId() != null, Is.is(true));
    }

}
