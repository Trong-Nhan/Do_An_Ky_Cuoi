/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Book;
import entity.Cart;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author MY PC
 */
public class ImplCartDAO implements ICartDAO{
    Session session;

    public ImplCartDAO() {
    }

    @Override
    public List<Cart> getCart() {
        session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from Cart");
        List<Cart> data = q.list();
        session.close();
        return data;
    }

    @Override
    public void delete(int userId) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        Query q = session.createQuery("Delete Cart c Where c.userId = :userId");
        q.setParameter("userId", userId);
        q.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void insert(Cart c) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(c);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Cart> getNumberByUser(int userId) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("Select c From Cart c Where c.userId = :userId");
        q.setParameter("userId", userId);
        List<Cart> data = q.list();
        session.close();
        return data;
    }

   
     public List<Book> getListBookInCart(int userId) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createSQLQuery("Select * From Book b, Cart c Where b.id = c.bookId & c.userId = :userId");
        q.setParameter("userId", userId);
        List<Book> data = q.list();
        session.close();
        return data;
    }
    
}
