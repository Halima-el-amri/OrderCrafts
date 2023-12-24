// Product.java
package com.ordercraft.ordercraft.model.classes;

public class Product {
    private int itemId;
    private String name;
    private double price;
    private int stockQuantity;

    public Product(int itemId, String name, double price, int stockQuantity) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Product(String name, double price, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public Product() {
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "itemId=" + itemId +
                ", Name='" + name + '\'' +
                ", price=" + price +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}
