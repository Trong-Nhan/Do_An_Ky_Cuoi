/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ImplPaymentDAO;
import entity.Payment;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author MY PC
 */
@Path("/payment")
public class PaymentService {
    ImplPaymentDAO pDAO;

    public PaymentService() {
        pDAO = new ImplPaymentDAO();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getpayment")
    public List<Payment> getPayments(){
        return pDAO.getPayments();
    }
   
}
