package br.com.lucasromagnoli.javaee.underpinning.rest.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
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
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        HttpServletResponse response = ((HttpServletResponse) servletResponse);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ) {
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);
            Claims claims = this.jwtAuthenticationService.validateAuthorizationToken(token);
            JwtAuthenticatedUser jwtAuthenticatedUser = JwtSupport.createAuthenticatedUser(claims);

            authentication = new JwtAuthenticationToken(jwtAuthenticatedUser);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
