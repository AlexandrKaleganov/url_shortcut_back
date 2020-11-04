package ru.akaleganov.urlshortcut.service;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.urlshortcut.domain.Role;
import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.repository.RoleRepository;
import ru.akaleganov.urlshortcut.repository.UserRepository;
import ru.akaleganov.urlshortcut.service.dto.UserDTO;
import ru.akaleganov.urlshortcut.service.mapper.UserMapper;
import ru.akaleganov.urlshortcut.service.util.RandomGenerator;

import java.util.Collections;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * The type User service test.
 */
@DisplayName("тестирование: UsersService")
@TestPropertySource(locations = "classpath:application-h2.properties")
@SpringBootTest
class UserServiceTest {
    /**
     * The Random generator.
     */
    @Autowired
    RandomGenerator randomGenerator;
    /**
     * The User service.
     */
    @Autowired
    UserService userService;
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
     * The User mapper.
     */
    @Autowired
    UserMapper userMapper;

    /**
     * Given using java 8 when generating random alphabetic string then correct.
     */
    @Test
    @DisplayName("тестирование: генерация логина")
    public void givenUsingJava8_whenGeneratingRandomAlphabeticString_thenCorrect() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
    }

    /**
     * Given using java 8 when generating random alphanumeric string then correct.
     */
    @Test
    @DisplayName("тестирование: генерация паролей")
    public void givenUsingJava8_whenGeneratingRandomAlphanumericString_thenCorrect() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        System.out.println(generatedString);
    }

    /**
     * Create users by url.
     */
    @Test
    @DisplayName("тестирование: добавление пользователей по  Domain")
    public void createUsersByUrl() {
        Role role = new Role(2L, "USER");
        role = this.roleRepository.save(role);
        assertThat(role.getId() == 2, Is.is(true));
        UserDTO res = userService.createUsersByUrl("akaleganov3.ru");
        assertThat(this.roleRepository.findById(role.getId()).orElse(new Role()).getName(), Is.is("USER"));
        assertThat(res.getId() != null, Is.is(true));
        UserDTO res2 = userService.createUsersByUrl("бла бла бла");
        assertThat(res2.getErrorMessage(), Is.is("url не прошёл валидацию"));
    }

    /**
     * Create users by url error one.
     */
    @Test
    @DisplayName("тестирование: добавление пользователей по  Domain errorMessage")
    public void createUsersByUrlErrorOne() {
        this.roleRepository.save(new Role(2L, "USER"));
        userService.createUsersByUrl("akaleganov.ru");
        UserDTO res2 = userService.createUsersByUrl("akaleganov.ru");
        assertThat(res2.getId() == null, Is.is(true));
        assertThat(res2.getErrorMessage(), Is.is("Домен  akaleganov.ru уже занят"));
    }

    /**
     * Create ne user admin.
     */
    @Test
    @DisplayName("тестирование: добавление пользователя админом")
    public void createNeUserAdmin() {
        Role role = new Role(2L, "USER");
        role = this.roleRepository.save(role);
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("ADADASDSAD");
        userDTO.setPwd("kjsfksdfkjdshf");
        userDTO.setDomain("asdasdasdas.com");
        userDTO.setRoles(Collections.singletonList(role));
        assertThat(userService.create(userDTO).getId() != null,
                Is.is(true));
    }

    /**
     * Test update user.
     */
    @Test
    @DisplayName("тестирование: обновление пользователя")
    public void testUpdateUser() {
        Role role = new Role(2L, "USER");
        role = this.roleRepository.save(role);
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("qwedwq545dqw");
        userDTO.setPwd("qdwqdwq");
        userDTO.setDomain("asdassdasxdadwqqdqsdas.com");
        userDTO.setRoles(Collections.singletonList(role));
        UserDTO res = this.userService.create(userDTO);
        res.setPwd(null);
        res.setLastName("Вася");
        assertThat(this.userService.updateUser(res).getLastName(), Is.is("Вася"));
        assertThat(this.userRepository.findById(res.getId()).orElse(new User()).getLastName(), Is.is("Вася"));
    }

}
