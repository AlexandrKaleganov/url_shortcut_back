package ru.akaleganov.urlshortcut.config.security.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Service for working with jwt tokken
 */
@Service
public class JwtTokenUtil implements Serializable {

    private final Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.sessionTime}")
    private long sessionTime;

    /**
     * Gets username from token.
     *
     * @param token
     *         the token
     *
     * @return the username from token
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Is token expired boolean.
     *
     * @param token
     *         the token
     *
     * @return the boolean
     */
    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        log.debug("All claims will be");
        final Claims claims = getAllClaimsFromToken(token);
        log.debug("All claims {}:", claims);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                   .setSigningKey(secretKey)
                   .parseClaimsJws(token)
                   .getBody();
    }

    /**
     * Do generate token string.
     *
     * @param user
     *         the user
     *
     * @return the string
     */
    public String doGenerateToken(UserDetails user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("authorities", user.getAuthorities());
        return Jwts.builder()
                   .setClaims(claims)
                   .setIssuer("akaleganov")
                   .setIssuedAt(new Date(System.currentTimeMillis()))
                   .setExpiration(new Date(System.currentTimeMillis() + sessionTime))
                   .signWith(SignatureAlgorithm.HS256, secretKey)
                   .compact();
    }

}
