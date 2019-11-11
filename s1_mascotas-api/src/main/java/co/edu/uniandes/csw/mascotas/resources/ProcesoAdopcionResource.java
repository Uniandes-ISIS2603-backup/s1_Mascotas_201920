/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.ProcesoAdopcionDTO;
import co.edu.uniandes.csw.mascotas.ejb.ProcesoAdopcionLogic;
import co.edu.uniandes.csw.mascotas.entities.ProcesoAdopcionEntity;
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

@Path("procesos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ProcesoAdopcionResource {
    private static final String PRIM= "El recurso /procesos/";
    private static final String NO=" no existe.";
    private static final Logger LOGGER = Logger.getLogger(ProcesoAdopcionResource.class.getName());
    
    @Inject
    private ProcesoAdopcionLogic procesoLogic;
    
    @POST
    public ProcesoAdopcionDTO createProcesoAdopcion(ProcesoAdopcionDTO procesoDTO) throws BusinessLogicException{
        ProcesoAdopcionEntity procesoEntity=procesoDTO.toEntity();
        ProcesoAdopcionEntity newProcesoEntity=procesoLogic.createProcesoAdopcion(procesoEntity);
        ProcesoAdopcionDTO newProcesoDTO=new ProcesoAdopcionDTO(newProcesoEntity);
        return newProcesoDTO;
    }
    
    @GET
    public List<ProcesoAdopcionDTO> getProcesos(){
        List<ProcesoAdopcionDTO> listaProcesos=listEntity2DTO(procesoLogic.findAllProcesosAdopcion());
        return listaProcesos;  
    }
    
     @GET
     @Path("{procesosId: \\d+}")
    public ProcesoAdopcionDTO getProcesoAdopcion(@PathParam("procesosId") Long procesoID)throws WebApplicationException{
        ProcesoAdopcionEntity procesoEntity=procesoLogic.findProcesoAdopcion(procesoID);
        if(procesoEntity==null){
            throw new WebApplicationException(PRIM + procesoID + NO, 404);
        }
        ProcesoAdopcionDTO procesoDTO=new ProcesoAdopcionDTO(procesoEntity);
        return procesoDTO;
    }
    
     @PUT
     @Path("{procesosId: \\d+}")
    public ProcesoAdopcionDTO updateProcesoAdopcion(@PathParam("procesosId") Long procesoID,ProcesoAdopcionDTO proceso) throws WebApplicationException, BusinessLogicException{
        proceso.setId(procesoID);
        if(procesoLogic.findProcesoAdopcion(procesoID)==null){
            throw new WebApplicationException(PRIM + procesoID + NO, 404);
        }
        ProcesoAdopcionDTO procesoDTO=new ProcesoAdopcionDTO(procesoLogic.updateProcesoAdopcion(proceso.toEntity()));
        return procesoDTO;
    }
    
     @DELETE
     @Path("{procesosId: \\d+}")
    public void deleteProcesoAdopcion(@PathParam("procesosId") Long procesoID) throws BusinessLogicException{
        if(procesoLogic.findProcesoAdopcion(procesoID)==null){
            throw new WebApplicationException(PRIM+ procesoID + NO, 404);
        }
        procesoLogic.deleteProcesoAdopcion(procesoID);
    }

    private List<ProcesoAdopcionDTO> listEntity2DTO(List<ProcesoAdopcionEntity> entityList) {
        List<ProcesoAdopcionDTO> list=new ArrayList<>();
        for(ProcesoAdopcionEntity entity : entityList){
            list.add(new ProcesoAdopcionDTO(entity));
        }
        return list;
    }
    
}
