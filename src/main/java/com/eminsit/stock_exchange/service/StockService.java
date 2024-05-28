package com.eminsit.stock_exchange.service;

import com.eminsit.stock_exchange.dto.StockRequestDTO;
import com.eminsit.stock_exchange.model.Stock;

public interface StockService {

    Stock getOneByName(String name);

    void deleteOneByName(String name);

    Stock saveStock(Stock stock);

    void update(StockRequestDTO dto);
}
