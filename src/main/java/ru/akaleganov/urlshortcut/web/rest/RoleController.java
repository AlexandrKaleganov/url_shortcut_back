package ru.akaleganov.urlshortcut.web.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.akaleganov.urlshortcut.domain.Role;
import ru.akaleganov.urlshortcut.repository.RoleRepository;

import java.util.List;

/**
 * The type Role controller.
 */
@RestController
@RequestMapping("/api")
public class RoleController {
    private final RoleRepository roleRepository;

    /**
     * Instantiates a new Role controller.
     *
     * @param roleRepository the role repository
     */
    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Find all roles response entity.
     *
     * @return получить список всех ролей
     */
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> findAllRoles() {
        return ResponseEntity.ok().body(this.roleRepository.findAll());
    }
}
