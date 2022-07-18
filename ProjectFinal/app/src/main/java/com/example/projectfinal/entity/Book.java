package com.example.projectfinal.entity;

public class Book {
    private String name;
    private int imgResouce;
    private String author;
    private float rating;
    private int price;
    private int salePrice;

    public Book() {
    }

    public Book(String name, int imgResouce, String author, float rating, int price, int salePrice) {
        this.name = name;
        this.imgResouce = imgResouce;
        this.author = author;
        this.rating = rating;
        this.price = price;
        this.salePrice = salePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgResouce() {
        return imgResouce;
    }

    public void setImgResouce(int imgResouce) {
        this.imgResouce = imgResouce;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }
}
