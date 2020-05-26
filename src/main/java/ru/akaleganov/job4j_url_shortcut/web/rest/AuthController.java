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
import ru.akaleganov.job4j_url_shortcut.config.security.UserDetailServiceCustom;
import ru.akaleganov.job4j_url_shortcut.config.security.jwt.JwtTokenUtil;
import ru.akaleganov.job4j_url_shortcut.domain.Role;
import ru.akaleganov.job4j_url_shortcut.domain.User;
import ru.akaleganov.job4j_url_shortcut.service.UserService;
import ru.akaleganov.job4j_url_shortcut.service.dto.AuthTokenResponseDTO;
import ru.akaleganov.job4j_url_shortcut.service.dto.UserDTO;
import ru.akaleganov.job4j_url_shortcut.service.mapper.AuthTokenResponseMapper;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthController {
    private static final Logger LOGGER = Logger.getLogger(AspectLogger.class);

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailServiceCustom userDetailServiceCustom;
    private final AuthTokenResponseMapper authTokenResponseMapper;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserDetailServiceCustom userDetailServiceCustom, AuthTokenResponseMapper authTokenResponseMapper, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailServiceCustom = userDetailServiceCustom;
        this.authTokenResponseMapper = authTokenResponseMapper;
        this.userService = userService;
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthTokenResponseDTO> register(@RequestBody User loginUser) {
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
        final UserDetails user = userDetailServiceCustom.loadUserByUsername(loginUser.getLogin());
        final String token = jwtTokenUtil.doGenerateToken(user);
        return ResponseEntity.ok(this.authTokenResponseMapper.toDTO(token,
                this.userDetailServiceCustom.loadUserByUsername(loginUser.getLogin())));
    }

    @GetMapping(value = "/auth/roles")
    public ResponseEntity<List<Role>> getRolesThisUser(Principal principal) {
        return ResponseEntity.ok().body(this.userService.getUserByLogin(principal.getName()).getRoles());
    }

    @PostMapping("/auth/registry")
    public ResponseEntity<UserDTO> saveUser(@RequestBody String url) {
        return ResponseEntity.ok(this.userService.createUsersByUrl(url));
    }
}
