/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.PublicidadDTO;
import co.edu.uniandes.csw.mascotas.dtos.PublicidadDetailDTO;
import co.edu.uniandes.csw.mascotas.ejb.MultimediaLogic;
import co.edu.uniandes.csw.mascotas.ejb.PublicidadLogic;
import co.edu.uniandes.csw.mascotas.entities.MultimediaEntity;
import co.edu.uniandes.csw.mascotas.entities.PublicidadEntity;
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
 * @author German Rozo
 */
@Path("publicidades")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PublicidadResource {

    private static final String PRIM = "El recurso /publicidades/";

    private static final String NO = " no existe.";

    @Inject
    private PublicidadLogic logic;

    @Inject
    private MultimediaLogic ml;

    @POST
    public PublicidadDetailDTO createPublicidad(PublicidadDetailDTO publicidad) throws BusinessLogicException {
        PublicidadEntity entity = publicidad.toEntity();
        List<MultimediaEntity> multimedia = entity.getMultimedia();
        entity.setMultimedia(new ArrayList<>());
        entity = logic.createPublicidad(entity);

        for (MultimediaEntity multimediaEntity : multimedia) {
            ml.createMultimedia(null, null, null, entity.getId(), multimediaEntity);
        }

        entity.setMultimedia(multimedia);
        return new PublicidadDetailDTO(entity);
    }

    @GET
    @Path("{publicidadId: \\d+}")
    public PublicidadDetailDTO getPublicidad(@PathParam("publicidadId") Long publicidadId) throws BusinessLogicException {
        PublicidadEntity authorEntity = logic.findPublicidad(publicidadId);
        if (authorEntity == null) {
            throw new WebApplicationException(PRIM + publicidadId + NO, 404);
        }
        return new PublicidadDetailDTO(authorEntity);
    }

    @GET
    public PublicidadDetailDTO getPublicidad() throws BusinessLogicException 
    {
        PublicidadEntity authorEntity = logic.getPublicidad();
        return new PublicidadDetailDTO(authorEntity);
    }
    
    @GET
    @Path("all")
    public List<PublicidadDTO> getAll() throws BusinessLogicException {
        List<PublicidadEntity> ens = logic.findAllPublicidad();
        
        if(ens==null)
            throw new WebApplicationException(PRIM + NO, 404);
        else
        {
            List<PublicidadDTO> dtos = new ArrayList<>();
            for (PublicidadEntity en : ens) {
                dtos.add(new PublicidadDTO(en));
            }
            return dtos;
        }
    }

    @PUT
    @Path("{publicidadId: \\d+}")
    public PublicidadDetailDTO updatePublicidad(@PathParam("publicidadId") Long publicidadId, PublicidadDetailDTO publicidad) throws BusinessLogicException {
        
        publicidad.setId(publicidadId);
        
        if (logic.findPublicidad(publicidadId) == null) {
            throw new WebApplicationException(PRIM + publicidadId + NO, 404);
        }
        
        return new PublicidadDetailDTO(logic.updatePublicidad(publicidad.toEntity()));
    }

    @DELETE
    @Path("{publicidadId: \\d+}")
    public void deletePublicidad(@PathParam("publicidadId") Long publicidadId) throws BusinessLogicException {
        
        PublicidadEntity publicidad = logic.findPublicidad(publicidadId);
       
        if (publicidad == null) 
        {
            throw new WebApplicationException(PRIM + publicidadId + NO, 404);
        }
        
        for (MultimediaEntity multimediaEntity : publicidad.getMultimedia()) 
        {
            ml.deleteMultimedia(multimediaEntity.getId());
        }
        
        logic.deletePublicidad(publicidadId);
    }
}
