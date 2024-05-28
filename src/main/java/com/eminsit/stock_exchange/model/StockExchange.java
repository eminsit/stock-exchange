package com.eminsit.stock_exchange.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="stock_exchange", indexes = {@Index(name = "idx_name",  columnList="name", unique = true)})
@Entity
public class StockExchange {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Boolean liveInMarket = false;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "exchanges",
            joinColumns = {
                    @JoinColumn(name = "stock_exchange_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "stock_id", referencedColumnName = "id")
            }
    )
    private Set<Stock> stocks = new HashSet<>();
}
