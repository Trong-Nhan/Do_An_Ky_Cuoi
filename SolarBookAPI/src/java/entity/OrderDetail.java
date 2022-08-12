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
 * @author MY PC
 */
@Entity
@Table(name = "tblOrderDetail")
public class OrderDetail implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "OrderId")
    private int orderId;
    @Column(name = "BookId")
    private int bookId;
    @Column(name = "BookCount")
    private int bookCount;

    public OrderDetail() {
    }

    public OrderDetail(int id, int orderId, int bookId, int bookCount) {
        this.id = id;
        this.orderId = orderId;
        this.bookId = bookId;
        this.bookCount = bookCount;
    }

    public OrderDetail(int orderId, int bookId, int bookCount) {
        this.orderId = orderId;
        this.bookId = bookId;
        this.bookCount = bookCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getBookCount() {
        return bookCount;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

   
    
    
}
