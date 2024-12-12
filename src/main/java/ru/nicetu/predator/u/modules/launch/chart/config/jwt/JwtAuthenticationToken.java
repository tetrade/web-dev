package ru.nicetu.predator.u.modules.launch.chart.config.jwt;

import java.util.Objects;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final String jwtToken;
    private final UserDetails userDetails;

    public JwtAuthenticationToken(String jwtToken) {
        super(null);
        this.jwtToken = jwtToken;
        this.userDetails = null;
        this.setAuthenticated(false);
    }

    public JwtAuthenticationToken(UserDetails userDetails) {
        super(Objects.requireNonNull(userDetails.getAuthorities()));
        this.jwtToken = null;
        this.userDetails = userDetails;
        this.setAuthenticated(true);
    }

    @Override
    public String getCredentials() {
        return jwtToken;
    }

    @Override
    public UserDetails getPrincipal() {
        return userDetails;
    }
}
