/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;
import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
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

    public MultimediaEntity createMultimedia(MultimediaEntity multimedia) throws BusinessLogicException {

        check(multimedia);

        multimedia = persistence.create(multimedia);
        return multimedia;
    }

    public MultimediaEntity updateMultimedia(MultimediaEntity multimedia) throws BusinessLogicException {

        check(multimedia);

        return persistence.update(multimedia);

    }

    public List<MultimediaEntity> getMultimedias() throws BusinessLogicException {

        List<MultimediaEntity> multimedia = persistence.findAll();

        for (int i = 0; i < multimedia.size(); i++) {
            check(multimedia.get(i));
        }

        return multimedia;
    }

    public MultimediaEntity getMultimedia(Long multimediaID) throws BusinessLogicException {

        MultimediaEntity multimedia = persistence.find(multimediaID);
        check(multimedia);

        return multimedia;
    }

    public void deleteMultimedia(Long multimediaID) throws BusinessLogicException {

        MultimediaEntity mascota = persistence.find(multimediaID);
        check(mascota);
        persistence.delete(multimediaID);

    }
    
    
    
}
