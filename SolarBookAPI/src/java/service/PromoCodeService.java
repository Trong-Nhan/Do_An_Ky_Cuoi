/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ImplPromoCodeDAO;
import entity.PromoCode;
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
@Path("/promo")
public class PromoCodeService {
    ImplPromoCodeDAO pcDAO;

    public PromoCodeService() {
        pcDAO = new ImplPromoCodeDAO();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getpromo")
    public List<PromoCode> getPromos(){
        return pcDAO.getPromos();
    }
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertPromo(PromoCode pc){
        pcDAO.insert(pc);
    }
}
