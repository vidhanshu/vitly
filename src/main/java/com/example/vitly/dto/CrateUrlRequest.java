package com.example.vitly.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CrateUrlRequest {
    @NotBlank
    @Max(2048)
    private String longUrl;
}
