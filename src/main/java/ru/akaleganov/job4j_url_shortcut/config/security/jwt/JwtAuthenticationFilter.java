package ru.akaleganov.job4j_url_shortcut.config.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.akaleganov.job4j_url_shortcut.config.security.UsersDetailServiceCustom;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This class checks for the authorization header
 * and authenticates the JWT token and sets the authentication
 * in the context.Doing so will protect our APIs from those
 * requests which do not have any authorization token.
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UsersDetailServiceCustom usersDetailServiceCustom;



    @Value("${jwt.header}")
    private String HEADER_STRING;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("HEADER_STRING = " + HEADER_STRING);
        log.info(request.getHeaderNames().nextElement());
        String header = request.getHeader(HEADER_STRING);
        String username = null;
        String authToken = null;
        log.info("header = " + header);
        if (header != null && header.startsWith("Bearer ")) {
            authToken = header.replace("Bearer ", "");
            try {
                log.info("Auth token is {}", authToken);
                username = jwtTokenUtil.getUsernameFromToken(authToken);
                log.debug("UserName from tokken {}", username);
            } catch (IllegalArgumentException e) {
                log.error("произошла ошибка при получении имени пользователя из токена", e);
            } catch (ExpiredJwtException e) {
                log.warn("токен истек и больше не действителен", e);
            } catch (SignatureException e) {
                log.error("Ошибка аутентификации. Имя пользователя или пароль не действительны.");
            }
        } else {
            log.info("в хедере отсутствует информация об авторизации");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = usersDetailServiceCustom.loadUserByUsername(username);
            log.debug("Current userDetails {}", userDetails);

            if (!jwtTokenUtil.isTokenExpired(authToken)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                log.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(request, response);
    }
}
