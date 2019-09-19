/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.PublicidadDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author German Rozo
 */
@Path("publicidades")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PublicidadResource 
{
   private static final Logger LOGGER = Logger.getLogger(PublicidadResource.class.getName());
   
   @POST
   public PublicidadDTO createPublicidad(PublicidadDTO publicidad)
   {
      return publicidad; 
   }
   
   @GET
   public PublicidadDTO getPublicidad(PublicidadDTO publicidad)
   {
      return publicidad; 
   }
   
   @PUT
   public PublicidadDTO updatePublicidad(PublicidadDTO publicidad)
   {
      return publicidad; 
   }
   
   @DELETE
   public PublicidadDTO deletePublicidad(PublicidadDTO publicidad)
   {
      return publicidad; 
   }
}
