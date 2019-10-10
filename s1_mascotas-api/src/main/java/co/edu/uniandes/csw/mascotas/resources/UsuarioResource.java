/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.dtos.UsuarioDTO;
import co.edu.uniandes.csw.mascotas.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.mascotas.ejb.UsuarioLogic;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.Collection;
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
 * @author Tobia Gasparoni
 */
@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource {
    
    @Inject
    private UsuarioLogic usuarioLogic;
    
    @POST
    public UsuarioDTO createUsuario(UsuarioDTO usuarioDto) throws BusinessLogicException {

        UsuarioEntity usuarioEntity = usuarioDto.toEntity();

        usuarioEntity = usuarioLogic.createUsuario(usuarioEntity);
        
        UsuarioDTO nuevoUsuario = new UsuarioDTO(usuarioEntity);

        return nuevoUsuario;
    }
    
    @GET
    @Path("{usuariosid: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("usuariosid") Long usuariosId) throws BusinessLogicException {
        UsuarioEntity entidad = usuarioLogic.findUsuario(usuariosId);
        if (entidad == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + " no existe", 404);
        }
         UsuarioDetailDTO detailDTO = new UsuarioDetailDTO(entidad);
        return detailDTO;
    }
    
    @GET
    public List<UsuarioDetailDTO> getUsuarios() throws BusinessLogicException {

        List<UsuarioDetailDTO> listaUsuarios = listEntity2DTO(usuarioLogic.findAllUsuario());

        return listaUsuarios;
    }
    
 
    @PUT
     @Path("{usuariosid: \\d+}")
    public UsuarioDTO updateUsuario(@PathParam("usuariosid") Long usuariosId, UsuarioDTO usuario) throws BusinessLogicException {
        usuario.setId(usuariosId);
        if (usuarioLogic.findUsuario(usuariosId) == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuariosId + " no existe.", 404);
        }
        UsuarioDetailDTO detailDTO = new UsuarioDetailDTO(usuarioLogic.updateUsuario(usuario.toEntity()));
        return detailDTO;
    }
    

    @DELETE
    @Path("{usuariosid: \\d+}")
    public void deleteUsuario(@PathParam("usuariosid") Long usuariosid) throws BusinessLogicException {
         if (usuarioLogic.findUsuario(usuariosid) == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuariosid + " no existe.", 404);
        }
        usuarioLogic.deleteUsuario(usuariosid);
    }

    private List<UsuarioDetailDTO> listEntity2DTO(Collection<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }
}
