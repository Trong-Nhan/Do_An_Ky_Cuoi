/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Category;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class ImplCategoryDAO implements ICategoryDAO {

    Session session;

    public ImplCategoryDAO() {
    }

    @Override
    public List<Category> getAll() {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Query q = session.createQuery("from Category");
            List<Category> data = q.list();
            session.close();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(Category c) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.save(c);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Category getById(int id) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Category c = (Category) session.get(Category.class, id);
            session.close();
            return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(int id) {
        try {
            Category b = getById(id);
            session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.delete(b);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category c) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.getTransaction().begin();
            session.update(c);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
