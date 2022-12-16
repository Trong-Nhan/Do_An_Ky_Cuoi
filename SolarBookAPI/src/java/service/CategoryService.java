/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.ImplCategoryDAO;
import entity.Category;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Nhan
 */
@Path("/category")
public class CategoryService {
    ImplCategoryDAO cDAO;

    public CategoryService() {
        cDAO = new ImplCategoryDAO();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getcategory")
    public List<Category> getCategory() {
        return cDAO.getAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("addcategory")
    public void addCategory(Category n) {
        cDAO.insert(n);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("updatecategory")
    public void updateCategory(Category n) {
        cDAO.update(n);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("deletecategory/{id}")
    public void deleteCategory(@PathParam("id") int id) {
        cDAO.delete(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("detailcategory/{id}")
    public Category detailCategory(@PathParam("id") int id) {
        Category c = null;
        c = cDAO.getById(id);
        return c;
    }
}
