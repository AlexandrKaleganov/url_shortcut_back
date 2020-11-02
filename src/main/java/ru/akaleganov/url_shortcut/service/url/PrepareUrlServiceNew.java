package ru.akaleganov.url_shortcut.service.url;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.akaleganov.url_shortcut.domain.User;
import ru.akaleganov.url_shortcut.repository.UserRepository;
import ru.akaleganov.url_shortcut.service.dto.UrlDTO;
import ru.akaleganov.url_shortcut.service.mapper.UserMapper;
import ru.akaleganov.url_shortcut.service.util.RandomGenerator;

import java.util.List;
import java.util.Random;

/**
 * Class PrepareUrlServiceNEw
 * класс проверит есть пройдёт ли список проверок нашь урл
 *
 *
 * @author Kaleganov Alexander
 * @since 31 окт. 20
 */
@Service
@AllArgsConstructor
public class PrepareUrlServiceNew implements PrepareUrlService {
    private final List<ValidService> validServices;
    private final UserRepository userRepository;
    private final JdbcTemplate jdbcTemplate;
    private final RandomGenerator randomGenerator;
    private final UserMapper userMapper;
    /**
     * если вернёт urlDTO с ошибкой то дальше не пойдём
     * @param url новый урл для сохранения
     * @param userLogin {@link User#getLogin()}
     * @return {@link UrlDTO}
     */
    @Override
    public UrlDTO prepareToSave(String url, String userLogin) {
        User user = this.userRepository.findByLogin(userLogin).orElse(new User());
        return this.validServices.stream().map(e->e.isValid(url, user))
                .filter(e->e.getErrorMessage()!= null).findFirst().orElse(new UrlDTO())
                .setUser(this.userMapper.userToUserDTO(user)).setOrigin(url)
                .setShortCut(randomGenerator.generateShortCut());

    }
}
