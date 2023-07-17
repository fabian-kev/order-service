package com.fabiankevin.orderserviceapp.application.web.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderRequest {
    @NotNull
    private UUID customerId;
    @NotNull
    @Valid
    private List<ItemRequest> items;
    @NotBlank
    private String note;
    @NotBlank
    private String currency;
}
