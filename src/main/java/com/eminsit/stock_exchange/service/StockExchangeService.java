package com.eminsit.stock_exchange.service;

import com.eminsit.stock_exchange.model.Stock;
import com.eminsit.stock_exchange.model.StockExchange;

public interface StockExchangeService {
    StockExchange getOneByName(String name);

    StockExchange saveStockExchange(StockExchange stock);

    StockExchange addStock(String exchangeName, String stockName);
}
