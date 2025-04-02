package com.sr.assignment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import com.sr.assignment.models.Order;
import com.sr.assignment.services.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Show the place order form
    @GetMapping("/place-order")
    public String showPlaceOrderForm(@RequestParam String userId, Model model) {
        System.out.println("Received userId: " + userId);  // Log the userId for debugging
        model.addAttribute("order", new Order());  // Create a new order object
        return "place-order";  // Return the Thymeleaf template for order placement
    }


    // Submit an order
    @PostMapping("/submitOrder")
    public String submitOrder(@ModelAttribute Order order, @RequestParam String userId, Model model) {
        System.out.println("Received userId in submitOrder: " + userId); // Check userId in submitOrder

        String result = orderService.submitOrder(order, userId, model);
        if (result.equals("order-success")) {
            return "order-success";
        } else {
            return "order-error";
        }
    }

}
