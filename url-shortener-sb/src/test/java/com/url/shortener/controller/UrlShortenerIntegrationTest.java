package com.url.shortener.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.url.shortener.dtos.LoginRequest;
import com.url.shortener.dtos.RegisterRequest;
import com.url.shortener.dtos.UrlRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UrlShortenerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testUrlShortenerFlow() throws Exception {
        String testUsername = "testuser_" + System.currentTimeMillis();
        // Register a new user
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername(testUsername);
        registerRequest.setPassword("password");
        registerRequest.setEmail("test@example.com");

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(registerRequest)))
                .andExpect(status().isOk());

        // Login with the new user
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(testUsername);
        loginRequest.setPassword("password");

        MvcResult loginResult = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String token = objectMapper.readTree(loginResult.getResponse().getContentAsString()).get("token").asText();

        // Create a short URL
        UrlRequest urlRequest = new UrlRequest();
        urlRequest.setOriginalUrl("https://www.google.com");

        MvcResult createUrlResult = mockMvc.perform(post("/api/urls")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(urlRequest)))
                .andExpect(status().isOk())
                .andReturn();

        String shortUrl = createUrlResult.getResponse().getContentAsString();

        // Redirect to the original URL
        mockMvc.perform(get("/" + shortUrl))
                .andExpect(status().isFound())
                .andExpect(header().string("Location", "https://www.google.com"));
    }
}
