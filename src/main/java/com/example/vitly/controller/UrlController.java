package com.example.vitly.controller;

import com.example.vitly.dto.CrateUrlRequest;
import com.example.vitly.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;

@RestController
public class UrlController {
    private final UrlService urlService;

    UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten")
    public String shortenUrl(@RequestBody CrateUrlRequest dto) {
        return urlService.createShortUrl(dto.getLongUrl());
    }

    @GetMapping("/{shortCode}")
    public RedirectView redirect(@PathVariable String shortCode, HttpServletResponse res) {
        return new RedirectView(urlService.getOriginalUrl(shortCode));
    }
}
