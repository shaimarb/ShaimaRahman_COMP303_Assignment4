package com.sr.assignment.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders") // MongoDB collection name
public class Order {
    
    @Id
    private String id;  // MongoDB generates this automatically
    private String userId; // Reference to the User placing the order
    private String stockSymbol; // e.g., "AAPL", "TSLA"
    private int quantity;
    private double pricePerUnit;
    private String orderType; // "BUY" or "SELL"
    private String status; // "PENDING", "COMPLETED"
    
    
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
	}
	public double getPricePerUnit() {
		return pricePerUnit;
	}
	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
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
	public Order(String id, String userId, String stockSymbol, int quantity, double pricePerUnit, String orderType,
			String status) {
		super();
		this.id = id;
		this.userId = userId;
		this.stockSymbol = stockSymbol;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
		this.orderType = orderType;
		this.status = status;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", stockSymbol=" + stockSymbol + ", quantity=" + quantity
				+ ", pricePerUnit=" + pricePerUnit + ", orderType=" + orderType + ", status=" + status + "]";
	}
    
    
}