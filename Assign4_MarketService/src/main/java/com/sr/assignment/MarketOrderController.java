package com.sr.assignment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/market/orders")
public class MarketOrderController {

    @Autowired
    private MarketOrderService marketOrderService;

    /**
     * Endpoint to receive an order and process it.
     * This will trigger the MarketOrderService to fetch the order data
     * from the OrderService and save it in the MarketService DB.
     *
     * @param orderId The ID of the order to be processed
     * @return ResponseEntity containing the processed MarketOrder
     */
//    @PostMapping
//    public ResponseEntity<MarketOrder> receiveOrder(@RequestParam String orderId) {
//        try {
//            // Process the order by calling MarketOrderService
//            MarketOrder marketOrder = marketOrderService.processOrder(orderId);
//
//            // Return success response with the processed MarketOrder
//            return ResponseEntity.ok(marketOrder);
//        } catch (IllegalArgumentException e) {
//            // If the order is not found or any other error occurs, return a bad request
//            return ResponseEntity.badRequest().body(null);
//        } catch (Exception e) {
//            // Handle other potential exceptions like connection failures
//            return ResponseEntity.status(500).body(null);
//        }
//    }
    
    @PostMapping
    public ResponseEntity<String> processOrder(@RequestBody OrderDTO order) {
        System.out.println("Received order: " + order);
        return ResponseEntity.ok("Order received successfully");
    }
}
