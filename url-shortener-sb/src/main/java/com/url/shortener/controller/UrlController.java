package com.url.shortener.controller;

import com.url.shortener.dtos.UrlRequest;
import com.url.shortener.models.UrlMapping;
import com.url.shortener.service.UrlService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/urls")
@AllArgsConstructor
public class UrlController {

    private final UrlService urlService;
    private final com.url.shortener.service.ClickService clickService;

    @PostMapping
    public ResponseEntity<String> createShortUrl(@RequestBody UrlRequest urlRequest) {
        String shortUrl = urlService.createShortUrl(urlRequest.getOriginalUrl());
        return ResponseEntity.ok(shortUrl);
    }

    @GetMapping
    public ResponseEntity<List<UrlMapping>> getUserUrls() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<UrlMapping> urls = urlService.getUrlsForUser(username);
        return ResponseEntity.ok(urls);
    }
}
