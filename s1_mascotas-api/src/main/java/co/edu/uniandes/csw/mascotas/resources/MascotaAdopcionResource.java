/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaAdopcionDTO;
import co.edu.uniandes.csw.mascotas.dtos.MascotaAdopcionDetailDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaAdopcionLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaAdopcionUsuarioLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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
    
    private static final String PRIM= "El recurso /mascotasadopcion/";
    private static final String NO=" no existe.";
    @Inject
    private MascotaAdopcionLogic mascotaLogic;
    
    @Inject
    private MascotaAdopcionUsuarioLogic mascotaUsuarioLogic;

    @POST
    public MascotaAdopcionDTO createMascotaAdopcion(MascotaAdopcionDTO mascotaDto) throws BusinessLogicException {

        MascotaAdopcionEntity mascotaEntity = mascotaDto.toEntity();

        mascotaEntity = mascotaLogic.createMascotaAdopcion(mascotaEntity);
        
        return  new MascotaAdopcionDTO(mascotaEntity);

        
    }

    @GET
    @Path("{mascotasid: \\d+}")
    public MascotaAdopcionDetailDTO getMascotaAdopcion(@PathParam("mascotasid") Long mascotasId) throws BusinessLogicException {
        MascotaAdopcionEntity entidad = mascotaLogic.getMascotaAdopcion(mascotasId);
        if (entidad == null) {
            throw new WebApplicationException(PRIM+ mascotasId +NO, 404);
        }
        return new MascotaAdopcionDetailDTO(entidad);
        
    }

    @GET
    public List<MascotaAdopcionDetailDTO> getMacotasAdopcion() throws BusinessLogicException {

        return listEntity2DTO(mascotaLogic.getMascotasAdopcion());

    }
    
 
    @PUT
     @Path("{mascotasid: \\d+}")
    public MascotaAdopcionDTO updateMascotaAdopcion(@PathParam("mascotasid") Long mascotasId, MascotaAdopcionDTO mascota) throws BusinessLogicException {
        mascota.setId(mascotasId);
        if (mascotaLogic.getMascotaAdopcion(mascotasId) == null) {
            throw new WebApplicationException(PRIM + mascotasId + NO, 404);
        }
        return new MascotaAdopcionDetailDTO(mascotaLogic.updateMascotaAdopcion(mascota.toEntity()));
        
    }
    

    @DELETE
    @Path("{mascotasid: \\d+}")
    public void deleteMascotaAdopcion(@PathParam("mascotasid") Long mascotasId) throws BusinessLogicException {
         if (mascotaLogic.getMascotaAdopcion(mascotasId) == null) {
            throw new WebApplicationException(PRIM + mascotasId + NO, 404);
        }
         mascotaUsuarioLogic.removeUsuario(mascotasId);
        mascotaLogic.deleteMascotaAdopcion(mascotasId);
    }

    private List<MascotaAdopcionDetailDTO> listEntity2DTO(List<MascotaAdopcionEntity> entityList) {
        List<MascotaAdopcionDetailDTO> list = new ArrayList<>();
        for (MascotaAdopcionEntity entity : entityList) {
            list.add(new MascotaAdopcionDetailDTO(entity));
        }
        return list;
    }

}
