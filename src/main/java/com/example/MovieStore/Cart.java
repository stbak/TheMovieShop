package com.example.MovieStore;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Cart {

    private List<Movie> cart = new ArrayList<>();
    private String title;
    private Double price;

    public Cart() {

    }



    public List<Movie> getCart() {
        return cart;
    }

    public List<Movie> addItemToBuy(Movie movie) {
        cart.add(movie);
        return cart;
    }
}
