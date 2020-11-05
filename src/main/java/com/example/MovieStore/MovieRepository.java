package com.example.MovieStore;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
* API key (v3 auth):
* bbce7dd3d129803095fc7a69228da829
*
* Sample API request:
* https://api.themoviedb.org/3/movie/550?api_key=bbce7dd3d129803095fc7a69228da829
*
* Read access token for API (v4 auth):
* eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYmNlN2RkM2QxMjk4MDMwOTVmYzdhNjkyMjhkYTgyOSIsInN1YiI6IjVmYTM2ZmM0MzIzZWJhMDA0MTIwNDhkYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.0y9FpcwWsY1mumx2oUvWRofDq_DsQCfcdbZPzgAJ5og
*/
@Service
public class MovieRepository {
    private List<Movie> movies;
    private List<String> raw;
    private BufferedReader bufferedReader = new BufferedReader(new FileReader("movieDB.txt")); //change to data1.txt if you want to get data out of data1 into movieDB
    private String[] movieDetail;

    public MovieRepository() throws IOException {
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
            if(year < 1950) {
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
            movies.add(new Movie(movieDetail[0], movieDetail[2], movieDetail[5], movieDetail[7], movieDetail[8], moviePrice));
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

    public Movie getMovie(int id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }
    /*
    public void getDataFromData1ToMovieDB() throws IOException {

        File fout = new File("movieDB.txt");
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos));
        for (String s: raw) {

            if (s.contains("movie")) { //if you only wants movies in the movieDB file, otherwise add e.g. "short", "tvMovie" etc...look in Data1.txt for types
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }

        }
        bufferedWriter.close();

    }
    */



}
