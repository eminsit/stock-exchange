package com.eminsit.stock_exchange.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="stock", indexes = {@Index(name = "idx_stock_name",  columnList="name", unique = true)})
@Entity
public class Stock {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Double currentPrice;
    private LocalDateTime lastUpdateDate;

    @ManyToMany(
            mappedBy = "stocks",
            fetch = FetchType.LAZY
    )
    private Set<StockExchange> stockExchanges;

    public Stock(String name, String description, Double currentPrice) {
        this.name = name;
        this.description = description;
        this.currentPrice = currentPrice;
    }
}
