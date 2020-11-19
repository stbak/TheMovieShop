package com.example.MovieStore;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@Service
public class MovieRepository {
    private List<Movie> movies;
    private List<Movie> seivom;
    private List<String> raw;
    private BufferedReader bufferedReader = new BufferedReader(new FileReader("movieDB.txt")); //change to data1.txt if you want to get data out of data1 into movieDB
    private String[] movieDetail;
    private int moviesNeverThan = 1960;


    public MovieRepository() throws IOException {
        seivom = new ArrayList<>();
        movies = new ArrayList<>();
        raw = new ArrayList<>();


        String line = bufferedReader.readLine();
        while (line != null) {
            raw.add(line);
            line = bufferedReader.readLine();
        }

        for (String s: raw) {

            //now split by tab
            Double moviePrice;
            int year;

            movieDetail = s.split("\t");
            try {
                year = Integer.parseInt(movieDetail[5]);
            } catch (Exception e) {
                year = 1800;
            }

            if (year < 1950) {
                moviePrice = 25.00;
            } else if (year > 1950 && year < 1975) {
                moviePrice = 40.00;
            } else if (year > 1975 && year < 2000) {
                moviePrice = 55.00;
            } else if (year > 2000 && year < 2015) {
                moviePrice = 150.00;
            } else if (year > 2015 && year < 2020) {
                moviePrice = 180.00;
            } else if (year >= 2020) {
                moviePrice = 340.00;
            } else {
                moviePrice = 1.00;
            }
            if (isNumeric(movieDetail[5]) && !movieDetail[8].equals("\\N") && year >= moviesNeverThan)
                seivom.add(new Movie(movieDetail[0], movieDetail[2], movieDetail[5], movieDetail[7], movieDetail[8], moviePrice));

        }

        for (int i = seivom.size()-1; i >= 0; i--) {
            movies.add(seivom.get(i));
        }
    }

    public List<Movie> getPage(int page, int pageSize) {
        int from = Math.max(0,page*pageSize);
        int to = Math.min(movies.size(),(page+1)*pageSize);
        return movies.subList(from, to);
    }

    public int numberOfPages(int pageSize) {
        return (int)Math.ceil(new Double(movies.size()) / pageSize);
    }

    public Movie getMovie(String id) {
        for (Movie movie : movies) {
            if (movie.getId().equals(id)) {
                return movie;
            }
        }
        return null;
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        try {
            int n = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public void fillDatabase(String string) {
        //System.out.println("Filling database " + string);
    }




}
