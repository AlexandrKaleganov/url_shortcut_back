package ru.akaleganov.urlshortcut.service.user.uservalibimpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.repository.UserRepository;
import ru.akaleganov.urlshortcut.service.dto.UserDTO;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("тестирование: ValidUserServiceIsUsePasswordTest")
class ValidUserServiceIsUsePasswordTest {
    private final UserRepository userRepository = mock(UserRepository.class);
    private final ValidUserServiceIsUsePassword validUserServiceIsUsePassword
            = new ValidUserServiceIsUsePassword(this.userRepository, new BCryptPasswordEncoder());

    @Test
    @DisplayName("проверка пользователь уже еть в бд, и пароль у входящего пользователя пустой. Логика обновление" +
            " ошибка отсутствует")
    public void whenUserByLoginContainedInData() {
        when(userRepository.findByLogin("login1")).thenReturn(Optional.of(new
                User(1L).setLogin("login1").setPwd("password")));
        assertNull(this.validUserServiceIsUsePassword.isValid(
                new UserDTO().setLogin("login1")).getErrorMessage());
    }
    @Test
    @DisplayName("проверка пользователя точно нету в бд и пароль у входящего пользователя пустой. Логика ошибка.")
    public void whenUserByLoginNotContainedInData() {
        when(userRepository.findByLogin("login1")).thenReturn(Optional.of(new User()));
        assertNotNull(this.validUserServiceIsUsePassword.isValid(
                new UserDTO().setLogin("login1")).getErrorMessage());
    }
    @Test
    @DisplayName("проверка пароль у входящего пользователя не пустой, Логика создания пользователя ," +
            " ошибка отсутствует")
    public void whenUserByLoginContainedInDataAndIdEquals() {
        when(userRepository.findByLogin("login1")).thenReturn(Optional.of(new User(1L).setLogin("login1")));
        UserDTO res = this.validUserServiceIsUsePassword.isValid(
                new UserDTO().setLogin("login1").setPwd("asd"));
        assertNull(res.getErrorMessage());
        assertNotEquals(res.getPwd(), "asd");
    }
}
