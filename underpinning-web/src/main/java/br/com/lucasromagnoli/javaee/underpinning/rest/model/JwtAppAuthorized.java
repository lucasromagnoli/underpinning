package br.com.lucasromagnoli.javaee.underpinning.rest.model;

/**
 * @author github.com/lucasromagnoli
 * @since 05/02/2020
 */
public class JwtAppAuthorized {
    private String token;

    public JwtAppAuthorized(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
