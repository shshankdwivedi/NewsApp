package com.example.newsapp;

public class CategoryModel {

    private String category;
    private String categoyImages;

    public CategoryModel(String category, String categoyImages) {
        this.category = category;
        this.categoyImages = categoyImages;
    }

    public String getCategory() {
        return category;
    }

    public String getCategoyImages() {
        return categoyImages;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCategoyImages(String categoyImages) {
        this.categoyImages = categoyImages;
    }
}
