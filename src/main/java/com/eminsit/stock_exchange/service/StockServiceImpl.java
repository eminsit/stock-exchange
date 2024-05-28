package com.eminsit.stock_exchange.service;

import com.eminsit.stock_exchange.constant.Constants;
import com.eminsit.stock_exchange.dto.StockRequestDTO;
import com.eminsit.stock_exchange.model.Stock;
import com.eminsit.stock_exchange.model.StockExchange;
import com.eminsit.stock_exchange.repository.StockRepository;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    StockRepository repository;

    @Override
    public Stock getOneByName(String name) {
        return repository.findByName(name);
    }

    @Lock(LockModeType.OPTIMISTIC)
    @Transactional
    @Override
    public void deleteOneByName(String name) {
        Stock stock = repository.findByName(name);
        for (StockExchange exchange: stock.getStockExchanges()) {
            exchange.getStocks().remove(stock);
            exchange.setLiveInMarket(exchange.getStocks().size() >= Constants.LIVE_IN_MARKET_MINIMUM_STOCK_COUNT);
        }
        repository.delete(stock);
    }

    @Lock(LockModeType.OPTIMISTIC)
    @Transactional
    @Override
    public Stock saveStock(Stock stock) {
        stock.setLastUpdateDate(LocalDateTime.now());
        return repository.save(stock);
    }

    @Lock(LockModeType.OPTIMISTIC)
    @Transactional
    @Override
    public void update(StockRequestDTO dto) {
        repository.updatePrice(dto.getName(), dto.getCurrentPrice(), dto.getUpdateDate());
    }
}

