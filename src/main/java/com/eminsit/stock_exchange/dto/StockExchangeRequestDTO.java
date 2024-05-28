package com.eminsit.stock_exchange.dto;

import com.eminsit.stock_exchange.model.StockExchange;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StockExchangeRequestDTO {
    private String name;
    private String description;

    public StockExchange toEntity() {
        StockExchange exchange = new StockExchange();
        exchange.setName(this.name);
        exchange.setDescription(this.description);

        return exchange;
    }
}
