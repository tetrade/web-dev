package ru.nicetu.predator.u.modules.launch.chart.config.jwt;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.util.WebUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public JwtAuthenticationFilter(RequestMatcher requestMatcher, AuthenticationManager authenticationManager) {
        super(requestMatcher , authenticationManager);

        setContinueChainBeforeSuccessfulAuthentication(true);

        setSessionAuthenticationStrategy(
                (Authentication authentication, HttpServletRequest request, HttpServletResponse response) ->
                        SecurityContextHolder.getContext().setAuthentication(authentication)
        );

        setAuthenticationSuccessHandler((request, response, authentication) -> {});
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        String jwt = parseJwt(request);
        Authentication authenticationToken = new JwtAuthenticationToken(jwt);
        return getAuthenticationManager().authenticate(authenticationToken);
    }

    private String parseJwt(HttpServletRequest request) {
        Cookie c = WebUtils.getCookie(request, "jwt");
        if (c != null) return c.getValue();
        return null;
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
        log.debug("Unsuccessful authentication: {}. IP: {}", failed.getMessage(), request.getRemoteAddr());
    }
}
