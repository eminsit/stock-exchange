package com.eminsit.stock_exchange.repository;

import com.eminsit.stock_exchange.model.StockExchange;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

@Repository
public interface StockExchangeRepository extends JpaRepository<StockExchange, Long> {
    StockExchange findByName(String name);
}
