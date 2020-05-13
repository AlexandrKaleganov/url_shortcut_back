package ru.akaleganov.job4j_url_shortcut.service;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.akaleganov.job4j_url_shortcut.domain.Roles;
import ru.akaleganov.job4j_url_shortcut.domain.Users;
import ru.akaleganov.job4j_url_shortcut.repository.UsersRepository;
import ru.akaleganov.job4j_url_shortcut.service.dto.UsersDTO;
import ru.akaleganov.job4j_url_shortcut.service.mapper.UsersMapper;
import ru.akaleganov.job4j_url_shortcut.service.util.RandomGeneratorLoginPass;

import java.util.Collections;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UsersMapper usersMapper;
    private final RandomGeneratorLoginPass randomGeneratorLoginPass;
    private static final Logger LOGGER = Logger.getLogger(UsersService.class);

    UsersService(UsersRepository usersRepository, BCryptPasswordEncoder encoder, UsersMapper usersMapper, RandomGeneratorLoginPass randomGeneratorLoginPass) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = encoder;
        this.usersMapper = usersMapper;
        this.randomGeneratorLoginPass = randomGeneratorLoginPass;
    }

    /**
     * получить всех пользаков c пагинацией  (гадо пдумать как реализовать)
     *
     * @param pageable пагинация
     * @return {@link UsersDTO}
     */
    public Page<UsersDTO> findAll(Pageable pageable) {

        return this.usersRepository.findAll(pageable).map(this.usersMapper::usersToUsersDTO);
    }

    /**
     * добавление пользователя по url
     *
     * @param url ссылка на домен, каждый пользователь может дбавить только один домен
     * @return {@link UsersDTO}
     */
    public UsersDTO createUsersByUrl(String url) {
        if (isValidUrl(url)) {
            if (this.usersRepository.findByUrl(url).isPresent()) {
                LOGGER.debug("пользователь с url " + url + " найден и  не будет добавлен в БД");
                return new UsersDTO().setErrorMessage("url  " + url + " уже занят");
            } else {
                Users users = new Users();
                users.setUrl(url);
                users.setRoles(Collections.singletonList(new Roles(2L)));
                users.setLogin(this.randomGeneratorLoginPass.generateLogin());
                users.setPwd(this.randomGeneratorLoginPass.generatePassword());
                UsersDTO result = this.usersMapper.usersToUsersDTO(users);
                result.setPwd(users.getPwd());
                users.setPwd(this.bCryptPasswordEncoder.encode(users.getPwd()));
                LOGGER.debug("users = " + users.toString());
                this.usersRepository.save(users);
                result.setId(users.getId());
                return result;
            }
        } else {
            return new UsersDTO().setErrorMessage("url не прошёл валидацию");
        }
    }

    /**
     * проверка url на валидность
     *
     * @param url домен пользователя
     * @return true если валидный, false   если нет
     */
    private boolean isValidUrl(String url) {
        return url.matches("^((?!-)[A-Za-z0-9-]{1,63}(?<!-)\\.)+[A-Za-z]{2,6}$");
    }
}
