package com.example.projectfinal.entity;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    private int id;
    private int userId;
    private String orderDate;
    private int bookId;
    private int bookNumber;
    private float totalPrice;
    private int cityId;
    private String shippingAddress;
    private int shippingPrice;
    private int paymentId;
    private String note;
    private String status;

    public Order() {
    }

    public Order(int id, int userId, String orderDate, int bookId, int bookNumber, float totalPrice, int cityId, String shippingAddress, int shippingPrice, int paymentId, String note, String status) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.bookId = bookId;
        this.bookNumber = bookNumber;
        this.totalPrice = totalPrice;
        this.cityId = cityId;
        this.shippingAddress = shippingAddress;
        this.shippingPrice = shippingPrice;
        this.paymentId = paymentId;
        this.note = note;
        this.status = status;
    }

    public Order(int userId, String orderDate, int bookId, int bookNumber, float totalPrice, int cityId, String shippingAddress, int shippingPrice, int paymentId, String note, String status) {
        this.userId = userId;
        this.orderDate = orderDate;
        this.bookId = bookId;
        this.bookNumber = bookNumber;
        this.totalPrice = totalPrice;
        this.cityId = cityId;
        this.shippingAddress = shippingAddress;
        this.shippingPrice = shippingPrice;
        this.paymentId = paymentId;
        this.note = note;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(int bookNumber) {
        this.bookNumber = bookNumber;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public int getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(int shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
