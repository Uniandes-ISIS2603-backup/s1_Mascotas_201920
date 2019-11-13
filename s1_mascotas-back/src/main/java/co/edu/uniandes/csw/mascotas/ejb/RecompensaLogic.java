/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.RecompensaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.RecompensaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author William Smith
 */
@Stateless
public class RecompensaLogic {
    
    @Inject
    private RecompensaPersistence persistence;
    
    public RecompensaEntity createRecompensa(RecompensaEntity recompensa) throws BusinessLogicException{
        if(recompensa.getMonto()<=0){
            throw new BusinessLogicException("El monto de la recompensa debe ser mayor a 0");
        }
        if(recompensa.getPagado()){
          throw new BusinessLogicException("La recompensa no puede ser creada como ya pagada");  
        }
        recompensa=persistence.create(recompensa);
        return recompensa;
    }
    
    public RecompensaEntity updateRecompensa(RecompensaEntity recompensa) throws BusinessLogicException{
        if(recompensa.getMonto()<=0){
            throw new BusinessLogicException("El monto de la recompensa debe ser mayor a 0");
        }
        recompensa=persistence.update(recompensa);
        return recompensa;
    }
    
    public void deleteRecompensa(Long recompensaID)
    {
        persistence.delete(recompensaID);
    }
    
    public RecompensaEntity findRecompensa(Long recompensaID)
    {
        return persistence.find(recompensaID);
    }
    
    public List<RecompensaEntity> findAllRecompensas()
    {
        return persistence.findAll();
    }
    
    
    
}
