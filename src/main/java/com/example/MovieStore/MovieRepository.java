package com.example.MovieStore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class MovieRepository {

    @Autowired
    private DataSource dataSource;




    public List<Movie> MovieList() throws IOException {
        List<Movie> movies = new ArrayList<>();

        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM Movie")
        ) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                movies.add(rsMovie(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }


    private Movie rsMovie(ResultSet rs) throws SQLException {
        return new Movie(rs.getInt("MovieId"),
                rs.getInt("ImgId"),
                rs.getString("Title"),
                rs.getInt("MovieYear"),
                rs.getString("Genre"),
                rs.getString("Stars"),
                rs.getString("Director"),
                rs.getInt("PlayTime"),
                rs.getString("Rate"),
                rs.getInt("Price"),
                rs.getString("Description"));
    }

    public List<Movie> getPage(int page, int pageSize) throws IOException {
        int from = Math.max(0,page*pageSize);
        int to = Math.min(MovieList().size(),(page+1)*pageSize);
        return MovieList().subList(from, to);
    }

    public int numberOfPages(int pageSize) throws IOException {
        return (int)Math.ceil(new Double(MovieList().size()) / pageSize);
    }

    public Movie getMovie(Integer id) throws IOException {
        for (Movie movie : MovieList()) {
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
