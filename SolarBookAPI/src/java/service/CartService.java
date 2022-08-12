/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ImplCartDAO;
import entity.Book;
import entity.Cart;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author MY PC
 */
@Path("/cart")
public class CartService {
    ImplCartDAO cDAO;

    public CartService() {
        cDAO = new ImplCartDAO();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getcart")
    public List<Cart> getCarts(){
        return cDAO.getCart();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getcart/{userId}")
    public List<Cart> getBookNumberById(@PathParam("userId") int userId){
        return cDAO.getNumberByUser(userId);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getcart/book/{userId}")
    public List<Book> getListBookInCart(@PathParam("userId") int userId){
        return cDAO.getListBookInCart(userId);
    }
    
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public void deleteCart(@PathParam("id") int id){
        cDAO.delete(id);
    }
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertCart(Cart c){
        cDAO.insert(c);
    }
}
