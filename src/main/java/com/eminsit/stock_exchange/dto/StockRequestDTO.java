package com.eminsit.stock_exchange.dto;

import com.eminsit.stock_exchange.model.Stock;
import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StockRequestDTO {
    private String name;
    private String description;
    private Double currentPrice;
    private LocalDateTime updateDate;

    public Stock toEntity() {
        Stock stock = new Stock();
        stock.setName(this.name);
        stock.setDescription(this.description);
        stock.setCurrentPrice(this.currentPrice);
        stock.setLastUpdateDate(this.updateDate);

        return stock;
    }
}
