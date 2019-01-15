package com.smartgroup.backend.controller;

import com.smartgroup.backend.bean.Usuario;
import com.smartgroup.backend.business.UsuarioBusiness;
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

@Path("usuarios")
public class UsuarioController {
    
    private UsuarioBusiness bus = new UsuarioBusiness();
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Usuario u) {
        try {

            bus.inserir(u);
            return Response.status(Response.Status.OK).build(); // 200
            
        } catch (Exception ex) {
            
            Logger.getLogger(UsuarioController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Usuario> getUsuarios() {
        try {
        
            return bus.listar();
        
        } catch (Exception ex) {
            
            Logger.getLogger(UsuarioController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/")
    public Usuario getUsuario(@PathParam("id") long id) {
        try {
        
            return bus.selecionar(id);
        
        } catch (Exception ex) {
            
            Logger.getLogger(UsuarioController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Usuario u) {
        try {
        
            bus.alterar(u);
            return Response.status(Response.Status.OK).build(); // 200
        
        } catch (Exception ex) {
            
            Logger.getLogger(UsuarioController.class.getName()).log(
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
            
            Logger.getLogger(UsuarioController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }
}
