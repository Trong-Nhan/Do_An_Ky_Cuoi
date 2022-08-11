/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
/**
 *
 * @author MY PC
 */
@Entity
@Table(name = "tblCart")
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "BookCount")
    private int bookCount;
    @Column(name = "BookId")
    private int bookId;
    @Column(name = "UserId")
    private int userId;

    public Cart() {
    }

    public Cart(int id, int bookCount, int bookId, int userId) {
        this.id = id;
        this.bookCount = bookCount;
        this.bookId = bookId;
        this.userId = userId;
    }

    public Cart(int bookCount, int bookId, int userId) {
        this.bookCount = bookCount;
        this.bookId = bookId;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    
    
}
