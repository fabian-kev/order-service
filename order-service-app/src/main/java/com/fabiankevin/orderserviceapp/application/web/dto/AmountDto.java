package com.fabiankevin.orderserviceapp.application.web.dto;

import com.fabiankevin.domainstarter.domain.Amount;
import lombok.*;

import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AmountDto {
    private String currency;
    private BigDecimal value;

    public static AmountDto of(Amount amount){
        return new AmountDto(amount.getCurrency(), amount.getValue());
    }
}
