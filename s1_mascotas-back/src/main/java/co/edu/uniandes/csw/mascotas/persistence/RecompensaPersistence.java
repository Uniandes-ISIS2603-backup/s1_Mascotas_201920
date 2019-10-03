/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.RecompensaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author William Smith
 */
@Stateless
public class RecompensaPersistence {
    
    @PersistenceContext(unitName = "mascotasPU")
    protected EntityManager em;
    
    public RecompensaEntity create(RecompensaEntity recompensaEntity){
        em.persist(recompensaEntity);
        return recompensaEntity;
    }
    
    public List<RecompensaEntity> findAll(){
        Query q=em.createQuery("select u from RecompensaEntity u");
        return q.getResultList();
    }
    
    public RecompensaEntity find(Long recompensaId){
        return em.find(RecompensaEntity.class, recompensaId);
    }
    
    public RecompensaEntity update(RecompensaEntity recompensaEntity){
        return em.merge(recompensaEntity);
    }
    
    public void delete(Long recompensaId){
        RecompensaEntity recompensaEntity=em.find(RecompensaEntity.class,recompensaId);
        em.remove(recompensaEntity);
    }
    
}
