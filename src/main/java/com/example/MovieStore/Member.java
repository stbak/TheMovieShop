package com.example.MovieStore;

import java.util.ArrayList;
import java.util.List;

public class Member {
    private int memberID;
    private String name;
    private String email;
    private String password;
    private List<String> favouriteList = new ArrayList<>();

    public Member(int memberID, String password){
        this.memberID=memberID;
        this.password=password;
    }

    public Member(int memberID, String name, String email, String password){
    this.memberID=memberID;
    this.name=name;
    this.email=email;
    this.password=password;
    }


    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getFavouriteList() {
        return favouriteList;
    }

    public void setFavouriteList(List<String> favouriteList) {
        this.favouriteList = favouriteList;
    }


}
