/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaAdopcionPersistance;
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
    
    public MascotaAdopcionEntity createMascotaAdopcion(MascotaAdopcionEntity mascota)throws BusinessLogicException{
        
    if(mascota.getLugar()==null){
        throw new BusinessLogicException("El lugar de la mascota está vacío");
    }
    mascota = persistence.create(mascota);
    return mascota;
}

}

