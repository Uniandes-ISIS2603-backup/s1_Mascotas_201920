/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.PublicidadEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.PublicidadPersistence;
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
            throw new BusinessLogicException("");
        }
        if(pe.getDiasPorSemana()== null)
        {
            throw new BusinessLogicException("");
        }
         if(pe.getFecchaFin()==null)
        {
            throw new BusinessLogicException("");
        }
         if(pe.getFechaInicio()==null)
        {
            throw new BusinessLogicException("");
        }
         if(pe.getMensaje()==null)
        {
            throw new BusinessLogicException("");
        }
        
        
        pe= pp.create(pe);
        return pe;
    }
    
    
}
