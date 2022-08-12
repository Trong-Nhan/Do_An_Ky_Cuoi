/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.OrderDetail;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author MY PC
 */
public class ImplOrderDetailDAO implements IOrderDetailDAO{
    Session session;

    public ImplOrderDetailDAO() {
    }
    
    @Override
    public List<OrderDetail> getOrderDetail() {
        session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from User");
        List<OrderDetail> data = q.list();
        session.close();
        return data;
    }

    @Override
    public void insert(OrderDetail od) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(od);
        session.getTransaction().commit();
        session.close();
    }
    
}
