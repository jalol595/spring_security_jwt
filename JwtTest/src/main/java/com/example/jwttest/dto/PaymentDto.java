package com.example.jwttest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDto {
    private Integer fromCard;
    private Double amount;
    private Integer toCard;

}
