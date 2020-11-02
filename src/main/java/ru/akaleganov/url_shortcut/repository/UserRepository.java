package ru.akaleganov.url_shortcut.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.akaleganov.url_shortcut.domain.User;

import java.util.Optional;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    /**
     * Find by login optional.
     *
     * @param login the login
     * @return the optional
     */
    Optional<User> findByLogin(String login);

    /**
     * Find by domain optional.
     *
     * @param domain the domain
     * @return the optional
     */
    Optional<User> findByDomain(String domain);
}
