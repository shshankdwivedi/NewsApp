package com.example.newsapp;

import java.util.ArrayList;

public class NewsModel {

    private int totalResult;
    private String status;
    private ArrayList<Articles> articles;

    public int getTotalResult() {
        return totalResult;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<Articles> getArticles() {
        return articles;
    }

}
