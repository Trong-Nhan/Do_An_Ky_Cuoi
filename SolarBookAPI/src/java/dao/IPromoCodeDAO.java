/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entity.PromoCode;
import java.util.List;

/**
 *
 * @author MY PC
 */
public interface IPromoCodeDAO {
    public List<PromoCode> getPromos();
    
    public void insert(PromoCode pc);
}
