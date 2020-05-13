package ru.akaleganov.job4j_url_shortcut.web.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.akaleganov.job4j_url_shortcut.service.UsersService;
import ru.akaleganov.job4j_url_shortcut.service.dto.UsersDTO;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UsersController {
    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }
    @GetMapping(value = "/users")
    public ResponseEntity<List<UsersDTO>> getAllUsers(Pageable pageable) {
        Page<UsersDTO> page = this.usersService.findAll(pageable);
        HttpHeaders headers = new HttpHeaders();
        headers.add("totalElements", String.valueOf(page.getTotalElements()));
        headers.add("totalPages", String.valueOf(page.getTotalPages()));
        return ResponseEntity.ok().body(page.getContent());
    }
}
