package com.eminsit.stock_exchange.controller;

import com.eminsit.stock_exchange.dto.StockRequestDTO;
import com.eminsit.stock_exchange.dto.StockResponseDTO;
import com.eminsit.stock_exchange.model.Stock;
import com.eminsit.stock_exchange.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/stock")
public class StockController {

    @Autowired
    StockService service;

    @PostMapping
    public ResponseEntity<StockResponseDTO> createStock(@RequestBody StockRequestDTO requestDTO) {
        Stock stock = service.saveStock(requestDTO.toEntity());
        return  new ResponseEntity<StockResponseDTO>(new StockResponseDTO(stock), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Boolean> updateStock(@RequestBody StockRequestDTO requestDTO) {
        requestDTO.setUpdateDate(LocalDateTime.now());
        service.update(requestDTO);
        return  new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public  ResponseEntity<StockResponseDTO> getStock(@PathVariable String name) {
        Stock stock = service.getOneByName(name);
        return new ResponseEntity<StockResponseDTO>(new StockResponseDTO(stock), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Boolean> deleteStock(@PathVariable String name) {
         service.deleteOneByName(name);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
}
