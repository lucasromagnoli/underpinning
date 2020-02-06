package br.com.lucasromagnoli.javaee.underpinning.rest.service;

import br.com.lucasromagnoli.javaee.underpinning.domain.model.SystemUser;
import br.com.lucasromagnoli.javaee.underpinning.domain.service.SystemUserService;
import br.com.lucasromagnoli.javaee.underpinning.rest.model.JwtAppAuthorized;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author github.com/lucasromagnoli
 * @since 03/02/2020
 */
public class UnderpinningJwtSecurityService {
    private static final String TOKEN_PREFIX = "Bearer ";
    private PublicKey publicKey;
    private PrivateKey privateKey;

    @Autowired
    SystemUserService systemUserService;

    public UnderpinningJwtSecurityService(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public JwtAppAuthorized authenticateSystemUser(SystemUser systemUser) {
        SystemUser userAuthenticated = systemUserService.findToAuthenticate(systemUser.getUsername(), systemUser.getPassword());
        return new JwtAppAuthorized(createAuthorizationToken(userAuthenticated));
    }

    public String createAuthorizationToken(SystemUser systemUser) {
        return Jwts.builder()
                .claim("user_id", systemUser.getId())
                .claim("roles", systemUser.getRoles())
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    public Claims validateAuthorizationToken(String token) {
        return Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody();
    }
}
