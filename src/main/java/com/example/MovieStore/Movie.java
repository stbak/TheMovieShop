package com.example.MovieStore;

public class Movie {
    private Integer id; //MovieId
    private Integer tId; //ImgId
    private String title; //Title
    private String author; //Director
    private Integer price; //Price
    private Integer date; //MovieYear
    private String type; //movie, short, tv titleType
    private String genres; //Genre
    private boolean adult; //isAdult
    private String rate; //Rate
    private Integer movieLength; //PlayTime
    private String descr; //Description
    private String actors; //Stars

    public Movie(Integer id, Integer imgId,  String title, Integer year, String genre, String actors, String author, Integer minutes, String rate, Integer price, String descr) {
        this.id = id;
        this.tId = imgId;
        this.title = title;
        this.date = year;
        this.genres = genre;
        this.actors = actors;
        this.author = author;
        this.movieLength = minutes;
        this.price = price;
        this.descr = descr;
        this.rate = rate;
    }

    public Integer getId() {
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
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

    public Integer getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(Integer movieLength) {
        this.movieLength = movieLength;
    }
}
