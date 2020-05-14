package ru.akaleganov.job4j_url_shortcut.web.rest;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.akaleganov.job4j_url_shortcut.aspect.AspectLogger;
import ru.akaleganov.job4j_url_shortcut.config.security.UsersDetailServiceCustom;
import ru.akaleganov.job4j_url_shortcut.config.security.jwt.JwtTokenUtil;
import ru.akaleganov.job4j_url_shortcut.domain.Roles;
import ru.akaleganov.job4j_url_shortcut.domain.Users;
import ru.akaleganov.job4j_url_shortcut.service.UsersService;
import ru.akaleganov.job4j_url_shortcut.service.dto.AuthTokenResponseDTO;
import ru.akaleganov.job4j_url_shortcut.service.dto.UsersDTO;
import ru.akaleganov.job4j_url_shortcut.service.mapper.AuthTokenResponseMapper;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    private static final Logger LOGGER = Logger.getLogger(AspectLogger.class);

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;
    private final UsersDetailServiceCustom usersDetailServiceCustom;
    private final AuthTokenResponseMapper authTokenResponseMapper;
    private final UsersService usersService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UsersDetailServiceCustom usersDetailServiceCustom, AuthTokenResponseMapper authTokenResponseMapper, UsersService usersService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.usersDetailServiceCustom = usersDetailServiceCustom;
        this.authTokenResponseMapper = authTokenResponseMapper;
        this.usersService = usersService;
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthTokenResponseDTO> register(@RequestBody Users loginUser) {
        LOGGER.debug("SIGN IN REQUEST:");
        LOGGER.debug(loginUser.toString());
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getLogin(),
                        loginUser.getPassword()
                )
        );


        SecurityContextHolder.getContext().setAuthentication(authentication);
        LOGGER.debug("CURRENT auth user {}" + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        final UserDetails user = usersDetailServiceCustom.loadUserByUsername(loginUser.getLogin());
        final String token = jwtTokenUtil.doGenerateToken(user);
        return ResponseEntity.ok(this.authTokenResponseMapper.toDTO(token,
                this.usersDetailServiceCustom.loadUserByUsername(loginUser.getLogin())));
    }

    @GetMapping(value = "/auth/roles")
    public ResponseEntity<List<Roles>> getRolesThisUser(Principal principal) {
        return ResponseEntity.ok().body(this.usersService.getUsersByLogin(principal.getName()).getRoles());
    }

    @PostMapping("/auth/registry")
    public ResponseEntity<UsersDTO> saveUser(@RequestBody String url) {
        return ResponseEntity.ok(this.usersService.createUsersByUrl(url));
    }
}
