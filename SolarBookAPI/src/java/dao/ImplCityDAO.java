/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.City;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author TaiyoNg
 */
public class ImplCityDAO implements ICityDAO{
    Session session;

    public ImplCityDAO() {
    }
    
    
    @Override
    public List<City> getCity() {
        session = HibernateUtil.getSessionFactory().openSession();
        Query q = session.createQuery("From City");
        List<City> data = q.list();
        session.close();
        return data;
    }
    
}
