/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaAdopcionPersistance;
import co.edu.uniandes.csw.mascotas.podam.TipoEspecies;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Tomás Langebaek
 */
@Stateless
public class MascotaAdopcionLogic {

    @Inject
    private MascotaAdopcionPersistance persistence;

    public void check(MascotaAdopcionEntity mascota) throws BusinessLogicException {

        if (mascota.getLugar() == null) {
            throw new BusinessLogicException("El lugar de la mascota está vacío");
        }
        if (mascota.getLugar().equals("")) {
            throw new BusinessLogicException("La mascota no tiene lugar valido.");
        }
        if (mascota.getRaza() == null) {
            throw new BusinessLogicException("La raza esta vacia");
        }
        if (mascota.getRaza().equals("")) {
            throw new BusinessLogicException("La mascota no tiene raza valida.");
        }
        if (mascota.getEspecie() == null) {
            throw new BusinessLogicException("La especie esta vacia");
        }
        if (mascota.getDescripcion() == null) {
            throw new BusinessLogicException("La descripcion esta vacia");
        }
        if (mascota.getDescripcion().equals("")) {
            throw new BusinessLogicException("La mascota no tiene descripcion valida.");
        }
        boolean ck = false;
        if (mascota.getEspecie() != 0) {
            if (mascota.getEspecie() != 1) {
               ck = true;
            }
        }
        if(ck){
            throw new BusinessLogicException("La mascota no es un gato, ni un perro.");
        }

    }

    public MascotaAdopcionEntity createMascotaAdopcion(MascotaAdopcionEntity mascota) throws BusinessLogicException {

        check(mascota);

        mascota = persistence.create(mascota);
        return mascota;
    }

    public MascotaAdopcionEntity updateMascotaAdopcion(MascotaAdopcionEntity mascota) throws BusinessLogicException {

        check(mascota);

        return persistence.update(mascota);

    }

    public List<MascotaAdopcionEntity> getMascotasAdopcion() throws BusinessLogicException {

        List<MascotaAdopcionEntity> mascotas = persistence.findAll();

        for (int i = 0; i > mascotas.size(); i++) {
            check(mascotas.get(i));
        }

        return mascotas;
    }

    public MascotaAdopcionEntity getMascotaAdopcion(Long mascotaID) throws BusinessLogicException {

        MascotaAdopcionEntity mascota = persistence.find(mascotaID);
        check(mascota);

        return mascota;
    }

    public void deleteMascotaAdopcion(Long mascotaID) throws BusinessLogicException {

        MascotaAdopcionEntity mascota = persistence.find(mascotaID);
        check(mascota);
        persistence.delete(mascotaID);

    }

}
