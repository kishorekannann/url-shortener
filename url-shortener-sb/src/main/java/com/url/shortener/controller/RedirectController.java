package com.url.shortener.controller;

import com.url.shortener.models.UrlMapping;
import com.url.shortener.service.UrlService;
import com.url.shortener.service.ClickService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class RedirectController {

    private final UrlService urlService;
    private final ClickService clickService;

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectToOriginalUrl(@PathVariable String shortUrl) {
        Optional<UrlMapping> urlMappingOptional = urlService.getOriginalUrl(shortUrl);
        if (urlMappingOptional.isPresent()) {
            UrlMapping urlMapping = urlMappingOptional.get();
            clickService.recordClick(urlMapping);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .header("Location", urlMapping.getOriginalUrl())
                    .build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
