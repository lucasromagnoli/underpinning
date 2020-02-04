package br.com.lucasromagnoli.javaee.underpinning.rest.service;

import br.com.lucasromagnoli.javaee.underpinning.domain.model.SystemUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author github.com/lucasromagnoli
 * @since 03/02/2020
 */
public class UnderpinningJwtSecurityService {
    private final String TOKEN_PREFIX = "Bearer ";
    private PublicKey publicKey;
    private PrivateKey privateKey;

    public UnderpinningJwtSecurityService(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
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
