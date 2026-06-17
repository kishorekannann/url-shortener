package com.url.shortener.service;

import com.url.shortener.models.UrlMapping;
import com.url.shortener.models.User;
import com.url.shortener.repository.UrlMappingRepository;
import com.url.shortener.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UrlService {

    private final UrlMappingRepository urlMappingRepository;
    private final UserRepository userRepository;

    public UrlService(UrlMappingRepository urlMappingRepository, UserRepository userRepository) {
        this.urlMappingRepository = urlMappingRepository;
        this.userRepository = userRepository;
    }

    public String createShortUrl(String originalUrl) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String shortUrl = generateShortUrl();
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setCreatedDate(LocalDateTime.now());
        urlMapping.setUser(user);
        urlMappingRepository.save(urlMapping);
        return shortUrl;
    }

    public Optional<UrlMapping> getOriginalUrl(String shortUrl) {
        return urlMappingRepository.findByShortUrl(shortUrl);
    }

    public List<UrlMapping> getUrlsForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return urlMappingRepository.findAllByUser(user);
    }

    private String generateShortUrl() {
        // A simple and naive approach to generate a short URL.
        // In a real-world application, a more robust algorithm should be used.
        return "short_" + System.currentTimeMillis();
    }
}
