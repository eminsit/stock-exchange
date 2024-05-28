package com.eminsit.stock_exchange.repository;

import com.eminsit.stock_exchange.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Stock findByName(String name);

    @Modifying
    @Query("update Stock s set s.currentPrice = :currentPrice, s.lastUpdateDate = :lastUpdateDate where s.name = :name")
    void updatePrice(@Param(value = "name") String name, @Param(value = "currentPrice") Double currentPrice, LocalDateTime lastUpdateDate);
}
