/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.PublicidadDTO;
import co.edu.uniandes.csw.mascotas.dtos.PublicidadDetailDTO;
import co.edu.uniandes.csw.mascotas.ejb.PublicidadLogic;
import co.edu.uniandes.csw.mascotas.entities.PublicidadEntity;
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
 * Recurso de publicidad
 * @author German Rozo
 */
@Path("publicidades")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PublicidadResource 
{
   private static final Logger LOGGER = Logger.getLogger(PublicidadResource.class.getName());
   
   @Inject 
   private PublicidadLogic logic;
   
   @POST
   public PublicidadDTO createPublicidad(PublicidadDTO publicidad) throws BusinessLogicException
   {
       
        PublicidadDTO authorDTO = new PublicidadDTO(logic.createPublicidad(publicidad.toEntity()));
        return authorDTO;
   }
   
    @GET
    @Path("{publicidadId: \\d+}")
    public PublicidadDetailDTO getPublicidad(@PathParam("publicidadId") Long publicidadId) throws BusinessLogicException
    {
        PublicidadEntity authorEntity = logic.findPublicidad(publicidadId);
        if (authorEntity == null) {
            throw new WebApplicationException("El recurso /publicidades/" + publicidadId + " no existe.", 404);
        }
        PublicidadDetailDTO detailDTO = new PublicidadDetailDTO(authorEntity);
        return detailDTO;
    }
    
    
    /**
     * Retorna una lista de DTOs con todas la publicidades existentes 
     * @return 
     */
    @GET
    public List<PublicidadDetailDTO> getPublicidades()
    {
        List<PublicidadDetailDTO> DTOs= new ArrayList();
        
        List<PublicidadEntity> publicidadEntity = logic.findAll();
        for (PublicidadEntity pe : publicidadEntity) 
        {
            DTOs.add(new PublicidadDetailDTO(pe));
        }
        
        return DTOs;
    }
   
   @PUT
   @Path("{publicidadId: \\d+}")
   public PublicidadDTO updatePublicidad(@PathParam("publicidadId") Long publicidadId, PublicidadDTO publicidad) throws BusinessLogicException
   {
       publicidad.setId(publicidadId);
        if (logic.findPublicidad(publicidadId) == null) {
            throw new WebApplicationException("El recurso /publicidades/" + publicidadId + " no existe.", 404);
        }
        PublicidadDTO detailDTO = new PublicidadDTO(logic.updatePublicidad(publicidad.toEntity()));
        return detailDTO;
   }
   
   @DELETE
   @Path("{publicidadId: \\d+}")
   public void deletePublicidad(@PathParam("publicidadId") Long publicidadId) throws BusinessLogicException
   {
        if (logic.findPublicidad(publicidadId) == null)
            throw new WebApplicationException("El recurso /publicidades/" + publicidadId + " no existe.", 404);
      
        logic.deletePublicidad(publicidadId);
   }
}
