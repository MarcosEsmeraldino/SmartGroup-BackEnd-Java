package com.smartgroup.controller;

import com.smartgroup.bean.Enquete;
import com.smartgroup.business.EnqueteBusiness;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("enquetes")
public class EnqueteController {
    
    private EnqueteBusiness bus = new EnqueteBusiness();
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Enquete e) {
        try {

            bus.inserir(e);
            return Response.status(Response.Status.OK).build(); // 200
            
        } catch (Exception ex) {
            
            Logger.getLogger(EnqueteController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Enquete> getEnquetes() {
        try {
        
            return bus.listar();
        
        } catch (Exception ex) {
            
            Logger.getLogger(EnqueteController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/")
    public Enquete getEnquete(@PathParam("id") long id) {
        try {
        
            return bus.selecionar(id);
        
        } catch (Exception ex) {
            
            Logger.getLogger(EnqueteController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("autor/{id-autor}")
    public List<Enquete> getEnquetes(@PathParam("id-autor") long idAutor) {
        try {
        
            return bus.listarByAutor(idAutor);
        
        } catch (Exception ex) {
            
            Logger.getLogger(EnqueteController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Enquete e) {
        try {
        
            bus.alterar(e);
            return Response.status(Response.Status.OK).build(); // 200
        
        } catch (Exception ex) {
            
            Logger.getLogger(EnqueteController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }
    
    @DELETE
    @Path("{id}/")
    public Response delete(@PathParam("id") long id) {
        try {
        
            bus.excluir(id);
            return Response.status(Response.Status.OK).build(); // 200
        
        } catch (Exception ex) {
            
            Logger.getLogger(EnqueteController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }
}
