package br.com.lucasromagnoli.javaee.underpinning.rest.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * @author github.com/lucasromagnoli
 * @since 07/02/2020
 */
public class JwtSupport {
    private JwtSupport() {}

    public static JwtAuthenticatedUser createAuthenticatedUser(Claims claims) {
        String username = claims.getSubject();
        List<String> claimGrants = (List<String>) claims.get(JwtParametersConfig.TOKEN_BODY_ROLES);
        List<SimpleGrantedAuthority> listRoles = new ArrayList<>();

        for (String grants : claimGrants) {
            listRoles.add(new SimpleGrantedAuthority(grants));
        }

        return new JwtAuthenticatedUser(username, listRoles);
    }
}
