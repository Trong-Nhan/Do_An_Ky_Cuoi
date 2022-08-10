/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Book;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author acer
 */
public class ImplBookDAO implements IBookDAO {

    Session s;

    public ImplBookDAO() {
    }

    @Override
    public List<Book> getAll() {
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Query q = s.createQuery("from Book");
            List<Book> data = q.list();
            s.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(Book b) {
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.getTransaction().begin();
            s.save(b);
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Book getbyId(int id) {
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Book b = (Book) s.get(Book.class, id);
            s.close();
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        try {
            Book b = getbyId(id);
            s = HibernateUtil.getSessionFactory().openSession();
            s.getTransaction().begin();
            s.delete(b);
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Book b) {
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            s.getTransaction().begin();
            s.update(b);
            s.getTransaction().commit();
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book findByName(String name) {
        s = HibernateUtil.getSessionFactory().openSession();
        Query q = s.createQuery("select b from Book u where u.name like :name");
        q.setParameter("name", name);
        Book u = (Book) q.uniqueResult();
        s.close();
        return u;
    }

}
