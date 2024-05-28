package com.eminsit.stock_exchange.service;

import com.eminsit.stock_exchange.constant.Constants;
import com.eminsit.stock_exchange.model.Stock;
import com.eminsit.stock_exchange.model.StockExchange;
import com.eminsit.stock_exchange.repository.StockExchangeRepository;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StockExchangeServiceImpl implements StockExchangeService{

    @Autowired
    StockExchangeRepository repository;

    @Autowired
    StockService stockService;


    @Lock(LockModeType.OPTIMISTIC)
    @Transactional
    @Override
    public StockExchange getOneByName(String name) {
        return repository.findByName(name);
    }

    @Lock(LockModeType.OPTIMISTIC)
    @Transactional
    @Override
    public StockExchange saveStockExchange(StockExchange exchange) {
        exchange.setLiveInMarket(exchange.getStocks().size() > Constants.LIVE_IN_MARKET_MINIMUM_STOCK_COUNT);
        return repository.save(exchange);
    }


    @Lock(LockModeType.OPTIMISTIC)
    @Transactional
    @Override
    public StockExchange addStock(String exchangeName, String stockName) {
        Stock stock = stockService.getOneByName(stockName);
        StockExchange exchange = repository.findByName(exchangeName);

        if (stock == null || exchange == null) {
            throw new IllegalArgumentException("Could not find either stock or stockExchange");
        }

        exchange.getStocks().add(stock);
        exchange.setLiveInMarket(exchange.getStocks().size() >= Constants.LIVE_IN_MARKET_MINIMUM_STOCK_COUNT);

        return repository.save(exchange);
    }
}

