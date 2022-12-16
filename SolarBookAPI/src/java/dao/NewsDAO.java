/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.News;
import java.util.List;

/**
 *
 * @author Nhan
 */
public interface NewsDAO {

    public List<News> getAll();

    public void insert(News b);

    public void delete(int id);

    public void update(News b);

}
