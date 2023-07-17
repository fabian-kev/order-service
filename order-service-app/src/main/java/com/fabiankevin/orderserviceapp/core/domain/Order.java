package com.fabiankevin.orderserviceapp.core.domain;

import com.fabiankevin.domainstarter.domain.Amount;
import com.fabiankevin.orderserviceapp.core.domain.value.Currency;
import com.fabiankevin.orderserviceapp.core.domain.value.Note;
import com.fabiankevin.orderserviceapp.core.exceptions.ItemAlreadyExistsException;
import com.fabiankevin.orderserviceapp.core.guards.Guards;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

public final class Order {
    private UUID id;
    private UUID customerId;
    private List<Item> items;
    private Note note;
    private Currency currency;
    private OrderStatus status;
    private Instant createdDate;
    private Instant updatedDate;

    public Order(Builder builder) {
        this.id = builder.id;
        this.customerId = builder.customerId;
        this.items = new ArrayList<>();
        this.note = builder.note;
        this.status = builder.status;
        this.currency = builder.currency;
        this.createdDate = Optional.ofNullable(builder.createdDate).orElse(Instant.now());
        this.updatedDate = Optional.ofNullable(builder.updatedDate).orElse(Instant.now());

        Guards.guard(currency).againstNull("Currency is required");
        Guards.guard(customerId).againstNull("Customer id is required");
        Guards.guard(status).againstNull("Order status is required");
    }

    public static Builder builder(){
        return new Builder();
    }

    public Amount getTotalAmount(){

        double totalAmount = this.items.stream()
                .map(item -> item.getUnitPrice().getValue().multiply(BigDecimal.valueOf(item.getQuantity().getValue())))
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
        return Amount.of(currency.value(), totalAmount);
    }

    public void addItem(Item item){
        Guards.guard(item).againstNull("Item should not be null");

        this.items.stream()
                .filter(item::equals)
                .findAny()
                .ifPresent(i -> {
                    throw new ItemAlreadyExistsException(item);
                });

        this.items.add(item);
    }

    public void remoteItem(Item item){
        this.items.remove(item);
    }
    public void serving(){
        this.status = OrderStatus.SERVING;
    }
    public void forDelivery(){
        this.status = OrderStatus.DELIVERY;
    }
    public void completed(){
        this.status = OrderStatus.COMPLETED;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    public String getCurrency() {
        return currency.value();
    }

    public String getNote() {
        return note.value();
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Instant getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(customerId, order.customerId) && Objects.equals(items, order.items) && Objects.equals(note, order.note) && Objects.equals(currency, order.currency) && status == order.status && Objects.equals(createdDate, order.createdDate) && Objects.equals(updatedDate, order.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, items, note, currency, status, createdDate, updatedDate);
    }

    public static class Builder {
        private UUID id;
        private UUID customerId;
        private Currency currency;
        private Note note;
        private OrderStatus status;
        private Instant createdDate;
        private Instant updatedDate;

        public Builder id(UUID id){
            this.id = id;
            return this;
        }

        public Builder customerId(UUID customerId){
            this.customerId = customerId;
            return this;
        }


        public Builder currency(Currency currency){
            this.currency = currency;
            return this;
        }

        public Builder note(Note note){
            this.note = note;
            return this;
        }

        public Builder status(OrderStatus status){
            this.status = status;
            return this;
        }

        public Builder asPendingOrder(){
            this.status = OrderStatus.PENDING;
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

        public Order build(){
            return new Order(this);
        }
    }
}