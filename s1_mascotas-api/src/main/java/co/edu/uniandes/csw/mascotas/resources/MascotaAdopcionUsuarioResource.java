/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.mascotas.resources;

import co.edu.uniandes.csw.mascotas.mappers.WebApplicationExceptionMapper;
import co.edu.uniandes.csw.mascotas.dtos.MascotaAdopcionDetailDTO;
import co.edu.uniandes.csw.mascotas.dtos.UsuarioDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaAdopcionLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaAdopcionUsuarioLogic;
import co.edu.uniandes.csw.mascotas.ejb.UsuarioLogic;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso "usuario/{id}/mascotaAdopcions".
 *
 * @author Tomas Langebaek
 * @version 1.0
 */
@Path("mascotasadopcion/{mascotaAdopcionId: \\d+}/usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MascotaAdopcionUsuarioResource {

    private static final Logger LOGGER = Logger.getLogger(MascotaAdopcionUsuarioResource.class.getName());

    @Inject
    private MascotaAdopcionLogic mascotaAdopcionLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private MascotaAdopcionUsuarioLogic mascotaAdopcionUsuarioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private UsuarioLogic usuarioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Remplaza la instancia de Usuario asociada a un MascotaAdopcion.
     *
     * @param mascotaAdopcionsId Identificador del libro que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param usuario La usuario que se será del libro.
     * @return JSON {@link MascotaAdopcionDetailDTO} - El arreglo de libros guardado en la
     * usuario.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la usuario o el
     * libro.
     */
    @PUT
    public MascotaAdopcionDetailDTO replaceUsuario(@PathParam("mascotaAdopcionId") Long mascotaAdopcionId, UsuarioDTO usuario) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "MascotaAdopcionUsuarioResource replaceUsuario: input: mascotaAdopcionId{0} , Usuario:{1}", new Object[]{mascotaAdopcionId, usuario});
        if (mascotaAdopcionLogic.getMascotaAdopcion(mascotaAdopcionId) == null) {
            throw new WebApplicationException("El recurso /mascotasencontradas/" + mascotaAdopcionId + " no existe.", 404);
        }
        if (usuarioLogic.findUsuario(usuario.getId()) == null) {
            throw new WebApplicationException("El recurso /usuarios/" + usuario.getId() + " no existe.", 404);
        }
        MascotaAdopcionDetailDTO mascotaAdopcionDetailDTO = new MascotaAdopcionDetailDTO(mascotaAdopcionUsuarioLogic.replaceUsuario(mascotaAdopcionId, usuario.getId()));
        LOGGER.log(Level.INFO, "MascotaAdopcionUsuarioResource replaceUsuario: output: {0}", mascotaAdopcionDetailDTO);
        return mascotaAdopcionDetailDTO;
    }
}
