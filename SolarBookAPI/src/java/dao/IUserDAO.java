/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.User;
import java.util.List;

/**
 *
 * @author TaiyoNg
 */
public interface IUserDAO {
    public List<User> getUser();
    public User findById(int userId);
    public User findByEmail(String email);
}
