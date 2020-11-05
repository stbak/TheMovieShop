package com.example.MovieStore;

public class Movie {
    private Integer id;
    private String tId; //tconst
    private String title; //primaryTitle
    private String author;
    private Double price;
    private String date; //startYear
    private String type; //movie, short, tv titleType
    private String genres;
    private boolean adult; //isAdult
    private String movieLength; //

    /*public Movie(Integer id, String title, String author, Double price, String date) {


        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.date = date;
    }

     */



    public Movie(String s, String s1, String s2, String s3, String s4) {
        this.tId = s;
        this.title = s1;
        this.date = s2;
        this.movieLength = s3;
        this.genres = s4;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
