package ru.akaleganov.url_shortcut.web.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.akaleganov.url_shortcut.service.UserService;
import ru.akaleganov.url_shortcut.service.dto.UserDTO;
import ru.akaleganov.url_shortcut.service.UserFilter;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param userService the user service
     */
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Gets all users.
     *
     * @param criteria the criteria
     * @param pageable the pageable
     * @return the all users
     */
    @GetMapping(value = "/users")
    public ResponseEntity<Page<UserDTO>> getAllUsers(UserFilter criteria, Pageable pageable) {
        return ResponseEntity.ok().body(this.userService.findAll(criteria, pageable));
    }

    /**
     * Find user by login response entity.
     *
     * @param login the login
     * @return the response entity
     */
    @GetMapping(value = "/users/{login}")
    public ResponseEntity<UserDTO> findUserByLogin(@PathVariable String login) {
        return ResponseEntity.ok().body(this.userService.getUserByLogin(login));
    }

    /**
     * Create user response entity.
     *
     * @param user the user
     * @return the response entity
     */
    @PostMapping(value = "/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok().body(this.userService.create(user));
    }

    /**
     * Update user response entity.
     *
     * @param user the user
     * @return the response entity
     */
    @PutMapping(value = "/users")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok().body(this.userService.updateUser(user));
    }
}
