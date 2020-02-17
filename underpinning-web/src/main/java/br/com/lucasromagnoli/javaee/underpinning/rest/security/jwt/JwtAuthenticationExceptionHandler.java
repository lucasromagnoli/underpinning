package br.com.lucasromagnoli.javaee.underpinning.rest.security.jwt;

import br.com.lucasromagnoli.javaee.underpinning.rest.model.MessageType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author github.com/lucasromagnoli
 * @since 07/02/2020
 */
public class JwtAuthenticationExceptionHandler implements AuthenticationEntryPoint, AccessDeniedHandler {
    private static final Logger logger = LogManager.getLogger(JwtAuthenticationExceptionHandler.class);

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException {
        logger.warn("[---- JWT EXCEPTION HANDLER (authenticationEntryPoint) ----]");
        JwtExceptionSupport.createErrorResponse(httpServletResponse, JwtParametersConfig.TOKEN_NOT_FOUND, HttpStatus.UNAUTHORIZED, MessageType.WARNING);
    }

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        logger.warn("[---- JWT EXCEPTION HANDLER (accessDeniedHandler) ----]");
        JwtExceptionSupport.createErrorResponse(httpServletResponse, JwtParametersConfig.TOKEN_NOT_FOUND, HttpStatus.FORBIDDEN, MessageType.WARNING);
    }

}