package ru.akaleganov.urlshortcut.service.user.uservalibimpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.repository.UserRepository;
import ru.akaleganov.urlshortcut.service.dto.UserDTO;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("тестирование: ValidUserServiceIsUserByLoginInDataTest")
class ValidUserServiceIsUserByLoginInDataTest {
    private final UserRepository userRepository = mock(UserRepository.class);
    private final ValidUserServiceIsUserByLoginInData validUserServiceIsUserByLoginInData
            = new ValidUserServiceIsUserByLoginInData(this.userRepository);

    @Test
    @DisplayName("проверка пользователь уже еть в бд")
    public void whenUserByLoginContainedInData() {
        when(userRepository.findByLogin("login1")).thenReturn(Optional.of(new User(1L).setLogin("login1")));
        assertNotNull(this.validUserServiceIsUserByLoginInData.isValid(
                new UserDTO().setLogin("login1")).getErrorMessage());
    }
    @Test
    @DisplayName("проверка пользователя точно нету в бд")
    public void whenUserByLoginNotContainedInData() {
        when(userRepository.findByLogin("login1")).thenReturn(Optional.of(new User()));
        assertNull(this.validUserServiceIsUserByLoginInData.isValid(
                new UserDTO().setLogin("login1")).getErrorMessage());
    }
    @Test
    @DisplayName("проверка пользователя пользователь есть в бд но мы позволяем т.к. id  совпадают и это обновление")
    public void whenUserByLoginContainedInDataAndIdEquals() {
        when(userRepository.findByLogin("login1")).thenReturn(Optional.of(new User(1L).setLogin("login1")));
        assertNull(this.validUserServiceIsUserByLoginInData.isValid(
                new UserDTO().setLogin("login1").setId(1L)).getErrorMessage());
    }
}
