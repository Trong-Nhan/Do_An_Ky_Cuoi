/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TaiyoNg
 */
@Entity
@Table(name = "tblOrder")
public class BookOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "UserId")
    @NotNull
    private Integer userId;
    @Column(name = "OrderDate")
    private String orderDate;
    @Column(name = "BookId")
    @NotNull
    private Integer bookId;
    @Column(name = "BookNumber")
    private Integer bookNumber;
    @Column(name = "TotalPrice")
    private Double totalPrice;
    @Column(name = "CityId")
    @NotNull
    private Integer cityId;
    @Column(name = "ShippingAddress")
    private String shippingAddress;
    @Column(name = "ShippingPrice")
    private Integer shippingPrice;
    @Column(name = "PaymentId")
    @NotNull
    private Integer paymentId;
    @Column(name = "Note")
    private String note;
    @Column(name = "Status")
    private String status;

    public BookOrder() {
    }

    public BookOrder(Integer id, Integer userId, String orderDate, Integer bookId, Integer bookNumber, Double totalPrice, Integer cityId, String shippingAddress, Integer shippingPrice, Integer paymentId, String note, String status) {
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

    public BookOrder(Integer userId, String orderDate, Integer bookId, Integer bookNumber, Double totalPrice, Integer cityId, String shippingAddress, Integer shippingPrice, Integer paymentId, String note, String status) {
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

    public Integer getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(Integer bookNumber) {
        this.bookNumber = bookNumber;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Integer getShippingPrice() {
        return shippingPrice;
    }

    public void setShippingPrice(Integer shippingPrice) {
        this.shippingPrice = shippingPrice;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
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
