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
    
    private static final String PRIM= "El recurso /recompensas/";
    private static final String NO=" no existe.";
    @Inject
    private RecompensaLogic recompensaLogic;
    
    
 
    
    @POST
    public RecompensaDTO createRecompensa(RecompensaDTO recompensaDTO) throws BusinessLogicException{
        RecompensaEntity recompensaEntity=recompensaDTO.toEntity();
        RecompensaEntity newRecompensaEntity=recompensaLogic.createRecompensa(recompensaEntity);
       
        return new RecompensaDTO(newRecompensaEntity);
    }
    
    @GET
    public List<RecompensaDTO> getRecompensas(){
 
        return listEntity2DTO(recompensaLogic.findAllRecompensas());
    }
    
     @GET
     @Path("{recompensasId: \\d+}")
    public RecompensaDTO getRecompensa(@PathParam("recompensasId") Long recompensaID){
        RecompensaEntity recompensaEntity=recompensaLogic.findRecompensa(recompensaID);
        if(recompensaEntity==null){
            throw new WebApplicationException(PRIM + recompensaID + NO, 404);
        }
       
        return new RecompensaDTO(recompensaEntity);
    }
    
     @PUT
     @Path("{recompensasId: \\d+}")
    public RecompensaDTO updateRecompensa(@PathParam("recompensasId") Long recompensaID,RecompensaDTO recompensa) throws  BusinessLogicException{
        recompensa.setId(recompensaID);
        if(recompensaLogic.findRecompensa(recompensaID)==null){
            throw new WebApplicationException(PRIM + recompensaID +NO, 404);
        }
       
        return new RecompensaDTO(recompensaLogic.updateRecompensa(recompensa.toEntity()));
    }
    
     @DELETE
     @Path("{recompensasId: \\d+}")
    public void deleteRecompensa(@PathParam("recompensasId") Long recompensaID) throws BusinessLogicException{
        if(recompensaLogic.findRecompensa(recompensaID)==null){
            throw new WebApplicationException(PRIM + recompensaID + NO, 404);
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
