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
    
     private enum especies {
        Perro,
        Gato
    }
    
    @Inject
    private MascotaAdopcionPersistance persistence;
    
    public MascotaAdopcionEntity createMascotaAdopcion(MascotaAdopcionEntity mascota)throws BusinessLogicException{
        
    if(mascota.getLugar()==null){
        throw new BusinessLogicException("El lugar de la mascota está vacío");
    }
    if (mascota.getLugar().equals("")){
            throw new BusinessLogicException ("La mascota no tiene lugar valido.");
    }
    if(mascota.getRaza()==null){
        throw new BusinessLogicException("La raza esta vacia");
    }
     if (mascota.getRaza().equals("")){
            throw new BusinessLogicException ("La mascota no tiene raza valida.");
    }
     if(mascota.getEspecie()==null){
        throw new BusinessLogicException("La especie esta vacia");
    }
     if(mascota.getDescripcion()==null){
        throw new BusinessLogicException("La descripcion esta vacia");
    }
      if (mascota.getDescripcion().equals("")){
            throw new BusinessLogicException ("La mascota no tiene descripcion valida.");
    }
     boolean flag = false;
     for (MascotaAdopcionLogic.especies valor : MascotaAdopcionLogic.especies.values()) {
            if (valor.name().equals(mascota.getEspecie())) {
                flag = true;
            }
        }
        if (!flag){
            throw new BusinessLogicException ("La mascota no es un gato, ni un perro."); 
        }
        
        
    mascota = persistence.create(mascota);
    return mascota;
}
    public MascotaAdopcionEntity updateMascotaAdopcion(MascotaAdopcionEntity mascota) throws BusinessLogicException
    {
        if(mascota.getLugar()==null){
        throw new BusinessLogicException("El lugar de la mascota está vacío");
    }
    if (mascota.getLugar().equals("")){
            throw new BusinessLogicException ("La mascota no tiene lugar valido.");
    }
    if(mascota.getRaza()==null){
        throw new BusinessLogicException("La raza esta vacia");
    }
     if (mascota.getRaza().equals("")){
            throw new BusinessLogicException ("La mascota no tiene raza valida.");
    }
     if(mascota.getEspecie()==null){
        throw new BusinessLogicException("La especie esta vacia");
    }
     if(mascota.getDescripcion()==null){
        throw new BusinessLogicException("La descripcion esta vacia");
    }
      if (mascota.getDescripcion().equals("")){
            throw new BusinessLogicException ("La mascota no tiene descripcion valida.");
    }
     boolean flag = false;
     for (MascotaAdopcionLogic.especies valor : MascotaAdopcionLogic.especies.values()) {
            if (valor.name().equals(mascota.getEspecie())) {
                flag = true;
            }
        }
        if (!flag){
            throw new BusinessLogicException ("La mascota no es un gato, ni un perro."); 
        }
        
        
        return persistence.update(mascota);
  
    }

}

