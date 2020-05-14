package ru.akaleganov.job4j_url_shortcut.service;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.akaleganov.job4j_url_shortcut.domain.Roles;
import ru.akaleganov.job4j_url_shortcut.domain.Users;
import ru.akaleganov.job4j_url_shortcut.repository.UsersRepository;
import ru.akaleganov.job4j_url_shortcut.service.dto.UsersDTO;
import ru.akaleganov.job4j_url_shortcut.service.mapper.UsersMapper;
import ru.akaleganov.job4j_url_shortcut.service.util.RandomGeneratorLoginPass;

import java.util.Collections;
import java.util.function.Supplier;

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
        return saveUsers(url, () -> {
            Users users = new Users();
            users.setUrl(url);
            users.setRoles(Collections.singletonList(new Roles(2L)));
            users.setLogin(this.randomGeneratorLoginPass.generateLogin());
            users.setPwd(this.randomGeneratorLoginPass.generatePassword());
            UsersDTO result = this.usersMapper.usersToUsersDTO(users);
            result.setPwd(users.getPwd());
            users.setPwd(this.bCryptPasswordEncoder.encode(users.getPwd()));
            this.usersRepository.save(users);
            result.setId(users.getId());
            return result;
        });
    }

    /**
     * создание пользователя через админку
     *
     * @param usersDTO {@link UsersDTO}
     * @return {@link UsersDTO}
     */
    public UsersDTO create(UsersDTO usersDTO) {
        return this.saveUsers(usersDTO.getUrl(), () -> this.usersMapper.usersToUsersDTO(this.usersRepository.save(this.encode(usersDTO)))
        );
    }

    /**
     * получить пользователя по логину
     *
     * @param login логин пользователя
     * @return {@link UsersDTO}
     */
    public UsersDTO getUsersByLogin(String login) {
        return this.usersMapper.usersToUsersDTO(this.usersRepository.findByLogin(login).orElse(new Users()));
    }

    /**
     * получить пользователя по логину
     *
     * @param login логин пользователя
     * @return {@link Users}
     */
    public Users findUserByLogin(String login) {
        return this.usersRepository.findByLogin(login).orElse(new Users());
    }

    /**
     * сохранение  пользователя в бд
     *
     * @param url  url
     * @param save функция сохранения которую надо реализовать
     * @return {@link UsersDTO}
     */
    private UsersDTO saveUsers(String url, Supplier<UsersDTO> save) {
        if (isValidUrl(url)) {
            if (this.usersRepository.findByUrl(url).isPresent()) {
                return new UsersDTO().setErrorMessage("url  " + url + " уже занят");
            } else {
                return save.get();
            }
        } else {
            return new UsersDTO().setErrorMessage("url не прошёл валидацию");
        }
    }

    /**
     * обновление данных текущего пользователя
     *
     * @param usersDTO {@link UsersDTO}
     * @return {@link UsersDTO}
     */
    public UsersDTO updateUsers(UsersDTO usersDTO) {
        Users oldUSer = this.usersRepository.findByLogin(usersDTO.getLogin()).orElse(new Users());
        if (!oldUSer.getUrl().equals(usersDTO.getUrl())) {
            return this.saveUsers(usersDTO.getUrl(), () -> this.usersMapper.usersToUsersDTO(this.usersRepository.save(
                    this.prepareUsers(usersDTO))));
        } else {
            return this.usersMapper.usersToUsersDTO(this.usersRepository.save(
                    this.prepareUsers(usersDTO)));
        }
    }

    /**
     * подготовка пользователя к обновлению (проверка паролей)
     * @param usersDTO {@link UsersDTO}
     * @return {@link Users}
     */
    private Users prepareUsers(UsersDTO usersDTO) {
        if (usersDTO.getPwd() != null && !usersDTO.getPwd().equals("")) {
            return this.encode(usersDTO);
        } else {
            usersDTO.setPwd(this.findUserByLogin(usersDTO.getLogin()).getPwd());
            return this.usersMapper.usersDTOToUser(usersDTO);
        }

    }

    /**
     * @param usersDTO {@link UsersDTO}
     * @return {@link Users}
     */
    private Users encode(UsersDTO usersDTO) {
        if (usersDTO.getPwd() != null) {
            usersDTO.setPwd(this.bCryptPasswordEncoder.encode(usersDTO.getPwd()));
        }
        return this.usersMapper.usersDTOToUser(usersDTO);
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
