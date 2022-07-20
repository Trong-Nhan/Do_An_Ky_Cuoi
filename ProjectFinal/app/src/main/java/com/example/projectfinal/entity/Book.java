package com.example.projectfinal.entity;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String name;
    private int categoryId;
    private String price;
    private String salePrice;
    private String author;
    private int publisherId;
    private int publishYear;
    private int picture;
    private int number;
    private String description;
    private int page;
    private float rating;
    private boolean status;

    public String toString(String s) {
        return s.toString();
    }

    public Book() {
    }

    public Book(int id, String name, int categoryId, String price, String salePrice, String author, int publisherId, int publishYear, int picture, int number, String description, int page, float rating, boolean status) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
        this.salePrice = salePrice;
        this.author = author;
        this.publisherId = publisherId;
        this.publishYear = publishYear;
        this.picture = picture;
        this.number = number;
        this.description = description;
        this.page = page;
        this.rating = rating;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
