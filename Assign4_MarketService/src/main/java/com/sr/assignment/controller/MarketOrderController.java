package com.sr.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sr.assignment.DTO.OrderDTO;
import com.sr.assignment.model.MarketOrder;
import com.sr.assignment.service.MarketOrderService;

@RestController
@RequestMapping("/market")
public class MarketOrderController {

    @Autowired
    private MarketOrderService marketOrderService;

    @PostMapping("/orders")
    public ResponseEntity<MarketOrder> createMarketOrder(@RequestBody OrderDTO orderDTO) {
        MarketOrder savedOrder = marketOrderService.saveMarketOrder(orderDTO);
        System.out.println("Market order saved: " + savedOrder);
        return ResponseEntity.ok(savedOrder);
    }
}