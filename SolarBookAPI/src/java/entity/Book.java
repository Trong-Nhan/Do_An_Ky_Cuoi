/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author acer
 */
@Entity
@Table(name = "tblBook")
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Name")
    private String name;
    @Column(name = "CategoryId")
    private Integer categoryId;
    @Column(name = "Price")
    private Double price;
    @Column(name = "SalePrice")
    private Double salePrice;
    @Column(name = "Author")
    private String author;
    @Column(name = "PublisherId")
    private Integer publisherId;
    @Column(name = "PublishYear")
    private Integer publishYear;
    @Column(name = "Picture")
    private String picture;
    @Column(name = "Number")
    private Integer number;
    @Column(name = "Description")
    private String description;
    @Column(name = "Page")
    private Integer page;
    @Column(name = "Rating")
    private Double rating;
    @Column(name = "Status")
    private Boolean status;

    public Book() {
    }

    public Book(Integer id, String name, Integer categoryId, Double price, Double salePrice, String author, Integer publisherId, Integer publishYear, String picture, Integer number, String description, Integer page, Double rating, Boolean status) {
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

    public Book(String name, Integer categoryId, Double price, Double salePrice, String author, Integer publisherId, Integer publishYear, String picture, Integer number, String description, Integer page, Double rating, Boolean status) {
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
