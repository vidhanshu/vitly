package com.example.vitly.service;

import com.example.vitly.entity.UrlMapping;
import com.example.vitly.repository.UrlRepository;
import com.example.vitly.util.Base62Encoder;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
        UrlMapping savedUrl = urlRepository.save(url);

        String shortCode = Base62Encoder.generate(savedUrl.getId());
        savedUrl.setShortCode(shortCode);
        urlRepository.save(savedUrl);

        return shortCode;
    }

    @Cacheable(value = "urls", key = "#shortCode")
    public String getOriginalUrl(String shortCode) {
        System.out.println(">>> CACHE MISS — hitting DB for: " + shortCode);
        UrlMapping mapping = urlRepository
                .findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("Url not found!"));
        return mapping.getLongUrl();
    }

    @CacheEvict(value = "urls", key = "#shortCode")
    public void deleteUrl(String shortCode) {
        urlRepository.findByShortCode(shortCode)
                .ifPresent(urlRepository::delete);
    }
}