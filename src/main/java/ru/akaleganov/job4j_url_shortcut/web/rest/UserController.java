package ru.akaleganov.job4j_url_shortcut.web.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.akaleganov.job4j_url_shortcut.service.UserService;
import ru.akaleganov.job4j_url_shortcut.service.dto.UserDTO;
import ru.akaleganov.job4j_url_shortcut.service.UserFilter;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<Page<UserDTO>> getAllUsers(UserFilter criteria, Pageable pageable) {
        return ResponseEntity.ok().body(this.userService.findAll(criteria, pageable));
    }
    @GetMapping(value = "/users/{login}")
    public ResponseEntity<UserDTO> findUserByLogin(@PathVariable String login) {
        return ResponseEntity.ok().body(this.userService.getUserByLogin(login));
    }
    @PostMapping(value = "/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok().body(this.userService.create(user));
    }

    @PutMapping(value = "/users")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO user) {
        return ResponseEntity.ok().body(this.userService.updateUser(user));
    }
}
