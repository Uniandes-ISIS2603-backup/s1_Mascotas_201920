/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.MascotaEncontradaDTO;
import co.edu.uniandes.csw.mascotas.dtos.MascotaEncontradaDetailDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaEncontradaLogic;
import co.edu.uniandes.csw.mascotas.ejb.UsuarioMascotasEncontradasLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Tobia Gasparoni
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
//@RequestScoped
public class UsuarioMascotasEncontradasResource {
    
    @Inject
    private UsuarioMascotasEncontradasLogic usuarioMascotasLogic;
    
    @Inject
    private MascotaEncontradaLogic mascotaLogic;
    
    private static final String NOEXISTE = " no existe.";
    
    @POST
    @Path("{mascotasEncontradasId: \\d+}")
    public MascotaEncontradaDTO addMascota(@PathParam("usuariosId") Long usuariosId, @PathParam("mascotasEncontradasId") Long mascotasEncontradasId) {
        if (mascotaLogic.findMascotaEncontrada(mascotasEncontradasId) == null) {
            throw new WebApplicationException("El recurso /mascotasencontradas/" + mascotasEncontradasId + NOEXISTE, 404);
        }
        return new MascotaEncontradaDTO(usuarioMascotasLogic.addMascota(mascotasEncontradasId, usuariosId));
    }
    
    @GET
    public List<MascotaEncontradaDetailDTO> getMascotas(@PathParam("usuariosId") Long usuariosId) {
        return mascotasListEntity2DTO(usuarioMascotasLogic.getMascotas(usuariosId));
    }
    
    @GET
    @Path("{mascotasEncontradasId: \\d+}")
    public MascotaEncontradaDetailDTO getMascota(@PathParam("usuariosId") Long usuariosId, @PathParam("mascotasEncontradasId") Long mascotasEncontradasId) throws BusinessLogicException {
        if (mascotaLogic.findMascotaEncontrada(mascotasEncontradasId) == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + "/mascotasencontradas/" + mascotasEncontradasId + NOEXISTE, 404);
        }
        return new MascotaEncontradaDetailDTO(usuarioMascotasLogic.getMascota(usuariosId, mascotasEncontradasId));
    }
    
    @PUT
    public List<MascotaEncontradaDetailDTO> replaceMascotas(@PathParam("usuariosId") Long usuariosId, List<MascotaEncontradaDetailDTO> mascotas) {
        for (MascotaEncontradaDetailDTO mascota : mascotas) {
            if (mascotaLogic.findMascotaEncontrada(mascota.getId()) == null) {
                throw new WebApplicationException("El recurso /mascotasencontradas/" + mascota.getId() + NOEXISTE, 404);
            }
        }
        return mascotasListEntity2DTO(usuarioMascotasLogic.replaceMascotas(usuariosId, mascotasListDTO2Entity(mascotas)));
    }
    
    private List<MascotaEncontradaEntity> mascotasListDTO2Entity(List<MascotaEncontradaDetailDTO> dtos) {
        List<MascotaEncontradaEntity> list = new ArrayList<>();
        for (MascotaEncontradaDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
    private List<MascotaEncontradaDetailDTO> mascotasListEntity2DTO(List<MascotaEncontradaEntity> entityList)
    {
        List<MascotaEncontradaDetailDTO> list = new ArrayList();
        for (MascotaEncontradaEntity entity : entityList) {
            list.add(new MascotaEncontradaDetailDTO(entity));
        }
        return list;
    }
}
