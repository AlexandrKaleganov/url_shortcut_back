package ru.akaleganov.urlshortcut.web.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.akaleganov.urlshortcut.service.UserFilter;
import ru.akaleganov.urlshortcut.service.UserServiceImpl;
import ru.akaleganov.urlshortcut.service.dto.UserDTO;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/api")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    /**
     * Instantiates a new User controller.
     *
     * @param userServiceImpl
     *         the user service
     */
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    /**
     * Gets all users.
     *
     * @param criteria
     *         the criteria
     * @param pageable
     *         the pageable
     *
     * @return the all users
     */
    @GetMapping(value = "/users")
    public ResponseEntity<Page<UserDTO>> getAllUsers(UserFilter criteria, Pageable pageable) {
        return ResponseEntity.ok().body(this.userServiceImpl.findAll(criteria, pageable));
    }

    /**
     * Find user by login response entity.
     *
     * @param login
     *         the login
     *
     * @return the response entity
     */
    @GetMapping(value = "/users/{login}")
    public ResponseEntity<UserDTO> findUserByLogin(@PathVariable String login) {
        return ResponseEntity.ok().body(this.userServiceImpl.getUserByLogin(login));
    }

    /**
     * Create user response entity.
     *
     * @param user
     *         the user
     *
     * @return the response entity
     */
    @PostMapping(value = "/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok().body(this.userServiceImpl.create(user));
    }

    /**
     * Update user response entity.
     *
     * @param user
     *         the user
     *
     * @return the response entity
     */
    @PutMapping(value = "/users")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok().body(this.userServiceImpl.updateUser(user));
    }

}
