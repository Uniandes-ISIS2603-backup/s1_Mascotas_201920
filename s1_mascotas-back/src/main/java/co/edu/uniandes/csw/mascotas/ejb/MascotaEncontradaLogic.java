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
    
    /**
     * Crea una mascota pero antes revisa que cumpla con los requerimientos
     * @param mEncontrada la mascota a crear
     * @return la mascota creada
     * @throws BusinessLogicException 
     */
    public MascotaEncontradaEntity createMascotaEncontrada(MascotaEncontradaEntity mEncontrada) throws BusinessLogicException
    {
        if(mEncontrada.getDescripcion()==null || mEncontrada.getDescripcion().equals(""))
        {
            throw new BusinessLogicException("La descripcion de la mascota no existe.");
        }
        if(mEncontrada.getEspecie()==null)
        {
            throw new BusinessLogicException ("La especie de la mascota no existe."); 
        }
        boolean flag= false;
        for (TipoEspecies value : TipoEspecies.values()) {
            if (value.ordinal() == mEncontrada.getEspecie().intValue()) {
                flag = true;
            }
        }
        if (!flag)
            throw new BusinessLogicException ("La mascota no es una especie correcta.");
        if (mEncontrada.getRaza()==null || mEncontrada.getRaza().equals(""))
            throw new BusinessLogicException ("La raza de la mascota no existe.");
        if (mEncontrada.getLugar()==null||mEncontrada.getLugar().equals(""))
            throw new BusinessLogicException ("El lugar de la mascota encontrada no existe.");
        if (mEncontrada.getFechaEncontrada()==null)
            throw new BusinessLogicException ("La fecha de encuentro de la mascota encontrada no existe.");
        
        mEncontrada = persistence.create(mEncontrada);
        return mEncontrada;
    }
    
    public MascotaEncontradaEntity updateMascotaEncontrada(MascotaEncontradaEntity mEncontrada) throws BusinessLogicException
    {
        if(mEncontrada.getDescripcion()==null)
        {
            throw new BusinessLogicException("La descripcion de la mascota no existe.");
        }
        if(mEncontrada.getEspecie()==null)
        {
            throw new BusinessLogicException ("La especie de la mascota no existe."); 
        }
        boolean flag= false;
        for (TipoEspecies value : TipoEspecies.values()) {
            if (value.ordinal() == mEncontrada.getEspecie().intValue()) {
                flag = true;
            }
        }
        if (!flag)
            throw new BusinessLogicException ("La mascota no es un gato, ni un perro.");
        if (mEncontrada.getRaza()==null || mEncontrada.getRaza().equals(""))
            throw new BusinessLogicException ("La raza de la mascota no existe.");
        if (mEncontrada.getLugar()==null||mEncontrada.getLugar().equals(""))
            throw new BusinessLogicException ("El lugar de la mascota encontrada no existe.");
        if (mEncontrada.getFechaEncontrada()==null)
            throw new BusinessLogicException ("La fecha de encuentro de la mascota encontrada no existe.");
        
        mEncontrada = persistence.update(mEncontrada);
        return mEncontrada;
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
