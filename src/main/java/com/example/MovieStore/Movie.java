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



    public Movie(String id, String title, String year, String minutes, String genre, Double price) {
        this.tId = id;
        this.title = title;
        this.date = year;
        this.movieLength = minutes;
        this.genres = genre;
        this.price = price;
    }



    public String getId() {
        return tId;
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

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(String movieLength) {
        this.movieLength = movieLength;
    }
}
