package ru.akaleganov.urlshortcut.repository;

import org.apache.log4j.Logger;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.urlshortcut.domain.Role;
import ru.akaleganov.urlshortcut.domain.User;

import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;


/**
 * The type User repository test.
 */
@DisplayName("тестирование: модель пользователи")
@TestPropertySource(locations = "classpath:application-h2.properties")
@SpringBootTest
class UserRepositoryTest {
    private static final Logger LOGGER = Logger.getLogger(UserRepositoryTest.class);
    /**
     * The Role repository.
     */
    @Autowired
    RoleRepository roleRepository;
    /**
     * The User repository.
     */
    @Autowired
    UserRepository userRepository;

    /**
     * Add user.
     */
    @Test
    @DisplayName("тестирование: добавление пользователия в  бд")
    public void addUser() {
        Role role = this.roleRepository.save(new Role("ADMIN2"));
        assertThat(role.getId() != null, Is.is(true));
        LOGGER.info("role = " + role.toString());
        User user = new User();
        user.setLogin("test");
        user.setPwd("pwd");
        user.setRoles(Collections.singletonList(role));
        User usersResult = this.userRepository.save(user);
        assertThat(usersResult.getId() != null, Is.is(true));
    }

}
