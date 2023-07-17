package com.fabiankevin.orderserviceapp.core.domain;

import com.fabiankevin.domainstarter.domain.Amount;
import com.fabiankevin.orderserviceapp.core.domain.value.Quantity;
import com.fabiankevin.orderserviceapp.core.guards.Guards;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class Item {

    private UUID id;
    private Quantity quantity;
    private UUID productId;
    private String code;
    private String name;
    private String description;
    private Instant createdDate;
    private Instant updatedDate;
    private Amount unitPrice;

    public Item(Builder builder) {
        this.id = builder.id;
        this.quantity = builder.quantity;
        this.code = builder.code;
        this.name = builder.name;
        this.description = builder.description;
        this.unitPrice = builder.unitPrice;
        this.productId = builder.productId;
        this.createdDate = Optional.ofNullable(builder.createdDate).orElse(Instant.now());
        this.updatedDate = Optional.ofNullable(builder.updatedDate).orElse(Instant.now());

        Guards.guard(productId).againstNull("Product id is required");
        Guards.guard(code).againstNullOrEmpty("Product code is required");
        Guards.guard(name).againstNullOrEmpty("Name is required");
        Guards.guard(description).againstNullOrEmpty("Description is required");
        Guards.guard(unitPrice).againstNull("Unit price is required");
        Guards.guard(quantity).againstNull("Quantity is required");

    }

    public static Builder builder(){
        return new Builder();
    }

    public UUID getId() {
        return id;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Instant getUpdatedDate() {
        return updatedDate;
    }

    public Amount getUnitPrice() {
        return unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return productId.equals(item.productId) && code.equals(item.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, code);
    }

    public static class Builder {
        private UUID id;
        private Quantity quantity;
        private String code;
        private String name;
        private String description;
        private Instant createdDate;
        private Instant updatedDate;
        private Amount unitPrice;
        private UUID productId;


        public Builder id(UUID id){
            this.id = id;
            return this;
        }

        public Builder productId(UUID productId){
            this.productId = productId;
            return this;
        }

        public Builder quantity(Quantity quantity){
            this.quantity = quantity;
            return this;
        }

        public Builder code(String code){
            this.code = code;
            return this;
        }

        public Builder name(String name){
            this.name = name;
            return this;
        }

        public Builder description(String description){
            this.description = description;
            return this;
        }

        public Builder unitPrice(Amount unitPrice){
            this.unitPrice = unitPrice;
            return this;
        }

        public Builder createdDate(Instant createdDate){
            this.createdDate = createdDate;
            return this;
        }

        public Builder updatedDate(Instant updatedDate){
            this.updatedDate = updatedDate;
            return this;
        }

        public Item build(){
            return new Item(this);
        }
    }
}
