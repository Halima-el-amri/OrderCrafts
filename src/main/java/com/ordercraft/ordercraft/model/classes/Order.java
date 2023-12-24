package com.ordercraft.ordercraft.model.classes;

import java.sql.Date;
import java.time.LocalDate;

public class Order {
    private int orderId;
    private int clientId;
    private LocalDate OrderDate;
    private boolean isCompleted;

    public Order(int orderId, int clientId, boolean isCompleted) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.OrderDate = LocalDate.now();
        this.isCompleted = isCompleted;
    }

    public Order() {

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public LocalDate getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        OrderDate = orderDate;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", clientId=" + clientId +
                ", OrderDate=" + OrderDate +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
