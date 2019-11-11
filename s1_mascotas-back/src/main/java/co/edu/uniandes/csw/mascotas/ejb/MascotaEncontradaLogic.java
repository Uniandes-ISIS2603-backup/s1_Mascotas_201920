/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaEncontradaPersistence;
import co.edu.uniandes.csw.mascotas.podam.TipoEspecies;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ja.avelino
 */
@Stateless
public class MascotaEncontradaLogic {
    
    @Inject
    private MascotaEncontradaPersistence persistence;
    
    public MascotaEncontradaEntity createMascotaEncontrada(MascotaEncontradaEntity pMascota) throws BusinessLogicException
    {
        if(pMascota.getDescripcion()==null || pMascota.getDescripcion().equals(""))
        {
            throw new BusinessLogicException("La descripcion de la mascota no existe.");
        }
        if(pMascota.getEspecie()==null)
        {
            throw new BusinessLogicException ("La especie de la mascota no existe."); 
        }
        boolean flag= false;
        for (TipoEspecies value : TipoEspecies.values()) {
            if (value.ordinal() == pMascota.getEspecie().intValue()) {
                flag = true;
            }
        }
        if (!flag)
            throw new BusinessLogicException ("La mascota no es una especie correcta.");
        if (pMascota.getRaza()==null || pMascota.getRaza().equals(""))
            throw new BusinessLogicException ("La raza de la mascota no existe.");
        if (pMascota.getLugar()==null||pMascota.getLugar().equals(""))
            throw new BusinessLogicException ("El lugar de la mascota encontrada no existe.");
        if (pMascota.getFechaEncontrada()==null)
            throw new BusinessLogicException ("La fecha de encuentro de la mascota encontrada no existe.");
        
        pMascota = persistence.create(pMascota);
        return pMascota;
    }
    
    public MascotaEncontradaEntity updateMascotaEncontrada(MascotaEncontradaEntity pMascota) throws BusinessLogicException
    {
        if(pMascota.getDescripcion()==null)
        {
            throw new BusinessLogicException("La descripcion de la mascota no existe.");
        }
        if(pMascota.getEspecie()==null)
        {
            throw new BusinessLogicException ("La especie de la mascota no existe."); 
        }
        boolean flag= false;
        for (TipoEspecies value : TipoEspecies.values()) {
            if (value.ordinal() == pMascota.getEspecie().intValue()) {
                flag = true;
            }
        }
        if (!flag)
            throw new BusinessLogicException ("La mascota no es un gato, ni un perro.");
        if (pMascota.getRaza()==null || pMascota.getRaza().equals(""))
            throw new BusinessLogicException ("La raza de la mascota no existe.");
        if (pMascota.getLugar()==null||pMascota.getLugar().equals(""))
            throw new BusinessLogicException ("El lugar de la mascota encontrada no existe.");
        if (pMascota.getFechaEncontrada()==null)
            throw new BusinessLogicException ("La fecha de encuentro de la mascota encontrada no existe.");
        
        pMascota = persistence.update(pMascota);
        return pMascota;
    }
    
    public MascotaEncontradaEntity findMascotaEncontrada(Long id)
    {
        return persistence.find(id);
    }
    
    public List<MascotaEncontradaEntity> findAllMascotaEncontrada()
    {
        return persistence.findAll();
    }
    
    public void deleteMascotaEncontrada(Long id)
    {
        persistence.delete(id);
    }
    
}
