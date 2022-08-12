/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ImplOrderDetailDAO;
import entity.OrderDetail;
import entity.User;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author MY PC
 */
@Path("/orderdetail")
public class OrderDetailService {
    ImplOrderDetailDAO odDAO;

    public OrderDetailService() {
        odDAO = new ImplOrderDetailDAO();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getorderdetail")
    public List<OrderDetail> getOrderDetail(){
        return odDAO.getOrderDetail();
    }
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertOrderDetail(OrderDetail od){
        odDAO.insert(od);
    }
}
