package ru.akaleganov.job4j_url_shortcut.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.akaleganov.job4j_url_shortcut.repository.UsersRepository;
import ru.akaleganov.job4j_url_shortcut.service.dto.UsersDTO;
import ru.akaleganov.job4j_url_shortcut.service.mapper.UsersMapper;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UsersMapper usersMapper;

    UsersService(UsersRepository usersRepository, BCryptPasswordEncoder encoder, UsersMapper usersMapper) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = encoder;
        this.usersMapper = usersMapper;
    }

    /**
     * получить всех пользаков c пагинацией  (гадо пдумать как реализовать)
     * @param pageable пагинация
     * @return {@link UsersDTO}
     */
    public Page<UsersDTO> findAll(Pageable pageable) {
        return this.usersRepository.findAll(pageable).map(this.usersMapper::usersToUsersDTO);
    }

    public UsersDTO create (String url) {
        return null;
    }
}
