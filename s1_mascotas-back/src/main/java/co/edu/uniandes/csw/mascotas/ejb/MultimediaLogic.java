/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;
import co.edu.uniandes.csw.mascotas.entities.MultimediaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MultimediaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Tomas Langebaek
 */
@Stateless
public class MultimediaLogic {
    
    
    @Inject
    private MultimediaPersistence persistence;
    /**
     * Método para revisar la lógica de los atributos de la entidad MultimediaEntity
     * @param multimedia es la entidad a checkear
     * @throws BusinessLogicException si se incumple una regla de negocio
     */
    
     public void check(MultimediaEntity multimedia) throws BusinessLogicException {

        if (multimedia.getNombre() == null) {
            throw new BusinessLogicException("El el nombre de multimedia está vacío");
        }
        if (multimedia.getNombre().equals("")) {
            throw new BusinessLogicException("La multimedia no tiene nombre valido.");
        }
        if (multimedia.getTipo() == null) {
            throw new BusinessLogicException("El tipo esta vacio");
        }
        if (multimedia.getTipo().equals("")) {
            throw new BusinessLogicException("La multimedia no tiene tipo valido.");
        }
        if (multimedia.getUrl() == null) {
            throw new BusinessLogicException("La url no puede ser nula");
        }
        if (multimedia.getUrl().equals("")) {
            throw new BusinessLogicException("La url esta vacia");
        }

    }
     /**
      * Verifica las reglas de negocio al crear una multimedia
      * @param multimedia es la multimedia a revisar
      * @return La entidad revisada
      * @throws BusinessLogicException  si se incumplen reglas de negocio
      */
    public MultimediaEntity createMultimedia(MultimediaEntity multimedia) throws BusinessLogicException {

        check(multimedia);

        multimedia = persistence.create(multimedia);
        return multimedia;
    }
      /**
      * Verifica las reglas de negocio al actualizar una multimedia
      * @param multimedia es la multimedia a revisar
      * @return La entidad revisada
      * @throws BusinessLogicException  si se incumplen reglas de negocio
      */
    public MultimediaEntity updateMultimedia(MultimediaEntity multimedia) throws BusinessLogicException {

        check(multimedia);

        return persistence.update(multimedia);

    }
      /**
      * Verifica las reglas de negocio al buscar todas las multimedia
      * @return Las entidades revisadas
      * @throws BusinessLogicException  si se incumplen reglas de negocio
      */
    public List<MultimediaEntity> getMultimedias() throws BusinessLogicException {

        List<MultimediaEntity> multimedia = persistence.findAll();

        for (int i = 0; i < multimedia.size(); i++) {
            check(multimedia.get(i));
        }

        return multimedia;
    }
      /**
      * Verifica las reglas de negocio buscar una multimedia
      * @param multimediaID es el id de la multimedia a buscar
      * @return La entidad revisada, al traerla
      * @throws BusinessLogicException  si se incumplen reglas de negocio
      */
    public MultimediaEntity getMultimedia(Long multimediaID) throws BusinessLogicException {

        MultimediaEntity multimedia = persistence.find(multimediaID);
        check(multimedia);

        return multimedia;
    }
      /**
      * Verifica las reglas de negocio alborraruna multimedia
      * @param multimediaID es el id de la multimedia a borrar
      * @throws BusinessLogicException  si se incumplen reglas de negocio
      */
    public void deleteMultimedia(Long multimediaID) throws BusinessLogicException {

        MultimediaEntity mascota = persistence.find(multimediaID);
        check(mascota);
        persistence.delete(multimediaID);

    }
    
    
    
}
