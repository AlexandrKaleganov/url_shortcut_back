package ru.akaleganov.job4j_url_shortcut.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.akaleganov.job4j_url_shortcut.domain.Users;
import ru.akaleganov.job4j_url_shortcut.repository.UsersRepository;

@Service
public class UsersService {
    private UsersRepository usersRepository;

    UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Page<Users> findAll(Pageable pageable) {
        return this.usersRepository.findAll(pageable);
    }
}
