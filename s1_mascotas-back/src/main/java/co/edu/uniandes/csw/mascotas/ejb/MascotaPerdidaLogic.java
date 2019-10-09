/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;


import co.edu.uniandes.csw.mascotas.entities.MascotaPerdidaEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaPerdidaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPerdidaPersistence;
import co.edu.uniandes.csw.mascotas.podam.TipoEspecies;
import java.util.List;
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
    
    /**
     * Revisa todas las reglas de negocio al crear una mascota.
     * @param masc mascota que se va a crear
     * @return mascota creada
     * @throws BusinessLogicException Si no cumple alguna regla de negocio 
     */
    public MascotaPerdidaEntity createMascotaPerdida ( MascotaPerdidaEntity masc) throws BusinessLogicException
    {
        check(masc);
        
        
        masc = pers.create(masc);
        return masc;
    }
    /**
     * Revisa todas las reglas de negocio al actualizar una mascota.
     * @param masc mascota que se va a actualizar
     * @return mascota actualizada
     * @throws BusinessLogicException Si no cumple alguna regla de negocio 
     */
    public MascotaPerdidaEntity updateMascotaPerdida(MascotaPerdidaEntity masc) throws BusinessLogicException
    {
        
        check (masc);
        
        masc= pers.update(masc);
        return masc;
    }
    
    /**
     * Revisa todas las reglas de negocio de una mascota.
     * @param masc mascota que se va a verificar
     * @throws BusinessLogicException Si no cumple alguna regla de negocio 
     */
    public void check (MascotaPerdidaEntity masc) throws BusinessLogicException
    {
        
       //Reglas de negocio de especie
        if (masc.getEspecie()==null)
            throw new BusinessLogicException ("La mascota no tiene especie."); 
        boolean flag= false;
        for (int i=0; i< TipoEspecies.values().length;i++) {
            if (Integer.compare(masc.getEspecie(), i) == 0) {
                flag = true;
            }
        }
        if (!flag)
            throw new BusinessLogicException ("La mascota no es un gato, ni un perro."); 
        
        //Reglas de negocio de raza
        if (masc.getRaza()==null)
            throw new BusinessLogicException ("La mascota no tiene raza.");
        if (masc.getRaza().equals(""))
            throw new BusinessLogicException ("La mascota no tiene raza.");
        
        //Reglas de negocio de la descripción
        if (masc.getDescripcion()==null)
            throw new BusinessLogicException ("La mascota no tiene descripción.");
        if (masc.getDescripcion().equals(""))
            throw new BusinessLogicException ("La mascota no tiene descripción.");
        
        //Reglas de negocio de lugar
        if (masc.getLugar()==null)
            throw new BusinessLogicException ("La mascota no tiene lugar.");
        if (masc.getLugar().equals(""))
            throw new BusinessLogicException ("La mascota no tiene lugar.");
        
    }
    /**
     * 
     * @param id
     * @return
     * @throws BusinessLogicException 
     */
    public MascotaPerdidaEntity findMascotaPerdida(Long id) throws BusinessLogicException
    {
        return pers.find(id);
    }
    /**
     * 
     * @return
     * @throws BusinessLogicException 
     */
    public List<MascotaPerdidaEntity> findAllMascotaPerdida() throws BusinessLogicException
    {
        return pers.findAll();
    }
    /**
     * Revisa todas las reglas de negocio al eliminar una mascota.
     * @param id id de la mascota que se va a eliminar
     * @throws BusinessLogicException Si no cumple alguna regla de negocio 
     */
    public void deleteMascotaPerdida(Long id) throws BusinessLogicException
    {
        pers.delete(id);
    }
    
    public List<MascotaPerdidaEntity> getMascotasPerdida() throws BusinessLogicException {

        List<MascotaPerdidaEntity> mascotas = pers.findAll();

        for (int i = 0; i < mascotas.size(); i++) {
            check(mascotas.get(i));
        }

        return mascotas;
    }

    public MascotaPerdidaEntity getMascotaPerdida(Long mascotaID) throws BusinessLogicException {

        MascotaPerdidaEntity mascota = pers.find(mascotaID);
        check(mascota);

        return mascota;
    }
}   

