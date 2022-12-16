/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.Book;
import java.util.List;

/**
 *
 * @author Nhan
 */
public interface IBookDAO {

    public List<Book> getAll();

    public void insert(Book b);

    public void delete(int id);

    public void update(Book b);

    public List<Book> findByName(String name);
}
