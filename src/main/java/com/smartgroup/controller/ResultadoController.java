package com.smartgroup.controller;

import com.smartgroup.bean.Resultado;
import com.smartgroup.business.ResultadoBusiness;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("resultados")
public class ResultadoController {

    private ResultadoBusiness bus = new ResultadoBusiness();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("enquete/{id-enquete}/")
    public Resultado getResultado(@PathParam("id-enquete") long idEnquete) {
        try {
        
            Resultado result = bus.calculaResultado(idEnquete);
            return result;
        
        } catch (Exception ex) {
            
            Logger.getLogger(ResultadoController.class.getName()).log(
                    Level.SEVERE, null, ex);
            throw new WebApplicationException(Response.Status
                    .INTERNAL_SERVER_ERROR); // 500
        
        }
    }
}
