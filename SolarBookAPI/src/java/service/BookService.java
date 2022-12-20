/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ImplBookDAO;
import entity.Book;
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
@Path("/book")
public class BookService {

    ImplBookDAO bDAO;

    public BookService() {
        bDAO = new ImplBookDAO();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getbook")
    public List<Book> getBook() {
        return bDAO.getAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("addbook")
    public void addBook(Book n) {
        bDAO.insert(n);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("updatebook")
    public void updateBook(Book n) {
        bDAO.update(n);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("deletebook/{id}")
    public void deleteBook(@PathParam("id") int id) {
        bDAO.delete(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("detailbook/{id}")
    public Book detailBook(@PathParam("id") int id) {
        Book c = null;
        c = bDAO.getbyId(id);
        return c;
    }

    @GET
    @Path("getbook/find")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public List<Book> getUserByEmail(@QueryParam("name") String name) {
        return bDAO.findByName(name);
    }
}
