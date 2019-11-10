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
    
    /**
     * Persiste el parametro entity si este cumple con las reglas de negocio
     * @param pe publicidad entity
     * @return el entity con el id asignado o null si no se guardo el objeto 
     * @throws BusinessLogicException
     */
    public PublicidadEntity createPublicidad(PublicidadEntity pe) throws BusinessLogicException
    {
        validar(pe);
        pe = pp.create(pe);
        return pe;
    }
    
    /**
     * Actualiza el parametro entity en la base de datos, si este cumple con las reglas de negocio
     * @param pe 
     * @return el entity con el la informacion actualizada
     * @throws BusinessLogicException 
     */
    public PublicidadEntity updatePublicidad(PublicidadEntity pe) throws BusinessLogicException
    {
        validar(pe);
        pe= pp.update(pe);
        return pe;
    }
    
    public PublicidadEntity findPublicidad(Long id) throws BusinessLogicException
    {
        return pp.find(id);
    }
    
    public List<PublicidadEntity> findAll()
    {
        return pp.findAll();
    }
    
    public void deletePublicidad(Long id) throws BusinessLogicException
    {
        pp.delete(id);
    }
    
    /**
     * Valida que la publicidad cumpla con las reglas de negocio, de lo contrario lanza BusinessLogicException
     * @param entity
     * @throws BusinessLogicException 
     */
    private void validar(PublicidadEntity entity) throws BusinessLogicException
    {
        if(entity.getCosto()==null )
        {
            throw new BusinessLogicException("El costo de la publicidad esta vacio");
        }
        if(entity.getDiasPorSemana()== null)
        {
            throw new BusinessLogicException("Los dias por semanade la publicidad esta vacio");
        }
         if(entity.getFecchaFin()==null)
        {
            throw new BusinessLogicException("La fecha final de la publicidad esta vacia");
        }
         if(entity.getFechaInicio()==null)
        {
            throw new BusinessLogicException("La fecha inicial de la publicidad esta vacia");
        }
        if(entity.getMensaje()==null)
        {
            throw new BusinessLogicException("El mensaje de la publicidad esta vacio");
        }
        if(entity.getCosto()<=0)
            
            throw new BusinessLogicException("El costo de la publicidad es menor o igual a 0");
        
        if(entity.getDiasPorSemana()!=3 && entity.getDiasPorSemana()!=1)
            
            throw new BusinessLogicException("El numero de disponibilidad semanal deve ser 1 o 3 pero es:"+entity.getDiasPorSemana());
        
    }
    
}
