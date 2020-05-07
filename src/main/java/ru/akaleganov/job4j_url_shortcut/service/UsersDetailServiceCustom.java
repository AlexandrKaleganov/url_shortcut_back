package ru.akaleganov.job4j_url_shortcut.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.akaleganov.job4j_url_shortcut.domain.Users;
import ru.akaleganov.job4j_url_shortcut.repository.UsersRepository;


public class UsersDetailServiceCustom implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users loadUserByUsername(String login) throws UsernameNotFoundException {
        Users users = this.usersRepository.findByLogin(login).orElse(new Users());
        if (users.getLogin() == null) {
            throw new UsernameNotFoundException(login);
        }
        return users;
    }
}
