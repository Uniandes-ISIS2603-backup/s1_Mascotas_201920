/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaPerdidaDTO;
import co.edu.uniandes.csw.mascotas.dtos.MascotaPerdidaDetailDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaPerdidaLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaPerdidaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
         LOGGER.log(Level.INFO, "MascotaEncontradaResource createMascotaPerdida: input(0)", mascotaDto);
        
        MascotaPerdidaEntity mascotaEntity = mascotaDto.toEntity();
        
        mascotaEntity = mascotaLogic.createMascotaPerdida(mascotaEntity);
        
        MascotaPerdidaDTO nuevaMascota = new MascotaPerdidaDTO(mascotaEntity);
        
        LOGGER.log(Level.INFO, "MascotaEncontradaResource createMascotaEncontrada: output(0)", nuevaMascota);
        return nuevaMascota;
    }
    
    @GET
    @Path("{mascotasid: \\d+}")
    public MascotaPerdidaDetailDTO getMascotaPerdida(@PathParam("mascotasid") Long mascotasId) throws BusinessLogicException {
        MascotaPerdidaEntity entidad = mascotaLogic.getMascotaPerdida(mascotasId);
        if (entidad == null) {
            throw new WebApplicationException("El recurso /mascotasadopcion/" + mascotasId + " no existe", 404);
        }
        return new MascotaPerdidaDetailDTO(entidad);
    }

    @GET
    public List<MascotaPerdidaDetailDTO> getMacotasPerdida() throws BusinessLogicException {
        
       List<MascotaPerdidaDetailDTO> listaMascotas = listEntity2DTO(mascotaLogic.getMascotasPerdida());
        
        return listaMascotas;
    }
    
 
    @PUT
     @Path("{mascotasid: \\d+}")
    public MascotaPerdidaDTO updateMascotaPerdida(@PathParam("mascotasid") Long mascotasId, MascotaPerdidaDTO mascota) throws BusinessLogicException {
        mascota.setId(mascotasId);
        if (mascotaLogic.getMascotaPerdida(mascotasId) == null) {
            throw new WebApplicationException("El recurso /mascotasadopcion/" + mascotasId + " no existe.", 404);
        }
        MascotaPerdidaDetailDTO detailDTO = new MascotaPerdidaDetailDTO(mascotaLogic.updateMascotaPerdida(mascota.toEntity()));
        return detailDTO;
    }
    

    @DELETE
    @Path("{mascotasid: \\d+}")
    public void deleteMascotaPerdida(@PathParam("mascotasid") Long mascotasId) throws BusinessLogicException {
         if (mascotaLogic.getMascotaPerdida(mascotasId) == null) {
            throw new WebApplicationException("El recurso /mascotasadopcion/" + mascotasId + " no existe.", 404);
        }
        mascotaLogic.deleteMascotaPerdida(mascotasId);
    }

    private List<MascotaPerdidaDetailDTO> listEntity2DTO(List<MascotaPerdidaEntity> entityList) {
        List<MascotaPerdidaDetailDTO> list = new ArrayList<>();
        for (MascotaPerdidaEntity entity : entityList) {
            list.add(new MascotaPerdidaDetailDTO(entity));
        }
        return list;
    }
}
