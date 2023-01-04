/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ImplOrderDAO;
import entity.BookOrder;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author MY PC
 */
@Path("/order")
public class OrderService {
    ImplOrderDAO oDAO;

    public OrderService() {
        oDAO = new ImplOrderDAO();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getorder")
    public List<BookOrder> getOrders(){
        return oDAO.getOrders();
    }
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertOrder(BookOrder o){
        oDAO.insertOrder(o);
    }
    
    @PUT
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateOrder(BookOrder o){
        oDAO.updateOrder(o);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getorder/{userId}")
    public List<BookOrder> getOrdersByUserId(@PathParam("userId") int userId){
        return oDAO.getByUserId(userId);
    }
}
