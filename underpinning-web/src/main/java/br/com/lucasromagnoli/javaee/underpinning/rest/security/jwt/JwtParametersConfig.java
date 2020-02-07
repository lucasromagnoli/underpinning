package br.com.lucasromagnoli.javaee.underpinning.rest.security.jwt;

/**
 * @author github.com/lucasromagnoli
 * @since 07/02/2020
 */
public class JwtParametersConfig {
    private JwtParametersConfig() {}

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_BODY_ROLES = "roles";
    public static final String TOKEN_BODY_ROLES_NAME = "name";
    public static final Integer DEFAULT_EXPIRATION_TOKEN_IN_MINUTES = 30;
}
