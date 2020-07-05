package ru.akaleganov.url_shortcut.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.akaleganov.url_shortcut.domain.User;
import ru.akaleganov.url_shortcut.repository.UserRepository;

@Service
public class UserDetailServiceCustom implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User loadUserByUsername(String login) throws UsernameNotFoundException {
        User users = this.userRepository.findByLogin(login).orElse(new User());
        if (users.getLogin() == null) {
            throw new UsernameNotFoundException(login);
        }
        return users;
    }
}
