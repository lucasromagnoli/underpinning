package br.com.lucasromagnoli.javaee.underpinning.rest.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author github.com/lucasromagnoli
 * @since 07/02/2020
 */
public class JwtSupport {
    private JwtSupport() {}

    public static JwtAuthenticatedUser createAuthenticatedUser(Claims claims) {
        String username = claims.getSubject();
        List<LinkedHashMap<String, Object>> claimGrants = (List<LinkedHashMap<String, Object>>) claims.get(JwtParametersConfig.TOKEN_BODY_ROLES);
        List<SimpleGrantedAuthority> listaRoles = new ArrayList<>();

        for (LinkedHashMap<String, Object> mapGrants : claimGrants) {
            listaRoles.add(new SimpleGrantedAuthority(mapGrants.get(JwtParametersConfig.TOKEN_BODY_ROLES_NAME).toString()));
        }

        return new JwtAuthenticatedUser(username, listaRoles);
    }
}
