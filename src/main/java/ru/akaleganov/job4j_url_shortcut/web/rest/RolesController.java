package ru.akaleganov.job4j_url_shortcut.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.akaleganov.job4j_url_shortcut.domain.Role;
import ru.akaleganov.job4j_url_shortcut.repository.RoleRepository;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RolesController {
    private final RoleRepository roleRepository;

    public RolesController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     *
     * @return получить список всех ролей
     */
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> findAllRoles() {
        return ResponseEntity.ok().body(this.roleRepository.findAll());
    }
}
