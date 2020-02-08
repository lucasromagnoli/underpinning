package br.com.lucasromagnoli.javaee.underpinning.rest.security.jwt;

import br.com.lucasromagnoli.javaee.underpinning.domain.model.SystemUser;
import br.com.lucasromagnoli.javaee.underpinning.domain.service.SystemUserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author github.com/lucasromagnoli
 * @since 03/02/2020
 */
public class JwtAuthenticationService {
    private PublicKey publicKey;
    private PrivateKey privateKey;

    @Autowired
    SystemUserService systemUserService;

    public JwtAuthenticationService(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public JwtAppAuthorized authenticateSystemUser(SystemUser systemUser) {
        SystemUser userAuthenticated = systemUserService.findToAuthenticate(systemUser.getUsername(), systemUser.getPassword());
        return new JwtAppAuthorized(createAuthorizationToken(userAuthenticated));
    }

    public String createAuthorizationToken(SystemUser systemUser) {
        return Jwts.builder()
                .setSubject(systemUser.getUsername())
                .claim("user_id", systemUser.getId())
                .claim("roles", systemUser.getRoles())
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(JwtParametersConfig.DEFAULT_EXPIRATION_TOKEN_IN_MINUTES).toInstant()))
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    public Claims validateAuthorizationToken(String token) {
        return Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(token.replace(JwtParametersConfig.TOKEN_PREFIX, ""))
                .getBody();
    }
}
