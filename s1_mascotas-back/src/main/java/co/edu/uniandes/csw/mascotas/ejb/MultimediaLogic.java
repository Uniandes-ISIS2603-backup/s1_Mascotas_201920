/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;
import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaPerdidaEntity;
import co.edu.uniandes.csw.mascotas.entities.MultimediaEntity;
import co.edu.uniandes.csw.mascotas.entities.PublicidadEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaAdopcionPersistance;
import co.edu.uniandes.csw.mascotas.persistence.MascotaEncontradaPersistence;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPerdidaPersistence;
import co.edu.uniandes.csw.mascotas.persistence.MultimediaPersistence;
import co.edu.uniandes.csw.mascotas.persistence.PublicidadPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Tomas Langebaek
 */
@Stateless
public class MultimediaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(MultimediaLogic.class.getName());
    
    @Inject
    private MultimediaPersistence persistence;
    
    @Inject
    private MascotaAdopcionPersistance adopcionPersistence;
    @Inject
    private MascotaEncontradaPersistence encontradaPersistence;
    @Inject
    private MascotaPerdidaPersistence perdidaPersistence;
    @Inject
    private PublicidadPersistence publicidadPersistence;
    /**
     * Método para revisar la lógica de los atributos de la entidad MultimediaEntity
     * @param multimedia es la entidad a checkear
     * @throws BusinessLogicException si se incumple una regla de negocio
     */
    
     public void check(MultimediaEntity multimedia) throws BusinessLogicException {

        if (multimedia.getNombre() == null) {
            throw new BusinessLogicException("El el nombre de multimedia está vacío");
        }
        if (multimedia.getTipo() == null) {
            throw new BusinessLogicException("El tipo esta vacio");
        }
        if (multimedia.getUrl() == null) {
            throw new BusinessLogicException("La url no puede ser nula");
        }
        if (multimedia.getNombre().equals("")) {
            throw new BusinessLogicException("La multimedia no tiene nombre valido.");
        }
        if (multimedia.getTipo().equals("")) {
            throw new BusinessLogicException("La multimedia no tiene tipo valido.");
        }
        if (multimedia.getUrl().equals("")) {
            throw new BusinessLogicException("La url esta vacia");
        }

    }
     /**
      * Verifica las reglas de negocio al crear una multimedia
      * @param adopcionId es la mascota adopcion de la multimedia
      * @param encontradaId es la mascota encontrada de la multimedia
      * @param perdidaId es la mascota perdida de la multimedia
      * @param publicidadId es la mascota perdida de la multimedia
      * @param multimedia es la multimedia a revisar
      * @return La entidad revisada
      * @throws BusinessLogicException  si se incumplen reglas de negocio
      */
    public MultimediaEntity createMultimedia(Long adopcionId, Long encontradaId, Long perdidaId, Long publicidadId, MultimediaEntity multimedia) throws BusinessLogicException {

        check(multimedia);

        LOGGER.log(Level.INFO, "Inicia proceso de crear multimedia");
        MascotaAdopcionEntity adopcion = null;
        MascotaEncontradaEntity encontrada = null;
        MascotaPerdidaEntity perdida = null;
        PublicidadEntity publicidad = null;
        if(adopcionId != null)
            adopcion = adopcionPersistence.find(adopcionId);
        else if(encontradaId != null)
            encontrada = encontradaPersistence.find(encontradaId);
        else if(perdidaId != null)
            perdida = perdidaPersistence.find(perdidaId);
        else 
            publicidad = publicidadPersistence.find(publicidadId);
        multimedia.setMascota(adopcion);
        multimedia.setMascotaEncontrada(encontrada);
        multimedia.setMascotaPerdida(perdida);
        multimedia.setPublicidad(publicidad);
        LOGGER.log(Level.INFO, "Termina proceso de creación de multimedia");
        return persistence.create(multimedia);
    }
    
    /**
     * Obtiene la lista de los registros de Multimedia que pertenecen a una entidad.
     *
     * @param adopcionId id de adopcion el cual es padre de las Multimedia.
     * @param encontradaId id de encontrada el cual es padre de las Multimedia.
     * @param perdidaId id de perdida el cual es padre de las Multimedia.
     * @param publicidadId id de publicidad el cual es padre de las Multimedia.
     * @return Colección de objetos de MultimediaEntity.
     */
    public List<MultimediaEntity> getMultimedias(Long adopcionId, Long encontradaId, Long perdidaId, Long publicidadId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la multimedia asociada a los objetos con id = {0} {1} {2} {3}", new Object[]{adopcionId, encontradaId, perdidaId, publicidadId});
        MascotaAdopcionEntity adopcion = null;
        MascotaEncontradaEntity encontrada = null;
        MascotaPerdidaEntity perdida = null;
        PublicidadEntity publicidad = null;
        if(adopcionId != null)
            adopcion = adopcionPersistence.find(adopcionId);
        else if(encontradaId != null)
            encontrada = encontradaPersistence.find(encontradaId);
        else if(perdidaId != null)
            perdida = perdidaPersistence.find(perdidaId);
        else if(publicidadId != null)
            publicidad = publicidadPersistence.find(publicidadId);
        List<MultimediaEntity> lista = new ArrayList<>();
        if(adopcion != null)
        {
            lista = adopcion.getMultimedia();
        }
        else if(encontrada != null)
        {
            lista = encontrada.getMultimedia();
        }
        else if(perdida != null)
        {
            lista = perdida.getMultimedia();
        }
        else
        {
            if(publicidad != null)
                lista = publicidad.getMultimedia();
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la multimedia asociada a los objetos con id = {0} {1} {2} {3}", new Object[]{adopcionId, encontradaId, perdidaId, publicidadId});
        return lista;
    }
    
    /**
     * Obtiene los datos de una instancia de Multimedia a partir de su ID.
     *
     * @param multimediaId Identificador de la Multimedia a consultar
     * @return Instancia de MultimediaEntity con los datos del Multimedia consultado.
     *
     */
    public MultimediaEntity getMultimedia(Long multimediaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la multimedia con id = {0}", multimediaId);
        return persistence.find(multimediaId);
    }
    
    
      /**
      * Verifica las reglas de negocio al actualizar una multimedia
      * @param multimedia es la multimedia a revisar
      * @return La entidad revisada
      * @throws BusinessLogicException  si se incumplen reglas de negocio
      */
    public MultimediaEntity updateMultimedia(MultimediaEntity multimedia) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la multimedia con id = {0}" , multimedia.getId());
        check(multimedia);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la multimedia con id = {0}", multimedia.getId());
        return persistence.update(multimedia);

    }
    
    
    
      /**
      * Borra una multimedia
      * @param id es el id de la multimedia a borrar
      * @throws BusinessLogicException  si se incumplen reglas de negocio
      */
    public void deleteMultimedia(Long id) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la multimedia con id = {0}", id);
        persistence.delete(id);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la multimedia con id = {0}", id);
    }
    
    
    
}
