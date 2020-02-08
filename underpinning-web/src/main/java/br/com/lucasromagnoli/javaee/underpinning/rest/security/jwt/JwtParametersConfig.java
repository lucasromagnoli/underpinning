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
    public static final String TOKEN_NOT_FOUND =  "The security token is missing from your request";
    public static final String AUTHENTICATION_FAIL_PASSWORD_DOESNT_MATCH = "Password doesn't match";

    public static final Integer DEFAULT_EXPIRATION_TOKEN_IN_MINUTES = 30;
}
