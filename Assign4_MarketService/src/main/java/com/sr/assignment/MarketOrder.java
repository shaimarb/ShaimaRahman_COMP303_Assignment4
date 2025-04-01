package com.sr.assignment;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Document(collection = "market_orders")
public class MarketOrder {
    
    @Id
    private String id;
    
    private String orderId; // Reference to the order from OrderService
    private String userId;
    private String stockSymbol;
    private int quantity;
    private double pricePerUnit;
    private String orderType; // BUY or SELL
    private String status; // PROCESSED, EXECUTED, REJECTED
    private LocalDateTime processedTime; // Time order was processed in the market
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	public LocalDateTime getProcessedTime() {
		return processedTime;
	}
	public void setProcessedTime(LocalDateTime processedTime) {
		this.processedTime = processedTime;
	}
	public MarketOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MarketOrder(String id, String orderId, String userId, String stockSymbol, int quantity, double pricePerUnit,
			String orderType, String status, LocalDateTime processedTime) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.userId = userId;
		this.stockSymbol = stockSymbol;
		this.quantity = quantity;
		this.pricePerUnit = pricePerUnit;
		this.orderType = orderType;
		this.status = status;
		this.processedTime = processedTime;
	}
	@Override
	public String toString() {
		return "MarketOrder [id=" + id + ", orderId=" + orderId + ", userId=" + userId + ", stockSymbol=" + stockSymbol
				+ ", quantity=" + quantity + ", pricePerUnit=" + pricePerUnit + ", orderType=" + orderType + ", status="
				+ status + ", processedTime=" + processedTime + "]";
	}


}
