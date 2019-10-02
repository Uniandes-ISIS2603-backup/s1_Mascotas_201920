/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaAdopcionDTO;
import co.edu.uniandes.csw.mascotas.dtos.MascotaAdopcionDetailDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaAdopcionLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
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
 * @author Estudiante
 */
@Path("mascotasadopcion")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class MascotaAdopcionResource {
    
     private static final Logger LOGGER = Logger.getLogger(MascotaAdopcionResource.class.getName());

    @Inject
    private MascotaAdopcionLogic mascotaLogic;
    
    @POST
    public MascotaAdopcionDTO createMascotaAdopcion (MascotaAdopcionDTO mascotaDto) throws BusinessLogicException{
        
        MascotaAdopcionEntity mascotaEntity = mascotaDto.toEntity();
        
        mascotaEntity = mascotaLogic.createMascotaAdopcion(mascotaEntity);
                
        return new MascotaAdopcionDTO(mascotaEntity);
    }
    
    @GET
    @Path("{mascotasId: \\d+}")
    public MascotaAdopcionDetailDTO getMascotaAdopcion(@PathParam("mascotasId") Long mascotasId) throws BusinessLogicException{
        MascotaAdopcionEntity entidad = mascotaLogic.getMascotaAdopcion(mascotasId);
        if(entidad == null){
            throw new WebApplicationException("El recurso /mascotasadopcion/"+mascotasId+" no existe", 404);
        }
        return new MascotaAdopcionDetailDTO(entidad);
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
