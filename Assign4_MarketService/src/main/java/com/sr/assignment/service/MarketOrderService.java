package com.sr.assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sr.assignment.MarketOrderRepository;
import com.sr.assignment.DTO.OrderDTO;
import com.sr.assignment.model.MarketOrder;

@Service
public class MarketOrderService {

    @Autowired
    private MarketOrderRepository marketOrderRepository;

    public MarketOrder saveMarketOrder(OrderDTO orderDTO) {
        System.out.println("Received new market order request: " + orderDTO);

        // Determine status (assuming completed if price is set, otherwise received)
        String status = (orderDTO.getPricePerUnit() > 0) ? "Completed" : "Received";
        
        MarketOrder marketOrder = new MarketOrder(
            orderDTO.getUserId(),
            orderDTO.getStockSymbol(),
            orderDTO.getQuantity(),
            orderDTO.getPricePerUnit(),
            orderDTO.getOrderType(),
            status // Set status dynamically
        );

        MarketOrder savedOrder = marketOrderRepository.save(marketOrder);
        System.out.println("Market order saved successfully: " + savedOrder);

        return savedOrder;
    }
}
