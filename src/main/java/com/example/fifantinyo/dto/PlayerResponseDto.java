package com.example.fifantinyo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PlayerResponseDto {
    private Long id;
    private String name;
}
