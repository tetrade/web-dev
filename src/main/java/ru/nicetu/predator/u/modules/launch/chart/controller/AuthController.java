package ru.nicetu.predator.u.modules.launch.chart.controller;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.nicetu.predator.u.modules.launch.chart.config.jwt.JwtUtils;
import ru.nicetu.predator.u.modules.launch.chart.dto.UserLoginDto;
import ru.nicetu.predator.u.modules.launch.chart.service.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs;
    private AuthService authService;
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @RequestBody UserLoginDto userLoginDto, HttpServletResponse httpServletResponse
            ) {
        Authentication authentication =
                authService.auth(userLoginDto.getLogin(), userLoginDto.getPassword());
        Cookie cookie = createJwtCookie(jwtUtils.generateJwtToken(authentication));
        httpServletResponse.addCookie(cookie);
        return ResponseEntity.ok().build();
    }


    public Cookie createJwtCookie(String jwt) {
        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setPath("/");
        cookie.setMaxAge(jwtExpirationMs);
        return cookie;
    }
}
