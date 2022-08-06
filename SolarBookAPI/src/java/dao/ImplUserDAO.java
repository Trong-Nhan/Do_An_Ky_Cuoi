/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author TaiyoNg
 */
public class ImplUserDAO implements IUserDAO{
    Session session;

    public ImplUserDAO() {
    }
    

    @Override
    public List<User> getUser() {
        session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from User");
        List<User> data = q.list();
        session.close();
        return data;
    }

    @Override
    public User findById(int userId) {
        session = HibernateUtil.getSessionFactory().openSession();
        User u = (User) session.get(User.class, userId);
        session.close();
        return u;
    }

    @Override
    public User findByEmail(String email) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("select u from User u where u.email like :email");
        q.setParameter("email", email);
        User u = (User) q.uniqueResult();
        session.close();
        return u;
    }

    @Override
    public void insert(User u) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(u);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(User u) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(u);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(int userId) {
        session = HibernateUtil.getSessionFactory().openSession();
        User u = (User) session.get(User.class, userId);
        session.getTransaction().begin();
        session.delete(u);
        session.getTransaction().commit();
        session.close();
    }
    
}
