package ru.akaleganov.urlshortcut.service.url;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.repository.UserRepository;
import ru.akaleganov.urlshortcut.service.dto.UrlDTO;
import ru.akaleganov.urlshortcut.service.mapper.UserMapper;
import ru.akaleganov.urlshortcut.service.util.RandomGenerator;

import java.util.List;

/**
 * Class PrepareUrlServiceNEw
 * класс проверит есть пройдёт ли список проверок нашь урл
 *
 * @author Kaleganov Alexander
 * @since 31 окт. 20
 */
@Service
@AllArgsConstructor
public class PrepareUrlServiceImpl implements PrepareUrlService {
    private final List<ValidUrlService> validUrlServices;
    private final UserRepository userRepository;
    private final RandomGenerator randomGenerator;
    private final UserMapper userMapper;

    /**
     * если вернёт urlDTO с ошибкой то дальше не пойдём
     *
     * @param url       новый урл для сохранения
     * @param userLogin {@link User#getLogin()}
     * @return {@link UrlDTO}
     */
    @Override
    public UrlDTO prepareToSave(String url, String userLogin) {
        User user = this.userRepository.findByLogin(userLogin).orElse(new User());
        return this.validUrlServices.stream().map(e -> e.isValid(url, user))
                .filter(e -> e.getErrorMessage() != null).findFirst().orElse(new UrlDTO())
                .setUser(this.userMapper.userToUserDTO(user)).setOrigin(url)
                .setShortCut(randomGenerator.generateShortCut());

    }
}
