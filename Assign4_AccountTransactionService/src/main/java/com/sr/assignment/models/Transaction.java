package com.sr.assignment.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transactions") // MongoDB collection name
public class Transaction {

    @Id
    private String id;  // MongoDB auto-generated ID

    private String userId;  
    private String stockSymbol;  
    private int quantity;  
    private double pricePerUnit;  
    private String orderType;  
    private String status;  
    private double amount;  // Calculated total amount (quantity * pricePerUnit)

    // Default constructor
    public Transaction() {}

    // Constructor to map from Order to Transaction
    public Transaction(String userId, String stockSymbol, int quantity, double pricePerUnit, String orderType, String status) {
        this.userId = userId;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.orderType = orderType;
        this.status = status;
        this.amount = quantity * pricePerUnit;  // Automatically calculate total amount
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.amount = quantity * this.pricePerUnit; // Recalculate amount
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
        this.amount = this.quantity * pricePerUnit; // Recalculate amount
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    // No setter for amount, as it's calculated automatically

}
