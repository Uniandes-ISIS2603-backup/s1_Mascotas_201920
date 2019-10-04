/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaPerdidaDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaPerdidaLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaPerdidaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Lily
 */
@Path("mascotasperdidas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class MascotaPerdidaResource {
     private static final Logger LOGGER = Logger.getLogger(MascotaPerdidaResource.class.getName());

    @Inject
    private MascotaPerdidaLogic mascotaLogic;
    
    @POST
    public MascotaPerdidaDTO createMascotaPerdida (MascotaPerdidaDTO mascotaDto) throws BusinessLogicException{
        
        MascotaPerdidaEntity mascotaEntity = mascotaDto.toEntity();
        
        mascotaEntity = mascotaLogic.createMascotaPerdida(mascotaEntity);
                
        return new MascotaPerdidaDTO(mascotaEntity);
    }
    
//    @GET
//    public MascotaPerdidaDTO getMascotaPerdida(@PathParam("mascotasId") Long editorialsId) throws BusinessLogicException{
//        
//    }
//    
//    @PUT
//    public MascotaPerdidaDTO updateMascotaPerdida (MascotaPerdidaDTO mascotaDto){
//        return mascotaDto;
//    }
//    
//    @DELETE
//    public MascotaPerdidaDTO deleteMascotaPerdida (MascotaPerdidaDTO mascotaDto){
//        return mascotaDto;
//    }
}
