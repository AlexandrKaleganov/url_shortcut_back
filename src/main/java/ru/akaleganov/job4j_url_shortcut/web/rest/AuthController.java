package ru.akaleganov.job4j_url_shortcut.web.rest;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.akaleganov.job4j_url_shortcut.aspect.AspectLogger;
import ru.akaleganov.job4j_url_shortcut.config.security.UsersDetailServiceCustom;
import ru.akaleganov.job4j_url_shortcut.config.security.jwt.JwtTokenUtil;
import ru.akaleganov.job4j_url_shortcut.domain.Users;
import ru.akaleganov.job4j_url_shortcut.service.UsersService;
import ru.akaleganov.job4j_url_shortcut.service.dto.AuthTokenResponseDTO;
import ru.akaleganov.job4j_url_shortcut.service.dto.UsersDTO;
import ru.akaleganov.job4j_url_shortcut.service.mapper.AuthTokenResponseMapper;


@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200")
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

    @PostMapping("/authenticate")
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
        LOGGER.debug("CURRENT auth user {}", (Throwable) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        final Users user = usersDetailServiceCustom.loadUserByUsername(loginUser.getLogin());
        final String token = jwtTokenUtil.generateToken(user);
        return ResponseEntity.ok(this.authTokenResponseMapper.toDTO(token, user));
    }

    @PostMapping("/signup")
    public ResponseEntity<UsersDTO> saveUser(@RequestBody String url) {
        return ResponseEntity.ok(this.usersService.create(url));
    }
}
