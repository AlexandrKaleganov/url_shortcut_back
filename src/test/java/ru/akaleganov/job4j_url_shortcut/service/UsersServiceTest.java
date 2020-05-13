package ru.akaleganov.job4j_url_shortcut.service;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ru.akaleganov.job4j_url_shortcut.domain.Roles;
import ru.akaleganov.job4j_url_shortcut.repository.RolesRepository;
import ru.akaleganov.job4j_url_shortcut.repository.UsersRepository;
import ru.akaleganov.job4j_url_shortcut.service.dto.UsersDTO;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("тестирование: UsersService")
@TestPropertySource(locations = "classpath:application-h2.properties")
@SpringBootTest
class UsersServiceTest {
    @Autowired
    UsersService usersService;
    @Autowired
    RolesRepository rolesRepository;
    @Autowired
    UsersRepository usersRepository;


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
        Roles roles = new Roles(2L, "USER");
        roles = this.rolesRepository.save(roles);
        assertThat(roles.getId() == 2, Is.is(true));
        UsersDTO res = this.usersService.createUsersByUrl("akaleganov3.ru");
        assertThat(this.rolesRepository.findById(roles.getId()).orElse(new Roles()).getName(), Is.is("USER"));
        assertThat(res.getId() != null, Is.is(true));
        UsersDTO res2 = this.usersService.createUsersByUrl("бла бла бла");
        assertThat(res2.getErrorMessage() , Is.is("url не прошёл валидацию"));
    }
    @Test
    @DisplayName("тестирование: добавление пользователей по  URL errorMessage")
    public void createUsersByUrlErrorOne() {
        this.rolesRepository.save(new Roles(2L, "USER"));
        this.usersService.createUsersByUrl("akaleganov.ru");
        UsersDTO res2 = this.usersService.createUsersByUrl("akaleganov.ru");
        assertThat(res2.getId() == null, Is.is(true));
        assertThat(res2.getErrorMessage() , Is.is("url  " + "akaleganov.ru" + "уже занят"));
    }
}
