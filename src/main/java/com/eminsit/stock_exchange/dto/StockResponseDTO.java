package com.eminsit.stock_exchange.dto;

import com.eminsit.stock_exchange.model.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StockResponseDTO {
    private String name;
    private String description;
    private Double currentPrice;
    private LocalDateTime updateDate;

    public StockResponseDTO(Stock stock) {
        this.name = stock.getName();
        this.description = stock.getDescription();
        this.currentPrice = stock.getCurrentPrice();
        this.updateDate = stock.getLastUpdateDate();
    }
}
