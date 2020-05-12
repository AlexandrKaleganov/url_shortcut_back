package ru.akaleganov.job4j_url_shortcut.service;

import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.akaleganov.job4j_url_shortcut.domain.Roles;
import ru.akaleganov.job4j_url_shortcut.domain.Users;
import ru.akaleganov.job4j_url_shortcut.repository.RolesRepository;
import ru.akaleganov.job4j_url_shortcut.repository.UsersRepository;
import ru.akaleganov.job4j_url_shortcut.service.dto.UsersDTO;
import ru.akaleganov.job4j_url_shortcut.service.mapper.UsersMapper;
import ru.akaleganov.job4j_url_shortcut.service.util.RandomGeneratorLoginPass;

import javax.management.relation.Role;
import java.util.Collections;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UsersMapper usersMapper;
    private final RandomGeneratorLoginPass randomGeneratorLoginPass;
    private final RolesRepository rolesRepository;
    private static final Logger LOGGER = Logger.getLogger(UsersService.class);

    UsersService(UsersRepository usersRepository, BCryptPasswordEncoder encoder, UsersMapper usersMapper, RandomGeneratorLoginPass randomGeneratorLoginPass, RolesRepository rolesRepository) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = encoder;
        this.usersMapper = usersMapper;
        this.randomGeneratorLoginPass = randomGeneratorLoginPass;
        this.rolesRepository = rolesRepository;
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
     * @param url ссылка на домен, каждый пользователь может дбавить только один домен
     * @return {@link UsersDTO}
     */
    public UsersDTO createUsersByUrl(String url) {
        if (this.usersRepository.findByUrl(url).isPresent()) {
            LOGGER.debug("пользователь с url " + url + " найден и  не будет добавлен в БД");
            return new UsersDTO();
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
    }
}
