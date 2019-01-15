package com.smartgroup.backend.controller;

import com.smartgroup.backend.bean.Voto;
import com.smartgroup.backend.business.VotoBusiness;
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

@Path("votos")
public class VotoController {
    
    private VotoBusiness bus = new VotoBusiness();
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Voto v) {
        try {

            bus.inserir(v);
            return Response.status(Response.Status.OK).build(); // 200
            
        } catch (Exception ex) {
            
            Logger.getLogger(VotoController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("enquete/{id-enquete}/")
    public List<Voto> getVotosByEnquete(@PathParam("id-enquete") long idEnquete) {
        try {
        
            return bus.listarByEnquete(idEnquete);
        
        } catch (Exception ex) {
            
            Logger.getLogger(VotoController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }

    @GET
    @Path("enquete/{id-enquete}/count/")
    public long getCountVotosByEnquete(@PathParam("id-enquete") long idEnquete) {
        try {
        
            return bus.getListSizeByEnquete(idEnquete);
        
        } catch (Exception ex) {
            
            Logger.getLogger(VotoController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Voto> getVotos() {
        try {
        
            return bus.listar();
        
        } catch (Exception ex) {
            
            Logger.getLogger(VotoController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}/")
    public Voto getVoto(@PathParam("id") long id) {
        try {
        
            return bus.selecionar(id);
        
        } catch (Exception ex) {
            
            Logger.getLogger(VotoController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Voto v) {
        try {
        
            bus.alterar(v);
            return Response.status(Response.Status.OK).build(); // 200
        
        } catch (Exception ex) {
            
            Logger.getLogger(VotoController.class.getName()).log(
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
            
            Logger.getLogger(VotoController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }
}
