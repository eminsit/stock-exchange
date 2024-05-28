package com.eminsit.stock_exchange.dto;

import com.eminsit.stock_exchange.model.Stock;
import com.eminsit.stock_exchange.model.StockExchange;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StockExchangeResponseDTO {
    private String name;
    private String description;
    private Boolean liveInMarket;
    private Set<StockResponseDTO> stocks = new HashSet<>();

    public StockExchangeResponseDTO(StockExchange exchange) {
        this.name = exchange.getName();
        this.description = exchange.getDescription();
        this.liveInMarket = exchange.getLiveInMarket();
        this.stocks = exchange.getStocks().stream()
                .map(StockResponseDTO::new)
                .collect(Collectors.toSet());
    }
}
