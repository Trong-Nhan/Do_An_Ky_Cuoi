/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ImplNewsDAO;
import entity.News;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Admin
 */
@Path("/news")
public class NewsService {

    ImplNewsDAO nDAO;

    public NewsService() {
        nDAO = new ImplNewsDAO();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getnews")
    public List<News> getNews() {
        return nDAO.getAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("addnews")
    public void addNews(News n) {
        nDAO.insert(n);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("updatenews")
    public void updateNews(News n) {
        nDAO.update(n);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("deletenews/{id}")
    public void deleteNews(@PathParam("id") int id) {
        nDAO.delete(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("detailnews/{id}")
    public News detail(@PathParam("id") int id) {
        News b = null;
        b = nDAO.getById(id);
        return b;
    }
}
