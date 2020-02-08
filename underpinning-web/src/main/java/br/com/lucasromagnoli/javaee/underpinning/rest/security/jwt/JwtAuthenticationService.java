package br.com.lucasromagnoli.javaee.underpinning.rest.security.jwt;

import br.com.lucasromagnoli.javaee.underpinning.domain.model.SystemUser;
import br.com.lucasromagnoli.javaee.underpinning.domain.service.SystemUserService;
import br.com.lucasromagnoli.javaee.underpinning.commons.exception.UnderpinningAuthenticationFail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    private PasswordEncoder passwordEncoder;

    @Autowired
    SystemUserService systemUserService;

    public JwtAuthenticationService(PublicKey publicKey, PrivateKey privateKey, PasswordEncoder passwordEncoder) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.passwordEncoder = passwordEncoder;
    }

    public JwtAppAuthorized authenticateSystemUser(SystemUser systemUser) throws UnderpinningAuthenticationFail {
        SystemUser userAuthenticated = systemUserService.findToAuthenticate(systemUser.getUsername());

        if (!passwordEncoder.matches(systemUser.getPassword(), userAuthenticated.getPassword())) {
            throw new UnderpinningAuthenticationFail(JwtParametersConfig.AUTHENTICATION_FAIL_PASSWORD_DOESNT_MATCH);
        }

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
