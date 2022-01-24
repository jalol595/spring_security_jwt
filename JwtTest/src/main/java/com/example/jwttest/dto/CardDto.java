package com.example.jwttest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardDto {
    private String username;
    private Double cardBalance;
}
