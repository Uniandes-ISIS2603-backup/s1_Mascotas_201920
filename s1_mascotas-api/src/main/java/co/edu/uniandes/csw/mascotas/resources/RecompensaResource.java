/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.RecompensaDTO;
import co.edu.uniandes.csw.mascotas.ejb.RecompensaLogic;
import co.edu.uniandes.csw.mascotas.entities.RecompensaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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
 * @author William Smith
 */
@Path("recompensas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class RecompensaResource {
    
    private static final Logger LOGGER = Logger.getLogger(RecompensaResource.class.getName());
    
    @Inject
    private RecompensaLogic recompensaLogic;
    
    @POST
    public RecompensaDTO createRecompensa(RecompensaDTO recompensaDTO) throws BusinessLogicException{
        RecompensaEntity recompensaEntity=recompensaDTO.toEntity();
        RecompensaEntity newRecompensaEntity=recompensaLogic.createRecompensa(recompensaEntity);
        RecompensaDTO newRecompensaDTO=new RecompensaDTO(newRecompensaEntity);
        return newRecompensaDTO;
    }
    
    @GET
    public List<RecompensaDTO> getRecompensas(){
        List<RecompensaDTO> listaRecompensas=listEntity2DTO(recompensaLogic.findAllRecompensas());
        return listaRecompensas;  
    }
    
     @GET
     @Path("{recompensasId: \\d+}")
    public RecompensaDTO getRecompensa(@PathParam("recompensasId") Long recompensaID)throws WebApplicationException{
        RecompensaEntity recompensaEntity=recompensaLogic.findRecompensa(recompensaID);
        if(recompensaEntity==null){
            throw new WebApplicationException("El recurso /recompensas/" + recompensaID + " no existe.", 404);
        }
        RecompensaDTO recompensaDTO=new RecompensaDTO(recompensaEntity);
        return recompensaDTO;
    }
    
     @PUT
     @Path("{recompensasId: \\d+}")
    public RecompensaDTO updateRecompensa(@PathParam("recompensasId") Long recompensaID,RecompensaDTO recompensa) throws WebApplicationException, BusinessLogicException{
        recompensa.setId(recompensaID);
        if(recompensaLogic.findRecompensa(recompensaID)==null){
            throw new WebApplicationException("El recurso /recompensas/" + recompensaID + " no existe.", 404);
        }
        RecompensaDTO recompensaDTO=new RecompensaDTO(recompensaLogic.updateRecompensa(recompensa.toEntity()));
        return recompensaDTO;
    }
    
     @DELETE
     @Path("{recompensasId: \\d+}")
    public void deleteRecompensa(@PathParam("recomensasId") Long recompensaID) throws BusinessLogicException{
        if(recompensaLogic.findRecompensa(recompensaID)==null){
            throw new WebApplicationException("El recurso /recompensas/" + recompensaID + " no existe.", 404);
        }
        recompensaLogic.deleteRecompensa(recompensaID);
    }

    private List<RecompensaDTO> listEntity2DTO(List<RecompensaEntity> entityList) {
        List<RecompensaDTO> list=new ArrayList<>();
        for(RecompensaEntity entity : entityList){
            list.add(new RecompensaDTO(entity));
        }
        return list;
    }
    
}
