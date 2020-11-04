package com.example.MovieStore;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieRepository {
    private List<Movie> movies;

    public MovieRepository() {
        movies = new ArrayList<>();

        for (int i = 1; i <= 95; i++) {
            movies.add(new Movie(200+i, "Movie Title " + i, "Author name " + i, 40 + i + 1.99, "Dec 31, " + (1910+i)));
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
}
