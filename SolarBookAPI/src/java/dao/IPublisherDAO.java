/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Publisher;
import java.util.List;

/**
 *
 * @author acer
 */
public interface IPublisherDAO {
    public List<Publisher> getAll();

    public void insert(Publisher p);

    public void delete(int id);

    public void update(Publisher p);
}
