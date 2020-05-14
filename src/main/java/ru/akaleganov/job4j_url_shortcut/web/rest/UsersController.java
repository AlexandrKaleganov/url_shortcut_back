package ru.akaleganov.job4j_url_shortcut.web.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.akaleganov.job4j_url_shortcut.repository.RolesRepository;
import ru.akaleganov.job4j_url_shortcut.service.UsersService;
import ru.akaleganov.job4j_url_shortcut.service.dto.UsersDTO;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UsersController {
    private final UsersService usersService;
    private final RolesRepository rolesRepository;

    public UsersController(UsersService usersService, RolesRepository rolesRepository) {
        this.usersService = usersService;
        this.rolesRepository = rolesRepository;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UsersDTO>> getAllUsers(Pageable pageable) {
        Page<UsersDTO> page = this.usersService.findAll(pageable);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Expose-Headers", "totalSize");
        headers.set("totalSize", Long.toString(page.getTotalElements()));
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @PostMapping(value = "/users")
    public ResponseEntity<UsersDTO> createUser(@RequestBody UsersDTO users) {
        return ResponseEntity.ok().body(this.usersService.create(users));
    }
}
