/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaAdopcionDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaAdopcionLogic;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Estudiante
 */
@Path("mascotasadopcion")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class MascotaAdopcionResource {
    
     private static final Logger LOGGER = Logger.getLogger(MascotaAdopcionResource.class.getName());

    @Inject
    private MascotaAdopcionLogic macotaLogic;
    
    @POST
    public MascotaAdopcionDTO createMascotaAdopcion (MascotaAdopcionDTO mascotaDto){
        return mascotaDto;
    }
    
    @GET
    public MascotaAdopcionDTO getMascotaAdopcion (MascotaAdopcionDTO mascotaDto){
        return mascotaDto;
    }
    
    @PUT
    public MascotaAdopcionDTO updateMascotaAdopcion (MascotaAdopcionDTO mascotaDto){
        return mascotaDto;
    }
    
    @DELETE
    public MascotaAdopcionDTO deleteMascotaAdopcion (MascotaAdopcionDTO mascotaDto){
        return mascotaDto;
    }
    
}
