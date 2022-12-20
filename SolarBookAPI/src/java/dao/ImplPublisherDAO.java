/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Publisher;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class ImplPublisherDAO implements IPublisherDAO {

    Session s;

    public ImplPublisherDAO() {
    }

    @Override
    public List<Publisher> getAll() {
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Query q = s.createQuery("from Publisher");
            List<Publisher> data = q.list();
            s.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(Publisher p) {
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.getTransaction().begin();
            s.save(p);
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Publisher getbyId(int id) {
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Publisher p = (Publisher) s.get(Publisher.class, id);
            s.close();
            return p;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        Publisher p = getbyId(id);
        s = HibernateUtil.getSessionFactory().openSession();
        s.getTransaction().begin();
        s.delete(p);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public void update(Publisher p) {
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.getTransaction().begin();
            s.update(p);
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
