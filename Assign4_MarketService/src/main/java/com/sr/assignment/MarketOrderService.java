package com.sr.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MarketOrderService {

    private static final String ORDER_SERVICE_URL = "http://localhost:8000/orders/{orderId}";  // Example URL for Order Service

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MarketOrderRepository marketOrderRepository;

    public MarketOrder processOrder(String orderId) {
        // Fetch the order data from the Order service using the dynamic URL
        OrderDTO orderDTO = restTemplate.getForObject(ORDER_SERVICE_URL, OrderDTO.class, orderId);
        
        // Check if the orderDTO is not null, just in case the order doesn't exist
        if (orderDTO == null) {
            throw new IllegalArgumentException("Order not found with ID: " + orderId);
        }

        // Convert OrderDTO to MarketOrder entity
        MarketOrder marketOrder = new MarketOrder();
        marketOrder.setOrderId(orderDTO.getId());
        marketOrder.setUserId(orderDTO.getUserId());
        marketOrder.setStockSymbol(orderDTO.getStockSymbol());
        marketOrder.setQuantity(orderDTO.getQuantity());
        marketOrder.setPricePerUnit(orderDTO.getPricePerUnit());
        marketOrder.setOrderType(orderDTO.getOrderType());
        marketOrder.setStatus("PROCESSED"); // You can replace this with more complex logic if needed

        // Save the order in MarketService DB
        return marketOrderRepository.save(marketOrder);
    }
}
