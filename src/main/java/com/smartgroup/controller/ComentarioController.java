package com.smartgroup.controller;

import com.smartgroup.bean.Comentario;
import com.smartgroup.business.ComentarioBusiness;
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

@Path("comentarios")
public class ComentarioController {
    
    private ComentarioBusiness bus = new ComentarioBusiness();
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Comentario c) {
        try {

            bus.inserir(c);
            return Response.status(Response.Status.OK).build(); // 200
            
        } catch (Exception ex) {
            
            Logger.getLogger(ComentarioController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Comentario> getComentarios() {
        try {
        
            return bus.listar();
        
        } catch (Exception ex) {
            
            Logger.getLogger(ComentarioController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/")
    public Comentario getComentario(@PathParam("id") long id) {
        try {
        
            return bus.selecionar(id);
        
        } catch (Exception ex) {
            
            Logger.getLogger(ComentarioController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("enquete/{id-enquete}/")
    public List<Comentario> getComentariosByEenquete(@PathParam("id-enquete") 
            long idEnquete) {
        try {
        
            return bus.listarByEnquete(idEnquete);
        
        } catch (Exception ex) {
            
            Logger.getLogger(ComentarioController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }
    
    @GET
    @Path("enquete/{id-enquete}/count/")
    public long getCountComentariosByEenquete(@PathParam("id-enquete") 
            long idEnquete) {
        try {
        
            return bus.getListSizeByEnquete(idEnquete);
        
        } catch (Exception ex) {
            
            Logger.getLogger(ComentarioController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Comentario c) {
        try {
        
            bus.alterar(c);
            return Response.status(Response.Status.OK).build(); // 200
        
        } catch (Exception ex) {
            
            Logger.getLogger(ComentarioController.class.getName()).log(
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
            
            Logger.getLogger(ComentarioController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }
}
