package ru.akaleganov.urlshortcut.service.user.uservalibimpl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.akaleganov.urlshortcut.service.dto.UserDTO;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("тестирование: ValidUserServiceIsDomainValidTest")
class ValidUserServiceIsDomainValidTest {

    private final ValidUserServiceIsDomainValid validUserServiceIsDomainValid = new ValidUserServiceIsDomainValid();


    @Test
    @DisplayName("домен валиден")
    public void whenDomainIsValid() {
        assertNull(this.validUserServiceIsDomainValid.isValid(new UserDTO().setDomain("zdzxdzx.ru")).getErrorMessage());
    }
    @Test
    @DisplayName("домен не валиден")
    public void whenDomainIsNotValid() {
        assertNotNull(this.validUserServiceIsDomainValid.isValid(new UserDTO().setDomain("zdzxdzx")).getErrorMessage());
    }
}
