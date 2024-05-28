package com.eminsit.stock_exchange.controller;

import com.eminsit.stock_exchange.dto.StockExchangeRequestDTO;
import com.eminsit.stock_exchange.dto.StockExchangeResponseDTO;
import com.eminsit.stock_exchange.model.StockExchange;
import com.eminsit.stock_exchange.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/stock-exchange")
public class StockExchangeController {

    @Autowired
    StockExchangeService service;

    @GetMapping("/{name}")
    public  ResponseEntity<StockExchangeResponseDTO>  getStockExchange(@PathVariable String name) {
        StockExchange exchange = service.getOneByName(name);
        return new ResponseEntity<StockExchangeResponseDTO>(new StockExchangeResponseDTO(exchange), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StockExchangeResponseDTO> createStockExchange(@RequestBody StockExchangeRequestDTO requestDTO) {
        StockExchange exchange = service.saveStockExchange(requestDTO.toEntity());

        return  new ResponseEntity<StockExchangeResponseDTO>(new StockExchangeResponseDTO(exchange), HttpStatus.OK);
    }

    @PatchMapping("/{name}")
    public ResponseEntity<StockExchangeResponseDTO> addStockToStockExchange(@PathVariable String name, @RequestParam String stockName) {
        StockExchange exchange = service.addStock(name, stockName);
        return new ResponseEntity<StockExchangeResponseDTO>(new StockExchangeResponseDTO(exchange), HttpStatus.OK);
    }
}
