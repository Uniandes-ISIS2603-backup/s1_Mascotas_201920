/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.PublicidadEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.PublicidadPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author German Rozo
 */

@Stateless
public class PublicidadLogic 
{
    @Inject
    private PublicidadPersistence pp;    
    
    public PublicidadEntity createPublicidad(PublicidadEntity pe) throws BusinessLogicException
    {
        if(pe.getCosto()==null )
        {
            throw new BusinessLogicException("El costo de la publicidad esta vacio");
        }
        if(pe.getDiasPorSemana()== null)
        {
            throw new BusinessLogicException("Los dias por semanade la publicidad esta vacio");
        }
         if(pe.getFecchaFin()==null)
        {
            throw new BusinessLogicException("La fecha final de la publicidad esta vacia");
        }
         if(pe.getFechaInicio()==null)
        {
            throw new BusinessLogicException("La fecha inicial de la publicidad esta vacia");
        }
        if(pe.getMensaje()==null)
        {
            throw new BusinessLogicException("El mensaje de la publicidad esta vacio");
        }
        if(pe.getCosto()<=0)
            throw new BusinessLogicException("El costo de la publicidad es menor o igual a 0");
        /**if(!pe.getFecchaFin().after(pe.getFechaInicio()))
        {
            throw new BusinessLogicException("La fecha inicial es posterior a la final");
        }
        if(pe.getDiasPorSemana()<=0)
        {
            throw new BusinessLogicException("Los dias por semana tienen que ser mayores a 0");
        }**/
        pe= pp.create(pe);
        return pe;
    }
    
    public PublicidadEntity updatePublicidad(PublicidadEntity pe) throws BusinessLogicException
    {
        if(pe.getCosto()==null )
        {
            throw new BusinessLogicException("El costo de la publicidad esta vacio");
        }
        if(pe.getDiasPorSemana()== null)
        {
            throw new BusinessLogicException("Los dias por semanade la publicidad esta vacio");
        }
         if(pe.getFecchaFin()==null)
        {
            throw new BusinessLogicException("La fecha final de la publicidad esta vacia");
        }
         if(pe.getFechaInicio()==null)
        {
            throw new BusinessLogicException("La fecha inicial de la publicidad esta vacia");
        }
         if(pe.getMensaje()==null)
        {
            throw new BusinessLogicException("El mensaje de la publicidad esta vacio");
        }
        
        pe= pp.update(pe);
        return pe;
    }
    
    public PublicidadEntity findPublicidad(Long id) throws BusinessLogicException
    {
        return pp.find(id);
    }
    
    public List<PublicidadEntity> findAllPublicidad() throws BusinessLogicException
    {
        return pp.findAll();
    }
    
    public void deletePublicidad(Long id) throws BusinessLogicException
    {
        pp.delete(id);
    }
    
    
}
