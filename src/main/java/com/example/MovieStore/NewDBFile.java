package com.example.MovieStore;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties;

import javax.sql.rowset.JdbcRowSet;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class NewDBFile {






    public NewDBFile() throws IOException {
        File fout = new File("db.txt");
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fos));

        FileReader fileReader = new FileReader("data1.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String tmp = null;
        String[] holder = new String[9];
        int counter = 0;

        while (bufferedReader.readLine() != null) {
            tmp = bufferedReader.readLine();
            holder = tmp.split("\t");
            for (int i = 0; i < holder.length; i++) {
                if (holder[1] != "tvEpisode") {
                    bufferedWriter.write(tmp);
                    bufferedWriter.newLine();
                }
            }

        }


        bufferedWriter.close();

    }




}
