/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.BookOrder;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author MY PC
 */
public class ImplOrderDAO implements IOrderDAO{
    
    Session session;

    public ImplOrderDAO() {
    }
    
    
    @Override
    public List<BookOrder> getOrders() {
        session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from BookOrder");
        List<BookOrder> data = q.list();
        session.close();
        return data;
    }

    @Override
    public void insertOrder(BookOrder o) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(o);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateOrder(BookOrder o) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(o);
        session.getTransaction().commit();
        session.close();
    }
    
    public List<BookOrder> getByUserId(int userId){
        session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("select o from BookOrder o where userId like :userId");
        q.setInteger("userId", userId);
        List<BookOrder> data = q.list();
        return data;
    }
    
}
