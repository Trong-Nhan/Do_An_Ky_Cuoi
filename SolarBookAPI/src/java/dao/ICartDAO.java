/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Book;
import entity.Cart;
import java.util.List;

/**
 *
 * @author MY PC
 */
public interface ICartDAO {
    public List<Cart> getCart();
    public void insert(Cart c);
    public void delete(int userId);
    public List<Cart> getNumberByUser(int userId);
}

