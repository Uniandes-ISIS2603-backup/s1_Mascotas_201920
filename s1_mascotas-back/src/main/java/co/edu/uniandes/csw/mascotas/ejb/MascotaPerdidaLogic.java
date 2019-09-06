/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;


import co.edu.uniandes.csw.mascotas.entities.MascotaPerdidaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPerdidaPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Lily
 */
@Stateless 
public class MascotaPerdidaLogic 
{
    @Inject 
    private MascotaPerdidaPersistence pers;
    
    public MascotaPerdidaEntity createMascotaPerdida ( MascotaPerdidaEntity masc) throws BusinessLogicException
    {
        if (masc.getEspecie()==null)
            throw new BusinessLogicException ("La mascota no tiene especie."); 
        if (!masc.getEspecie().equals("Gato")&&  !masc.getEspecie().equals("Perro"))
            throw new BusinessLogicException ("La mascota no es un gato, ni un perro."); 
        
        if (masc.getRaza()==null)
            throw new BusinessLogicException ("La mascota no tiene raza.");
        if (masc.getRaza().equals(""))
            throw new BusinessLogicException ("La mascota no tiene raza.");
        
        if (masc.getDescripcion()==null)
            throw new BusinessLogicException ("La mascota no tiene descripción.");
        if (masc.getDescripcion().equals(""))
            throw new BusinessLogicException ("La mascota no tiene descripción.");
        
        if (masc.getLugar()==null)
            throw new BusinessLogicException ("La mascota no tiene lugar.");
        if (masc.getLugar().equals(""))
            throw new BusinessLogicException ("La mascota no tiene lugar.");
        
        
        masc = pers.create(masc);
        return masc;
    }
}   

