/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaAdopcionDTO;
import co.edu.uniandes.csw.mascotas.dtos.ProcesoAdopcionDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author William Smith
 */

@Path("procesos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ProcesoAdopcionResource {
    
    private static final Logger LOGGER = Logger.getLogger(ProcesoAdopcionResource.class.getName());
    
    @POST
    public ProcesoAdopcionDTO createProcesoAdopcion(ProcesoAdopcionDTO procesoDto){
        return procesoDto;
    }
    
     @GET
    public ProcesoAdopcionDTO getProcesoAdopcion(ProcesoAdopcionDTO procesoDto){
        return procesoDto;
    }
    
     @PUT
    public ProcesoAdopcionDTO updateProcesoAdopcion(ProcesoAdopcionDTO procesoDto){
        return procesoDto;
    }
    
     @DELETE
    public ProcesoAdopcionDTO deleteProcesoAdopcion(ProcesoAdopcionDTO procesoDto){
        return procesoDto;
    }
    
}
