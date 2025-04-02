package com.sr.assignment.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import com.sr.assignment.models.Order;
import com.sr.assignment.repos.OrderRepository;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.MediaType;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    private static final String MARKET_SERVICE_URL = "http://localhost:8001/market/orders";
    private static final String TRANSACTION_SERVICE_URL = "http://localhost:8002/transactions";

    // Place a new order and make external service calls
    public String submitOrder(Order order, @RequestParam String userId,Model model) {
        try {
            // Save the order to the repository
            Order savedOrder = orderRepository.save(order);
            model.addAttribute("order", savedOrder);
            model.addAttribute("message", "Order placed successfully!");

            // Send the order to the market and transaction services
            Mono<String> marketResponse = webClientBuilder.build()
                .post()
                .uri(MARKET_SERVICE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(savedOrder)
                .retrieve()
                .bodyToMono(String.class);

            Mono<String> transactionResponse = webClientBuilder.build()
                .post()
                .uri(TRANSACTION_SERVICE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(savedOrder)
                .retrieve()
                .bodyToMono(String.class);

            // Wait for both responses and return success
            Mono.zip(marketResponse, transactionResponse)
                .subscribe(); // We handle the responses asynchronously

            return "order-success";
        } catch (Exception e) {
            model.addAttribute("message", "Error placing order.");
            return "order-error";
        }
    }

    // Get all orders by a specific user
    public List<Order> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
}
