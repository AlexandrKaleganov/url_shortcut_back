package ru.akaleganov.urlshortcut.web.rest;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.akaleganov.urlshortcut.aspect.AspectLogger;
import ru.akaleganov.urlshortcut.config.security.UserDetailServiceCustom;
import ru.akaleganov.urlshortcut.config.security.jwt.JwtTokenUtil;
import ru.akaleganov.urlshortcut.domain.Role;
import ru.akaleganov.urlshortcut.domain.User;
import ru.akaleganov.urlshortcut.service.UserService;
import ru.akaleganov.urlshortcut.service.dto.AuthTokenResponseDTO;
import ru.akaleganov.urlshortcut.service.dto.UserDTO;
import ru.akaleganov.urlshortcut.service.mapper.AuthTokenResponseMapper;

import java.security.Principal;
import java.util.List;

/**
 * The type Auth controller.
 */
@RestController
@RequestMapping("/api")
public class AuthController {
    private static final Logger LOGGER = Logger.getLogger(AspectLogger.class);

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailServiceCustom userDetailServiceCustom;
    private final AuthTokenResponseMapper authTokenResponseMapper;
    private final UserService userService;

    /**
     * Instantiates a new Auth controller.
     *
     * @param authenticationManager   the authentication manager
     * @param jwtTokenUtil            the jwt token util
     * @param userDetailServiceCustom the user detail service custom
     * @param authTokenResponseMapper the auth token response mapper
     * @param userService             the user service
     */
    public AuthController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserDetailServiceCustom userDetailServiceCustom, AuthTokenResponseMapper authTokenResponseMapper, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailServiceCustom = userDetailServiceCustom;
        this.authTokenResponseMapper = authTokenResponseMapper;
        this.userService = userService;
    }

    /**
     * Register response entity.
     *
     * @param loginUser the login user
     * @return the response entity
     */
    @PostMapping("/auth")
    public ResponseEntity<AuthTokenResponseDTO> register(@RequestBody User loginUser) {
        LOGGER.debug("SIGN IN REQUEST:");
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getLogin(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final UserDetails user = userDetailServiceCustom.loadUserByUsername(loginUser.getLogin());
        final String token = jwtTokenUtil.doGenerateToken(user);
        return ResponseEntity.ok(this.authTokenResponseMapper.toDTO(token,
                this.userDetailServiceCustom.loadUserByUsername(loginUser.getLogin())));
    }

    /**
     * Gets roles this user.
     *
     * @param principal the principal
     * @return the roles this user
     */
    @GetMapping(value = "/auth/roles")
    public ResponseEntity<List<Role>> getRolesThisUser(Principal principal) {
        return ResponseEntity.ok().body(this.userService.getUserByLogin(principal.getName()).getRoles());
    }

    /**
     * Save user response entity.
     *
     * @param domain the domain
     * @return the response entity
     */
    @PostMapping("/auth/registry")
    public ResponseEntity<UserDTO> saveUser(@RequestBody String domain) {
        return ResponseEntity.ok(this.userService.createUsersByUrl(domain));
    }
}
