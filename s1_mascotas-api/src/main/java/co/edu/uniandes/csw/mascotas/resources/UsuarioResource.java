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
 * @author Tobia Gasparoni
 */
@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource {
    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());
    
    @Inject
    private UsuarioLogic usuarioLogic;
    
    private static final String RECURSO = "El recurso /usuarios/";
    
    private static final String NOEXISTE = " no existe.";
    
    @POST
    public UsuarioDTO createUsuario(UsuarioDTO usuarioDto) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioResource createUsuario: input{0}", usuarioDto);
        
        UsuarioEntity usuarioEntity = usuarioDto.toEntity();

        usuarioEntity = usuarioLogic.createUsuario(usuarioEntity);
        
        UsuarioDTO nuevoUsuario = new UsuarioDTO(usuarioEntity);

        LOGGER.log(Level.INFO, "UsuarioResource createUsuario: output{0}", nuevoUsuario);
        
        return nuevoUsuario;
    }
    
    @GET
    @Path("{usuariosid: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("usuariosid") Long usuariosId) throws BusinessLogicException {
        UsuarioEntity entidad = usuarioLogic.findUsuario(usuariosId);
        if (entidad == null) {
            throw new WebApplicationException(RECURSO + usuariosId + " no existe", 404);
        }
        return new UsuarioDetailDTO(entidad);
    }
    
    @GET
    public List<UsuarioDetailDTO> getUsuarios() throws BusinessLogicException {

        return listEntity2DTO(usuarioLogic.findAllUsuario());
    }
    
 
    @PUT
     @Path("{usuariosid: \\d+}")
    public UsuarioDTO updateUsuario(@PathParam("usuariosid") Long usuariosId, UsuarioDTO usuario) throws BusinessLogicException {
        usuario.setId(usuariosId);
        if (usuarioLogic.findUsuario(usuariosId) == null) {
            throw new WebApplicationException(RECURSO + usuariosId + NOEXISTE, 404);
        }
        return new UsuarioDetailDTO(usuarioLogic.updateUsuario(usuario.toEntity()));
    }
    

    @DELETE
    @Path("{usuariosid: \\d+}")
    public void deleteUsuario(@PathParam("usuariosid") Long usuariosid) throws BusinessLogicException {
         if (usuarioLogic.findUsuario(usuariosid) == null) {
            throw new WebApplicationException(RECURSO + usuariosid + NOEXISTE, 404);
        }
        usuarioLogic.deleteUsuario(usuariosid);
    }
    
    /**
     * Conexión con el servicio de libros para una editorial.
     * {@link EditorialBooksResource}
     *
     * Este método conecta la ruta de /editorials con las rutas de /books que
     * dependen de la editorial, es una redirección al servicio que maneja el
     * segmento de la URL que se encarga de los libros de una editorial.
     *
     * @param usuariosId El ID de la editorial con respecto a la cual se
     * accede al servicio.
     * @return El servicio de libros para esta editorial en paricular.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la editorial.
     */
    @Path("{usuariosId: \\d+}/mascotasencontradas")
    public Class<UsuarioMascotasEncontradasResource> getUsuarioMascotasEncontradasResource(@PathParam("usuariosId") Long usuariosId) {
        if (usuarioLogic.findUsuario(usuariosId) == null) {
            throw new WebApplicationException(RECURSO + usuariosId + NOEXISTE, 404);
        }
        return UsuarioMascotasEncontradasResource.class;
    }

    private List<UsuarioDetailDTO> listEntity2DTO(Collection<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }
}
