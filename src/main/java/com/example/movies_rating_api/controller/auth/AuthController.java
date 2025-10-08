package com.example.movies_rating_api.controller.auth;

import com.example.movies_rating_api.dto.AuthResponse;
import com.example.movies_rating_api.dto.UserRegisterDTO;
import com.example.movies_rating_api.model.User;
import com.example.movies_rating_api.security.CustomUserDetailsService;
import com.example.movies_rating_api.security.JwtService;
import com.example.movies_rating_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserRegisterDTO registerDTO) {
        User user = userService.registerUserFromDTO(registerDTO);
        String accessToken = jwtService.generateToken(user.getEmail());
        String refreshToken = jwtService.generateRefreshToken(user.getEmail());
        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserRegisterDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        UserDetails user = (UserDetails) authentication.getPrincipal();
        String accessToken = jwtService.generateToken(user.getUsername());
        String refreshToken = jwtService.generateRefreshToken(user.getUsername());

        return ResponseEntity.ok(new AuthResponse(accessToken, refreshToken));
    }

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody String refreshToken) {
        String username = jwtService.extractUsername(refreshToken);
        UserDetails user = customUserDetailsService.loadUserByUsername(username);

        if (jwtService.isTokenValid(refreshToken, user)) {
            String newAccess = jwtService.generateToken(username);
            return ResponseEntity.ok(new AuthResponse(newAccess, refreshToken));
        }

        return ResponseEntity.status(403).build();
    }

}
