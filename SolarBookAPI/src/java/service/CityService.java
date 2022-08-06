/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ImplCityDAO;
import entity.City;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author TaiyoNg
 */
@Path("/city")
public class CityService {
    ImplCityDAO cDAO;

    public CityService() {
        cDAO = new ImplCityDAO();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getcity")
    public List<City> getCity(){
        return cDAO.getCity();
    }
}
