package com.fabiankevin.orderserviceapp.application.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmountDto {
    private String currency;
    private BigDecimal value;
}
