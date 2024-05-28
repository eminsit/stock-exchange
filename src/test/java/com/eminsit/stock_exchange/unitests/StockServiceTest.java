package com.eminsit.stock_exchange.unitests;

import com.eminsit.stock_exchange.model.Stock;
import com.eminsit.stock_exchange.model.StockExchange;
import com.eminsit.stock_exchange.repository.StockExchangeRepository;
import com.eminsit.stock_exchange.repository.StockRepository;
import com.eminsit.stock_exchange.service.StockExchangeServiceImpl;
import com.eminsit.stock_exchange.service.StockService;
import com.eminsit.stock_exchange.service.StockServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class StockServiceTest {

    @InjectMocks
    StockServiceImpl stockService;

    @Mock
    StockRepository stockRepository;


    @DisplayName("Should return correct exchange")
    @Test
    public void when_createStockSuccessful() {
        Stock stock = new Stock();
        stock.setName("stock_1");
        stock.setDescription("desc_1");

        Stock stock2 = new Stock();
        stock2.setName("stock_2");
        stock2.setDescription("desc_2");

        Mockito.when(stockRepository.save(stock)).thenReturn(stock2);
        var response = stockService.saveStock(stock);

        assertEquals("stock_1", response.getName());
    }

    @DisplayName("Should Load correct stock exchange on corect name")
    @Test
    public void when_fetchStockSuccessful() {
        Stock stock = new Stock();
        stock.setName("stock_1");
        stock.setDescription("desc_1");

        Mockito.when(stockRepository.findByName("stock_1")).thenReturn(stock);
        var response = stockService.getOneByName("stock_1");
        assertEquals("stock_1", response.getName());
    }
}

