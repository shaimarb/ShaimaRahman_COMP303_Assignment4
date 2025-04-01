package com.sr.assignment.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.sr.assignment.models.Order;
import com.sr.assignment.services.OrderService;

import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private WebClient.Builder webClientBuilder;

    //private RestTemplate restTemplate; // To communicate with Market & Transaction services

    private static final String MARKET_SERVICE_URL = "http://localhost:8001/market/orders";
    private static final String TRANSACTION_SERVICE_URL = "http://localhost:8002/transactions";

    // Show the place order form
    @GetMapping("/place-order")
    public String showPlaceOrderForm(Model model) {
        model.addAttribute("order", new Order()); // Create a new empty Order object to bind to the form
        return "place-order"; // Return the Thymeleaf template for order placement
    }
    
//    // Place an order (also sends to Market & Transaction services)
//    @PostMapping("/place")
//    public String placeOrder(@ModelAttribute Order order, Model model) {
//        try {
//            // Save the order in the OrderService database
//            Order savedOrder = orderService.placeOrder(order);
//
//            System.out.println("Sending order to Market Service: " + order);
//
//            // Forward order to Market Service
//            restTemplate.postForObject(MARKET_SERVICE_URL, order, String.class);
//
//            System.out.println("Sending order to Transaction Service: " + order);
//
//            // Forward order to Transaction Service
//            restTemplate.postForObject(TRANSACTION_SERVICE_URL, order, String.class);
//
//            // Add the saved order to the model and return a success page
//            model.addAttribute("order", savedOrder);
//            model.addAttribute("message", "Order placed successfully!");
//
//            return "order-success"; // You can create a "order-success.html" page to display success message
//        } catch (RestClientException e) {
//            // If there's an error communicating with Market or Transaction service
//            model.addAttribute("message", "Error placing order. Please try again later.");
//            return "order-error"; // You can create an "order-error.html" page to display error message
//        }
//    }

    @PostMapping("/submitOrder")
    public Mono<String> submitOrder(@ModelAttribute Order order, Model model) {
        System.out.println("Received order: " + order);

        return Mono.fromCallable(() -> orderService.placeOrder(order))
            .flatMap(savedOrder -> {
                model.addAttribute("order", savedOrder);
                model.addAttribute("message", "Order placed successfully!");

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

                return Mono.zip(marketResponse, transactionResponse)
                    .thenReturn("order-success");
            })
            .onErrorResume(error -> {
                model.addAttribute("message", "Error placing order.");
                return Mono.just("order-error");
            });
    }

    
    // Get all orders for a specific user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable String userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        if (orders.isEmpty()) {
            return ResponseEntity.status(404).body(null);  // Not Found if no orders found
        }
        return ResponseEntity.ok(orders);
    }

    // Get an order by ID
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId) {
        Optional<Order> order = orderService.getOrderById(orderId);
        if (order.isEmpty()) {
            return ResponseEntity.status(404).body(null);  // Not Found if order not found
        }
        return ResponseEntity.ok(order.get());
    }
}
