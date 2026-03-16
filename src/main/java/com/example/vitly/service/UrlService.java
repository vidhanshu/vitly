package com.example.vitly.service;

import com.example.vitly.entity.UrlMapping;
import com.example.vitly.repository.UrlRepository;
import com.example.vitly.util.Base62Encoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String createShortUrl(String longUrl) {
        UrlMapping url = new UrlMapping();
        url.setLongUrl(longUrl);
        url.setCreatedAt(LocalDateTime.now());
        urlRepository.save(url);

        return "TEMP"; // TODO: implement encoding
    }

    public String getOriginalUrl(String shortCode) {
        UrlMapping mapping = urlRepository
                .findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("Url not found!"));

        return mapping.getLongUrl();
    }
}
