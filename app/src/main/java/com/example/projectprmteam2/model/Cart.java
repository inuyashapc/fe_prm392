package com.example.projectprmteam2.model;

import java.util.List;

public class Cart {
    private double totalProduct;
    private double totalQuantity;
    private double price;
    private User user;
    private List<Product> products;

    public Cart(double totalProduct, double totalQuantity, double price, User user, List<Product> products) {
        this.totalProduct = totalProduct;
        this.totalQuantity = totalQuantity;
        this.price = price;
        this.user = user;
        this.products = products;
    }

    public double getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(double totalProduct) {
        this.totalProduct = totalProduct;
    }

    public double getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(double totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
