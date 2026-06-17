package com.url.shortener.security.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtAuthenticatonResponse {
    private String token;
    private String tokenType = "Bearer";

    public JwtAuthenticatonResponse(String token) {
        this.token = token;
    }
}
