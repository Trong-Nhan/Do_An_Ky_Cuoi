package service;


import dao.ImplPublisherDAO;
import entity.Publisher;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author acer
 */
@Path("/publisher")
public class PublisherService {
    ImplPublisherDAO pDAO;

    public PublisherService() {
        pDAO = new ImplPublisherDAO();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getpublisher")
    public List<Publisher> getPublisher() {
        return pDAO.getAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("addpublisher")
    public void addPublisher(Publisher n) {
        pDAO.insert(n);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("updatepublisher")
    public void updatePublisher(Publisher n) {
        pDAO.update(n);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("deletepublisher/{id}")
    public void deletePublisher(@PathParam("id") int id) {
        pDAO.delete(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("detailpublisher/{id}")
    public Publisher detailCategory(@PathParam("id") int id) {
        Publisher p = null;
        p = pDAO.getbyId(id);
        return p;
    }
}
