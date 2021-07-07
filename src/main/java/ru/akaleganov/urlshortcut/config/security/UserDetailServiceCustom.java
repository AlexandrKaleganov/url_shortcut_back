package ru.akaleganov.urlshortcut.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.repository.UserRepository;

/**
 * The type User detail service custom.
 */
@Service
@RequiredArgsConstructor
public class UserDetailServiceCustom implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String login) throws UsernameNotFoundException {
        User users = this.userRepository.findByLogin(login).orElse(new User());
        if (users.getLogin() == null) {
            throw new UsernameNotFoundException(login);
        }
        return users;
    }

}
