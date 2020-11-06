package com.example.MovieStore;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Cart {

    private Map<Double, String> cart = new HashMap<Double, String>();
    private String title;
    private Double price;

    public Cart() {

    }

    public Cart(Double price, String title) {
        cart.put(price, title);
    }

    public Map<Double, String> getCart() {
        return cart;
    }

    public void addItemToBuy(Double price, String title) {
        cart.put(price, title);
    }
}
