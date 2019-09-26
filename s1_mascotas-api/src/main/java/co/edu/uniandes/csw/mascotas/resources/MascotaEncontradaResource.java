/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaEncontradaDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaEncontradaLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author ja.avelino
 */
@Path("mascotasencontradas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class MascotaEncontradaResource {
    
    private static final Logger LOGGER = Logger.getLogger(MascotaEncontradaResource.class.getName());
    
    @Inject
    private MascotaEncontradaLogic mel;
    
    @POST
    public MascotaEncontradaDTO createMascotaEncontrada(MascotaEncontradaDTO mascota) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "MascotaEncontradaResource createMascotaEncontrada: input(0)", mascota);
        
        MascotaEncontradaEntity mascotaEntity = mascota.toEntity();
        
        MascotaEncontradaEntity nuevaMascotaEntity = mel.createMascotaEncontrada(mascotaEntity);
        
        MascotaEncontradaDTO nuevaMascota = new MascotaEncontradaDTO(nuevaMascotaEntity);
        
        LOGGER.log(Level.INFO, "MascotaEncontradaResource createMascotaEncontrada: output(0)", nuevaMascota);
        return nuevaMascota;
    }
    
}
