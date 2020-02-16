package br.com.lucasromagnoli.javaee.underpinning.rest.security.jwt;

import br.com.lucasromagnoli.javaee.underpinning.commons.exception.UnderpinningAuthenticationFail;
import br.com.lucasromagnoli.javaee.underpinning.domain.model.SystemUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author github.com/lucasromagnoli
 * @since 03/02/2020
 */
public class JwtAuthenticationService {
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private PasswordEncoder passwordEncoder;

    public JwtAuthenticationService(PublicKey publicKey, PrivateKey privateKey, PasswordEncoder passwordEncoder) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
        this.passwordEncoder = passwordEncoder;
    }

    public JwtAppAuthorized authenticateSystemUser(SystemUser userFilled, SystemUser userDatabase) throws UnderpinningAuthenticationFail {
        if (!passwordEncoder.matches(userFilled.getPassword(), userDatabase.getPassword())) {
            throw new UnderpinningAuthenticationFail(JwtParametersConfig.AUTHENTICATION_FAIL_PASSWORD_DOESNT_MATCH);
        }

        return new JwtAppAuthorized(createAuthorizationToken(
                userDatabase.getUsername(),
                userDatabase.getId().toString(),
                userDatabase.getRoles().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())
        ));
    }

    public String createAuthorizationToken(String username, String id, List<String> roles) {
        return Jwts.builder()
                .setSubject(username)
                .claim("user_id", id)
                .claim("roles", roles)
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
