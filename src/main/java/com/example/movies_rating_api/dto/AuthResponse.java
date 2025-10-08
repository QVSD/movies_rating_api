package com.example.movies_rating_api.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * The ideea is pretty simple here.
 * JWT has an amount of time after it expires ~1h. After that instead of forcing the user into logging in again, you can
 * give him a refresh token which generates a new access token.
 * */
@Getter
public class AuthResponse {
    private final String accessToken;
    private final String refreshToken;

    public AuthResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
