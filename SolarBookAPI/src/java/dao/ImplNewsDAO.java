/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.News;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Admin
 */
public class ImplNewsDAO implements NewsDAO {

    Session session;

    public ImplNewsDAO() {
    }

    @Override
    public List<News> getAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from News");
        List<News> data = q.list();
        session.close();
        return data;
    }

    @Override
    public void insert(News b) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(b);
        session.getTransaction().commit();
        session.close();
    }

    public News getById(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        News n = (News) session.get(News.class, id);
        session.close();
        return n;
    }

    @Override
    public void delete(int id) {
        News b = getById(id);
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.delete(b);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(News b) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.update(b);
        session.getTransaction().commit();
        session.close();
    }

}
