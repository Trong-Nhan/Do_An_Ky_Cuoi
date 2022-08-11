/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author MY PC
 */
@Entity
@Table(name = "tblOrder")
public class BookOrder implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "UserId")
    private int userId;
    @Column(name = "OrderDate")
    private Date orderDate;
    @Column(name = "PromoCodeId")
    private int promoCodeId;
    @Column(name = "TotalPrice")
    private float totalPrice;
    @Column(name = "CityId")
    private int cityId;
    @Column(name = "ShippingAddress")
    private String shippingAddress;
    @Column(name = "ShippingPrice")
    private int shippingPrice;
    @Column(name = "PaymentId")
    private int paymentId;
    @Column(name = "Note")
    private String note;
    @Column(name = "CreatedDate")
    private Date createdDate;

    public BookOrder() {
    }

    public BookOrder(int id, int userId, Date orderDate, int promoCodeId, float totalPrice, int cityId, String shippingAddress, int shippingPrice, int paymentId, String note, Date createdDate) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.promoCodeId = promoCodeId;
        this.totalPrice = totalPrice;
        this.cityId = cityId;
        this.shippingAddress = shippingAddress;
        this.shippingPrice = shippingPrice;
        this.paymentId = paymentId;
        this.note = note;
        this.createdDate = createdDate;
    }

    public BookOrder(int userId, Date orderDate, int promoCodeId, float totalPrice, int cityId, String shippingAddress, int shippingPrice, int paymentId, String note, Date createdDate) {
        this.userId = userId;
        this.orderDate = orderDate;
        this.promoCodeId = promoCodeId;
        this.totalPrice = totalPrice;
        this.cityId = cityId;
        this.shippingAddress = shippingAddress;
        this.shippingPrice = shippingPrice;
        this.paymentId = paymentId;
        this.note = note;
        this.createdDate = createdDate;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getPromoCodeId() {
        return promoCodeId;
    }

    public void setPromoCodeId(int promoCodeId) {
        this.promoCodeId = promoCodeId;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

   
    
    
}
