package com.example.MovieStore;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MovieGrade {


    public void gradeThatMovie(String id) throws IOException {

        File fout = new File("/ratings/rating_"+id+".txt");
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos));

        List<String> raw = new ArrayList<>();
        for (String s: raw) {

            if (s.contains("movie")) { //if you only wants movies in the movieDB file, otherwise add e.g. "short", "tvMovie" etc...look in Data1.txt for types
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }

        }
        bufferedWriter.close();

    }


}
