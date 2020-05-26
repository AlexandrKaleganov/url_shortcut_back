package ru.akaleganov.job4j_url_shortcut.service;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.job4j_url_shortcut.domain.Role;
import ru.akaleganov.job4j_url_shortcut.domain.User;
import ru.akaleganov.job4j_url_shortcut.repository.RoleRepository;
import ru.akaleganov.job4j_url_shortcut.repository.UserRepository;
import ru.akaleganov.job4j_url_shortcut.service.dto.UserDTO;
import ru.akaleganov.job4j_url_shortcut.service.mapper.UserMapper;
import ru.akaleganov.job4j_url_shortcut.service.util.RandomGeneratorLoginPass;

import java.util.Collections;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("тестирование: UsersService")
@TestPropertySource(locations = "classpath:application-h2.properties")
@SpringBootTest
class UserServiceTest {
    @Autowired
    RandomGeneratorLoginPass randomGeneratorLoginPass;
    @Autowired
    UserService userService;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

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

    @Test
    @DisplayName("тестирование: добавление пользователей по  URL")
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

    @Test
    @DisplayName("тестирование: добавление пользователей по  URL errorMessage")
    public void createUsersByUrlErrorOne() {
        this.roleRepository.save(new Role(2L, "USER"));
        userService.createUsersByUrl("akaleganov.ru");
        UserDTO res2 = userService.createUsersByUrl("akaleganov.ru");
        assertThat(res2.getId() == null, Is.is(true));
        assertThat(res2.getErrorMessage(), Is.is("url  " + "akaleganov.ru" + " уже занят"));
    }

    @Test
    @DisplayName("тестирование: добавление пользователя админом")
    public void createNeUserAdmin() {
        Role role = new Role(2L, "USER");
        role = this.roleRepository.save(role);
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("ADADASDSAD");
        userDTO.setPwd("kjsfksdfkjdshf");
        userDTO.setUrl("asdasdasdas.com");
        userDTO.setRoles(Collections.singletonList(role));
        assertThat(userService.create(userDTO).getId() != null,
                Is.is(true));
    }

    @Test
    @DisplayName("тестирование: обновление пользователя")
    public void testUpdateUser() {
        Role role = new Role(2L, "USER");
        role = this.roleRepository.save(role);
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("qwedwq545dqw");
        userDTO.setPwd("qdwqdwq");
        userDTO.setUrl("asdassdasxdadwqqdqsdas.com");
        userDTO.setRoles(Collections.singletonList(role));
        UserDTO res = this.userService.create(userDTO);
        res.setPwd(null);
        res.setLastName("Вася");
        assertThat(this.userService.updateUser(res).getLastName(), Is.is("Вася"));
        assertThat(this.userRepository.findById(res.getId()).orElse(new User()).getLastName(), Is.is("Вася"));
    }

    @Test
    @DisplayName("тестирование: транзакций")
    public void khskjd() {
        Role role = new Role(2L, "USER");
        role = this.roleRepository.save(role);
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin("qwedwqdqw");
        userDTO.setPwd("qdwqdwq");
        userDTO.setUrl("asdasdadwqqdqsdas.com");
        userDTO.setRoles(Collections.singletonList(role));
        UserDTO res = this.userService.create(userDTO);
        this.userService.setLastName("Вася", res.getId());
        assertThat(this.userRepository.findById(res.getId()).orElse(new User()).getLastName(), Is.is("Вася"));
    }
}
