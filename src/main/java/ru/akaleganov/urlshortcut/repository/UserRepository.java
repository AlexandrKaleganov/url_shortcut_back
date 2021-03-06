package ru.akaleganov.urlshortcut.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.akaleganov.urlshortcut.domain.User;

/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    /**
     * Find by login optional.
     *
     * @param login
     *         the login
     *
     * @return the optional
     */
    Optional<User> findByLogin(String login);

    /**
     * Find by domain optional.
     *
     * @param domain
     *         the domain
     *
     * @return the optional
     */
    Optional<User> findByDomain(String domain);

}
