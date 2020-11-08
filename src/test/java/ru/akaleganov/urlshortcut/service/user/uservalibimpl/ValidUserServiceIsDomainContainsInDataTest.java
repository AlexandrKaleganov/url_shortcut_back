package ru.akaleganov.urlshortcut.service.user.uservalibimpl;

import org.junit.jupiter.api.DisplayName;
import ru.akaleganov.urlshortcut.repository.UserRepository;

import static org.mockito.Mockito.mock;

@DisplayName("тестирование: ValidUserServiceIsDomainContainsInDataTest")
class ValidUserServiceIsDomainContainsInDataTest {
    private final UserRepository userRepository = mock(UserRepository.class);
    private final ValidUserServiceIsDomainContainsInData validUserServiceIsDomainContainsInData
            = new ValidUserServiceIsDomainContainsInData(this.userRepository);
}
