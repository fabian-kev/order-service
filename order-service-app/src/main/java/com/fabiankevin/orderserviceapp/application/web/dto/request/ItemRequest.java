package com.fabiankevin.orderserviceapp.application.web.dto.request;

import com.fabiankevin.orderserviceapp.application.web.dto.AmountDto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {
    @Min(1)
    private int quantity;
    @NotEmpty
    private String code;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private AmountDto unitPrice;
    @NotNull
    private UUID productId;
}