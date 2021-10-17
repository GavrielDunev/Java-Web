package com.example.coffeeshop.model.view;

public class UserViewModel {

    private String username;
    private Integer numberOfOrders;

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public Integer getNumberOfOrders() {
        return numberOfOrders;
    }

    public UserViewModel setNumberOfOrders(Integer numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
        return this;
    }
}
