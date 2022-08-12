/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Payment;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author MY PC
 */
public class ImplPaymentDAO {
    Session session;

    public ImplPaymentDAO() {
    }
    
    public List<Payment> getPayments(){
        session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("from Payment");
        List<Payment> data = q.list();
        session.close();
        return data;
    }
}
