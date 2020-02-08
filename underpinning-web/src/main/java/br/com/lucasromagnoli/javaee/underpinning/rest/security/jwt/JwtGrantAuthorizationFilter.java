package br.com.lucasromagnoli.javaee.underpinning.rest.security.jwt;

import br.com.lucasromagnoli.javaee.underpinning.rest.model.MessageType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author github.com/lucasromagnoli
 * @since 05/02/2020
 */
public class JwtGrantAuthorizationFilter extends GenericFilterBean {

    @Autowired
    JwtAuthenticationService jwtAuthenticationService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        HttpServletResponse response = ((HttpServletResponse) servletResponse);

        try {
            logger.info("[---- STARTING TO TOKEN VALIDATION ----]");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (authentication == null && token != null) {
                logger.info("[---- TRYING TO VALIDATE TOKEN ----]");
                Claims claims = this.jwtAuthenticationService.validateAuthorizationToken(token);
                JwtAuthenticatedUser jwtAuthenticatedUser = JwtSupport.createAuthenticatedUser(claims);

                authentication = new JwtAuthenticationToken(jwtAuthenticatedUser);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                logger.info("[---- FINISHING TOKEN VALIDATION ----]");
            }

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException | SignatureException e) {
            logger.warn(e.getMessage());
            JwtExceptionSupport.createErrorResponse(response, e.getMessage(), HttpStatus.FORBIDDEN, MessageType.WARNING);
        } catch (MalformedJwtException e) {
            logger.warn(e.getMessage());
            JwtExceptionSupport.createErrorResponse(response, e.getMessage(), HttpStatus.BAD_REQUEST, MessageType.WARNING);
        } catch (Exception e) {
            logger.error(e.getMessage());
            JwtExceptionSupport.createErrorResponse(response, e.getMessage(), HttpStatus.FORBIDDEN, MessageType.ERROR);
        }
    }
}
