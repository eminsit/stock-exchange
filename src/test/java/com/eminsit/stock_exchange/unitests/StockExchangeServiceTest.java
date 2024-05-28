package com.eminsit.stock_exchange.unitests;

import com.eminsit.stock_exchange.model.Stock;
import com.eminsit.stock_exchange.model.StockExchange;
import com.eminsit.stock_exchange.repository.StockExchangeRepository;
import com.eminsit.stock_exchange.service.StockExchangeServiceImpl;
import com.eminsit.stock_exchange.service.StockService;
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
public class StockExchangeServiceTest {

    @InjectMocks
    StockExchangeServiceImpl exchangeService;

    @Mock
    StockExchangeRepository exchangeRepository;
    @Mock
    StockService stockService;


    public void setup() {
        StockExchange exchange1 = new StockExchange();
        exchange1.setName("exchange_1");
        exchange1.setDescription("exchange_desc_1");

    }

    @DisplayName("Should return correct exchange")
    @Test
    public void when_createStockExchangeSuccessful() {
        StockExchange exchange1 = new StockExchange();
        exchange1.setName("exchange_1");
        exchange1.setDescription("exchange_desc_1");

        StockExchange exchange2 = new StockExchange();
        exchange2.setName("exchange_1");
        exchange2.setDescription("exchange_desc_1");
        Mockito.when(exchangeRepository.save(exchange1)).thenReturn(exchange2);
        var response = exchangeService.saveStockExchange(exchange1);

        assertEquals("exchange_1", response.getName());
    }

    @DisplayName("Should Load correct stock exchange on corect name")
    @Test
    public void when_fetchStockExchangeSuccessful() {
        StockExchange exchange1 = new StockExchange();
        exchange1.setName("exchange_1");
        exchange1.setDescription("exchange_desc_1");

        Mockito.when(exchangeRepository.findByName("exchange_1")).thenReturn(exchange1);
        var response = exchangeService.getOneByName("exchange_1");
        assertEquals("exchange_1", response.getName());
    }

    @DisplayName("Should add stock to exchange successful")
    @Test
    public void when_addingStockToStockExchangeSuccessful() {
        LocalDateTime now = LocalDateTime.now();
        Stock stock1 = new Stock();
        stock1.setName("stock_1");
        stock1.setDescription("description_1");
        stock1.setLastUpdateDate(now);

        StockExchange exchange1 = new StockExchange();
        exchange1.setName("exchange_1");
        exchange1.setDescription("exchange_desc_1");

        StockExchange exchange2 = new StockExchange();
        exchange2.setName("exchange_1");
        exchange2.setDescription("exchange_desc_1");
        Set<Stock> stocks = new HashSet<>();
        stocks.add(stock1);
        exchange2.setStocks(stocks);

        Mockito.when(stockService.getOneByName("stock_1")).thenReturn(stock1);
        Mockito.when(exchangeRepository.findByName("exchange_1")).thenReturn(exchange2);
        Mockito.when(exchangeRepository.save(exchange2)).thenReturn(exchange2);

        var response = exchangeService.addStock("exchange_1", "stock_1");
        assertEquals("exchange_1", response.getName());
    }

    @DisplayName("It should throw Illegal Argument Exceptıon")
    @Test
    void whenNullListCameResponseShouldThrowException() {

        LocalDateTime now = LocalDateTime.now();
        Stock stock1 = new Stock();
        stock1.setName("stock_1");
        stock1.setDescription("description_1");
        stock1.setLastUpdateDate(now);

        StockExchange exchange1 = new StockExchange();
        exchange1.setName("exchange_1");
        exchange1.setDescription("exchange_desc_1");


        Mockito.when(stockService.getOneByName("stock_1")).thenReturn(stock1);
        Mockito.when(exchangeRepository.findByName("exchange_1")).thenReturn(exchange1);
        Mockito.when(exchangeRepository.save(exchange1)).thenReturn(exchange1);

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> exchangeService.addStock("exchange_2", "stock_1"),
                "Could not find either stock or stockExchange"
        );

        assertTrue(thrown.getMessage().equals("Could not find either stock or stockExchange"));
    }
}

