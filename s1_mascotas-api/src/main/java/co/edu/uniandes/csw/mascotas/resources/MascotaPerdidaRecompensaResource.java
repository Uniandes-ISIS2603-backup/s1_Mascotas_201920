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
import co.edu.uniandes.csw.mascotas.dtos.MascotaPerdidaDTO;
import co.edu.uniandes.csw.mascotas.dtos.RecompensaDTO;
import co.edu.uniandes.csw.mascotas.ejb.RecompensaLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaPerdidaRecompensaLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaPerdidaLogic;
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
 * Clase que implementa el recurso "recompensas/{id}/mascotaPerdida".
 *
 * @author Lily
 * @version 1.0
 */
@Path("recompensas/{recompensaId: \\d+}/mascotaperdidas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MascotaPerdidaRecompensaResource {

    private static final Logger LOGGER = Logger.getLogger(MascotaPerdidaRecompensaResource.class.getName());

    @Inject
    private RecompensaLogic recompensaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private MascotaPerdidaRecompensaLogic recompensaMascotaPerdidaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private MascotaPerdidaLogic mascotaPerdidaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Remplaza la instancia de MascotaPerdida asociada a un Recompensa.
     *
     * @param recompensasId Identificador del libro que se esta actualizando. Este
     * debe ser una cadena de dígitos.
     * @param mascotaPerdida La mascotaPerdida que se será del libro.
     * @return JSON {@link RecompensaDetailDTO} - El arreglo de libros guardado en la
     * mascotaPerdida.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la mascotaPerdida o el
     * libro.
     */
    @PUT
    public RecompensaDTO replaceMascotaPerdida(@PathParam("recompensaId") Long recompensaId, MascotaPerdidaDTO mascotaPerdida) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "RecompensaMascotaPerdidaResource replaceMascotaPerdida: input: recompensaId{0} , MascotaPerdida:{1}", new Object[]{recompensaId, mascotaPerdida});
        if (recompensaLogic.findRecompensa(recompensaId) == null) {
            throw new WebApplicationException("El recurso /mascotasencontradas/" + recompensaId + " no existe.", 404);
        }
        if (mascotaPerdidaLogic.getMascotaPerdida(mascotaPerdida.getId()) == null) {
            throw new WebApplicationException("El recurso /mascotaPerdidas/" + mascotaPerdida.getId() + " no existe.", 404);
        }
        RecompensaDTO recompensaDetailDTO = new RecompensaDTO(recompensaMascotaPerdidaLogic.replaceMascotaPerdida(recompensaId, mascotaPerdida.getId()));
        LOGGER.log(Level.INFO, "RecompensaMascotaPerdidaResource replaceMascotaPerdida: output: {0}", recompensaDetailDTO);
        return recompensaDetailDTO;
    }
}
