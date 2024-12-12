package ru.nicetu.predator.u.modules.launch.chart.config.jwt;

import java.util.Objects;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String jwt = (String) authentication.getCredentials();
        if (jwt == null || !jwtUtils.validateJwtToken(jwt)) {
            throw new BadCredentialsException("Invalid JWT token");
        }

        String username;
        try {
            username = jwtUtils.getUserNameFromJwtToken(jwt);
        } catch (JwtException e) {
            throw new BadCredentialsException("Invalid JWT token", e);
        }

        if (username == null) {
            throw new BadCredentialsException("JWT token is empty");
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return new JwtAuthenticationToken(userDetails);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return Objects.equals(authentication, JwtAuthenticationToken.class);
    }
}
