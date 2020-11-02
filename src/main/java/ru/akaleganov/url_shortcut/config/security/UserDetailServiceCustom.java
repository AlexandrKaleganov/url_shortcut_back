package ru.akaleganov.url_shortcut.config.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.akaleganov.url_shortcut.domain.User;
import ru.akaleganov.url_shortcut.repository.UserRepository;

/**
 * The type User detail service custom.
 */
@Service
@AllArgsConstructor
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
