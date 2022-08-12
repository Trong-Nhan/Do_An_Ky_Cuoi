/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.PromoCode;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author MY PC
 */
public class ImplPromoCodeDAO implements IPromoCodeDAO{

    Session session;

    public ImplPromoCodeDAO() {
    }
    
    
    @Override
    public List<PromoCode> getPromos() {
        session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from PromoCode");
        List<PromoCode> data = q.list();
        session.close();
        return data;
    }

    @Override
    public void insert(PromoCode pc) {
        session = HibernateUtil.getSessionFactory().openSession();
        session.getTransaction().begin();
        session.save(pc);
        session.getTransaction().commit();
        session.close();
    }
    
}
