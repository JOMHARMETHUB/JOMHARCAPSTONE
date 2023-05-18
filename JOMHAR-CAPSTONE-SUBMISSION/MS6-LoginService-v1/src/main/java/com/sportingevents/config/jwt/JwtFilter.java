package com.sportingevents.config.jwt;

import com.sportingevents.common.constant.ApiEndpoint;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private TokenManager tokenManager;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            if(!request.getRequestURI().equals(ApiEndpoint.LOGIN) && !request.getRequestURI().equals(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.USERS)
                    && !request.getRequestURI().equals(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.USERS + ApiEndpoint.VALIDATE_JWT_TOKEN)
            && !request.getRequestURI().equals(ApiEndpoint.API + ApiEndpoint.VERSION + ApiEndpoint.USERS + ApiEndpoint.GET_EMAIL_FROM_JWT)) {
                String tokenHeader = request.getHeader("Authorization");
                String username = null;
                String token = null;
                if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
                    token = tokenHeader.substring(7);
                    username = tokenManager.getUsernameFromToken(token);
                } else {
                    LOGGER.error("Bearer String not found in token");
                }
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    boolean isValidToken = tokenManager.validateJwtToken(token, userDetails);
                    if (isValidToken) {
                        UsernamePasswordAuthenticationToken
                                authenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null,
                                userDetails.getAuthorities());
                        authenticationToken.setDetails(new
                                WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            }
            filterChain.doFilter(request, response);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Unable to get JWT Token");
        } catch (ExpiredJwtException e) {
            LOGGER.error("JWT Token has expired");
        } catch (MalformedJwtException e) {
            LOGGER.error("Unable to read JSON value");
        }
    }
}
