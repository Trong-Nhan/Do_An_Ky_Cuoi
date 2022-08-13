/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.BookOrder;
import java.util.List;

/**
 *
 * @author MY PC
 */
public interface IOrderDAO {
    public List<BookOrder> getOrders();
    
    public void insertOrder(BookOrder o);
    
    public void updateOrder(BookOrder o);
}
