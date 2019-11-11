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

import co.edu.uniandes.csw.mascotas.dtos.MultimediaDTO;
import co.edu.uniandes.csw.mascotas.ejb.MultimediaLogic;
import co.edu.uniandes.csw.mascotas.entities.MultimediaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.mascotas.mappers.WebApplicationExceptionMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * Clase que implementa el recurso "multimedia".
 *
 * @author ja.avelino
 * @version 1.0
 */
@Produces("application/json")
@Consumes("application/json")
public class MultimediaResource {

    private static final Logger LOGGER = Logger.getLogger(MultimediaResource.class.getName());
    private static final String PRIM= "El recurso /multimedia/";
    private static final String NO=" no existe.";

    @Inject
    private MultimediaLogic multimediaLogic;

    /**
     * Crea una nueva multimedia con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param adopcionId El ID de la mascotaAdopcion del cual se le agrega la multimedia
     * @param encontradaId El ID de la mascotaEncontrada del cual se le agrega la multimedia
     * @param perdidaId El ID de la mascotaPerdida del cual se le agrega la multimedia
     * @param publicidadId El ID de la publicidad del cual se le agrega la multimedia
     * @param multimedia {@link MultimediaDTO} - La multimedia que se desea guardar.
     * @return JSON {@link MultimediaDTO} - La multimedia guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la multimedia.
     */
    @POST
    public MultimediaDTO createMultimedia(@PathParam("adopcionId") Long adopcionId, 
            @PathParam("encontradaId") Long encontradaId, 
            @PathParam("perdidaId") Long perdidaId, 
            @PathParam("publicidadId") Long publicidadId,
            MultimediaDTO multimedia) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "MultimediaResource createMultimedia: input: {0}", multimedia);
        MultimediaDTO nuevoMultimediaDTO = new MultimediaDTO(multimediaLogic.createMultimedia(adopcionId, encontradaId, perdidaId, publicidadId, multimedia.toEntity()));
        LOGGER.log(Level.INFO, "ReviewResource createReview: output: {0}", nuevoMultimediaDTO);
        return nuevoMultimediaDTO;
    }

    /**
     * Busca y devuelve todas las multimedias que existen en un objeto.
     *
     * @param adopcionId El ID de adopcion del cual se buscan las multimedias
     * @param encontradaId El ID de encontrada del cual se buscan las multimedias
     * @param perdidaId El ID de perdida del cual se buscan las multimedias
     * @param publicidadId El ID de publicidad del cual se buscan las multimedias
     * 
     * @return JSONArray {@link MultimediaDTO} - Las multimedias encontradas en el
     * objeto. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<MultimediaDTO> getMultimedias(@PathParam("adopcionId") Long adopcionId, 
            @PathParam("encontradaId") Long encontradaId, 
            @PathParam("perdidaId") Long perdidaId, 
            @PathParam("publicidadId") Long publicidadId) {
        LOGGER.log(Level.INFO, "MultimediaResource getMultimedias: input: {0} {1} {2} {3}", new Object[]{adopcionId, encontradaId, perdidaId, publicidadId});
        List<MultimediaDTO> listaDTOs = listEntity2DTO(multimediaLogic.getMultimedias(adopcionId, encontradaId, perdidaId, publicidadId));
        LOGGER.log(Level.INFO, "MultimediaResource getMultimedias: output: {0} {1} {2} {3}", new Object[]{adopcionId, encontradaId, perdidaId, publicidadId});
        return listaDTOs;
    }

    /**
     * Busca y devuelve la multimedia con el ID recibido en la URL
     *
     * @param multimediaId El ID de la multimeida que se busca
     * @return {@link ReviewDTO} - La multimedia buscada..
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la multimedia.
     */
    @GET
    @Path("{mutlimediaId: \\d+}")
    public MultimediaDTO getMultimedia(@PathParam("multimediaId") Long multimediaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "MultimediaResource getMultimedia: input: {0}", multimediaId);
        MultimediaEntity entity = multimediaLogic.getMultimedia(multimediaId);
        if (entity == null) {
            throw new WebApplicationException(PRIM + multimediaId + NO, 404);
        }
        MultimediaDTO multimediaDTO = new MultimediaDTO(entity);
        LOGGER.log(Level.INFO, "MultimediaResource getMultimedia: output: {0}", multimediaDTO);
        return multimediaDTO;
    }

    /**
     * Actualiza una multimedia con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     *
     * @param multimediaId El ID de la multimedia que se va a actualizar
     * @param review {@link ReviewDTO} - La multimedia que se desea guardar.
     * @return JSON {@link ReviewDTO} - La multimedia actualizada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la multimedia.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la multimedia.
     */
    @PUT
    @Path("{multimediaId: \\d+}")
    public MultimediaDTO updateMultimedia(@PathParam("multimediaId") Long multimediaId, MultimediaDTO multimedia) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "MultimediaResource updateMultimedia: input: multimediaId: {0} , multimedia:{1}", new Object[]{multimediaId, multimedia});
        if (multimediaId.equals(multimedia.getId())) {
            throw new BusinessLogicException("Los ids de la multimedia no coinciden.");
        }
        MultimediaEntity entity = multimediaLogic.getMultimedia(multimediaId);
        if (entity == null) {
            throw new WebApplicationException(PRIM+ multimediaId +NO, 404);

        }
        MultimediaDTO multimediaDTO = new MultimediaDTO(multimediaLogic.updateMultimedia(multimedia.toEntity()));
        LOGGER.log(Level.INFO, "MultimediaResource updateMultimedia: output:{0}", multimediaDTO);
        return multimediaDTO;

    }

    /**
     * Borra la multimedia con el id asociado recibido en la URL.
     *
     * @param multimediaId El ID de la multimedia que se va a eliminar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la multimedia.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la multimedia.
     */
    @DELETE
    @Path("{multimediaId: \\d+}")
    public void deleteMultimedia(@PathParam("multimediaId") Long multimediaId) throws BusinessLogicException {
        MultimediaEntity entity = multimediaLogic.getMultimedia(multimediaId);
        if (entity == null) {
            throw new WebApplicationException(PRIM+ multimediaId + NO, 404);
        }
        multimediaLogic.deleteMultimedia(multimediaId);
    }

    /**
     * Lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos MultimediaEntity a una lista de
     * objetos MultimediaDTO (json)
     *
     * @param entityList corresponde a la lista de multimedia de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de multimedia en forma DTO (json)
     */
    private List<MultimediaDTO> listEntity2DTO(List<MultimediaEntity> entityList) {
        List<MultimediaDTO> list = new ArrayList<MultimediaDTO>();
        for (MultimediaEntity entity : entityList) {
            list.add(new MultimediaDTO(entity));
        }
        return list;
    }
}
