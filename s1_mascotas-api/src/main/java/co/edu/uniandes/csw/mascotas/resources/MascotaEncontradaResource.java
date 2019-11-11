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

import co.edu.uniandes.csw.mascotas.dtos.MascotaEncontradaDTO;
import co.edu.uniandes.csw.mascotas.dtos.MascotaEncontradaDetailDTO;
import co.edu.uniandes.csw.mascotas.ejb.MascotaEncontradaLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaEncontradaUsuarioLogic;
import co.edu.uniandes.csw.mascotas.ejb.UsuarioLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 * Clase que implementa el recurso "mascotasencontradas".
 *
 * @author ja.avelino
 * @version 1.0
 */
@Path("mascotasencontradas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class MascotaEncontradaResource {
    private static final String PRIM= "El recurso /mascotasencontradas/";
    private static final String NO=" no existe.";
    private static final Logger LOGGER = Logger.getLogger(MascotaEncontradaResource.class.getName());

    @Inject
    private MascotaEncontradaLogic meLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private UsuarioLogic usuarioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private MascotaEncontradaUsuarioLogic meUsuarioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una nueva mascota con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param mascota {@link MascotaEncontradaDTO} - La mascota que se desea guardar.
     * @return JSON {@link MascotaEncontradaDTO} - Mascota guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la mascota.
     */
    @POST
    public MascotaEncontradaDTO createMascotaEncontrada(MascotaEncontradaDTO mascota) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "MascotaEncontradaResource createMascotaEncontrada: input: {0}", mascota);
        MascotaEncontradaDTO nuevaMascotaDTO = new MascotaEncontradaDTO(meLogic.createMascotaEncontrada(mascota.toEntity()));
        LOGGER.log(Level.INFO, "MascotaEncontradaResource createMascotaEncontrada: output: {0}", nuevaMascotaDTO);
        return nuevaMascotaDTO;
    }

    /**
     * Busca y devuelve todas las mascotas que existen en la aplicacion.
     *
     * @return JSONArray {@link MascotaEncontradaDetailDTO} - Los libros encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<MascotaEncontradaDetailDTO> getMascotas() {
        LOGGER.info("MascotaEncontradaResource getMascotas: input: void");
        List<MascotaEncontradaDetailDTO> listaMascotas = listEntity2DetailDTO(meLogic.findAllMascotaEncontrada());
        LOGGER.log(Level.INFO, "MascotaEncontradaResource getMascotas: output: {0}", listaMascotas);
        return listaMascotas;
    }

    /**
     * Busca la mascota con el id asociado recibido en la URL y lo devuelve.
     *
     * @param mascotaId Identificador de la mascota que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link BookDetailDTO} - La mascota buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la mascota.
     */
    @GET
    @Path("{mascotaId: \\d+}")
    public MascotaEncontradaDetailDTO getMascota(@PathParam("mascotaId") Long mascotaId) {
        LOGGER.log(Level.INFO, "MascotaEncontradaResource getMascota: input: {0}", mascotaId);
        MascotaEncontradaEntity mascotaEncontradaEntity = meLogic.findMascotaEncontrada(mascotaId);
        if (mascotaEncontradaEntity == null) {
            throw new WebApplicationException(PRIM + mascotaId + NO, 404);
        }
        MascotaEncontradaDetailDTO mDetailDTO = new MascotaEncontradaDetailDTO(mascotaEncontradaEntity);
        LOGGER.log(Level.INFO, "MascotaEncontradaResource getMascota: output: {0}", mDetailDTO);
        return mDetailDTO;
    }

    /**
     * Actualiza la mascota con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param mascotaId Identificador de la mascota que se desea actualizar. Este debe
     * ser una cadena de dígitos.
     * @param mascota {@link MascotaEncontradaDTO} La mascota que se desea guardar.
     * @return JSON {@link MascotaEncontradaDetailDTO} - La mascota guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la mascota a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar la mascota.
     */
    @PUT
    @Path("{mascotaId: \\d+}")
    public MascotaEncontradaDetailDTO updateMascota(@PathParam("mascotaId") Long mascotaId, MascotaEncontradaDetailDTO mascota) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "MascotaEncontradaResource updateMascota: input: id: {0} , mascota: {1}", new Object[]{mascotaId, mascota});
        mascota.setId(mascotaId);
        if (meLogic.findMascotaEncontrada(mascotaId) == null) {
            throw new WebApplicationException(PRIM + mascotaId + NO, 404);
        }
        MascotaEncontradaDetailDTO detailDTO = new MascotaEncontradaDetailDTO(meLogic.updateMascotaEncontrada(mascota.toEntity()));
        LOGGER.log(Level.INFO, "MascotaEncontradaResource updateMascota: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Borra la mascota con el id asociado recibido en la URL.
     *
     * @param mId Identificador del libro que se desea borrar. Este debe ser
     * una cadena de dígitos.
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     * cuando la mascota tiene usuario asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la mascota.
     */
    @DELETE
    @Path("{mId: \\d+}")
    public void deleteMascota(@PathParam("mId") Long mId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "MascotaEncontradaResource deleteMascota: input: {0}", mId);
        MascotaEncontradaEntity entity = meLogic.findMascotaEncontrada(mId);
        if (entity == null) {
            throw new WebApplicationException(PRIM + mId + NO, 404);
        }
        meUsuarioLogic.removeUsuario(mId);
        meLogic.deleteMascotaEncontrada(mId);
        LOGGER.info("MascotaEncontradaResource deleteMascota: output: void");
    }

    /**
     * Conexión con el servicio de multimedia para una mascota. {@link MultimediaResource}
     *
     * Este método conecta la ruta de /mascotasencontradas con las rutas de /multimedia que
     * dependen de la mascota, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de la multimedia.
     *
     * @param mId El ID de la mascota con respecto al cual se accede al
     * servicio.
     * @return El servicio de multimedia para esa mascota en paricular.\
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la mascota.
     */
    @Path("{encontradaId: \\d+}/multimedia")
    public Class<MultimediaResource> getMultimediaResource(@PathParam("encontradaId") Long mId) {
        if (meLogic.findMascotaEncontrada(mId) == null) {
            throw new WebApplicationException(PRIM + mId + "/multimedia"+NO, 404);
        }
        return MultimediaResource.class;
    }

    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos MascotaEncontradaEntity a una lista de
     * objetos MascotaEncontradaDetailDTO (json)
     *
     * @param entityList corresponde a la lista de mascotas de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de mascotas en forma DTO (json)
     */
    private List<MascotaEncontradaDetailDTO> listEntity2DetailDTO(List<MascotaEncontradaEntity> entityList) {
        List<MascotaEncontradaDetailDTO> list = new ArrayList<>();
        for (MascotaEncontradaEntity entity : entityList) {
            list.add(new MascotaEncontradaDetailDTO(entity));
        }
        return list;
    }
}
