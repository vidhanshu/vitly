package com.example.vitly.controller;

import com.example.vitly.service.UrlService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlController {
    private final UrlService urlService;

    UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public String shortenUrl(String longUrl) {
        return urlService.createShortUrl(longUrl);
    }

    @GetMapping("/{shortCode}")
    public String redirect(@PathVariable String shortCode) {
        return urlService.getOriginalUrl(shortCode);
    }
}
