package com.fabiankevin.orderserviceapp.infrastructure.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "items")
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;
    private Integer quality;
    private String code;
    private String name;
    private String currency;
    private BigDecimal amount;
    private String description;
    private String productId;
    private Instant createdDate;
    private Instant updatedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(quality, that.quality) && Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(currency, that.currency) && Objects.equals(amount, that.amount) && Objects.equals(description, that.description) && Objects.equals(productId, that.productId) && Objects.equals(createdDate, that.createdDate) && Objects.equals(updatedDate, that.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quality, code, name, currency, amount, description, productId, createdDate, updatedDate);
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "id=" + id +
                ", quality=" + quality +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", currency='" + currency + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", productId=" + productId +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
