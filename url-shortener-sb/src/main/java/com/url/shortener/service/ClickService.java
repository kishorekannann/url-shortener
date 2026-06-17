package com.url.shortener.service;

import com.url.shortener.models.ClickEvent;
import com.url.shortener.models.UrlMapping;
import com.url.shortener.repository.ClickEventRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ClickService {

    private final ClickEventRepository clickEventRepository;

    public void recordClick(UrlMapping urlMapping) {
        ClickEvent clickEvent = new ClickEvent();
        clickEvent.setUrlMapping(urlMapping);
        clickEvent.setClickDate(LocalDateTime.now());
        clickEventRepository.save(clickEvent);
    }
}
