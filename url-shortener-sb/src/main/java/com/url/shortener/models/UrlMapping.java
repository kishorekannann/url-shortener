package com.url.shortener.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class UrlMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalUrl;
    private String shortUrl;
    private LocalDateTime createdDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "urlMapping")
    @JsonIgnore
    private List<ClickEvent> clickEvents;

    public int getClickCount() {
        return clickEvents == null ? 0 : clickEvents.size();
    }
}
