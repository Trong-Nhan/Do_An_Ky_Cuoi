/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ImplUserDAO;
import entity.User;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author TaiyoNg
 */ 
@Path("/user")
public class UserService {
    ImplUserDAO uDAO;

    public UserService() {
        uDAO = new ImplUserDAO();
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getuser")
    public List<User> getUsers(){
        return uDAO.getUser();
    }
    
    @GET
    @Path("getuser/find")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public User getUserByEmail(@QueryParam("email") String email){
        return uDAO.findByEmail(email);
    }
    
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public void insertUser(User u){
        uDAO.insert(u);
    }
    
    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public void deleteUser(@PathParam("id") int id){
        uDAO.delete(id);
    }
}
