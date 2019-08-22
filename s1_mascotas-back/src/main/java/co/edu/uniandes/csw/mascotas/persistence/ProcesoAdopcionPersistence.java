/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.ProcesoAdopcionEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 *
 * @author William Smith
 */
@Stateless
public class ProcesoAdopcionPersistence {
    
    @PersistenceContext(unitName = "mascotasPU")
    protected EntityManager em;
    
    public ProcesoAdopcionEntity create(ProcesoAdopcionEntity proceso){
        em.persist(proceso);
        
        return proceso;
    }
    
    public ProcesoAdopcionEntity find(Long procesoID){
        return em.find(ProcesoAdopcionEntity.class, procesoID);
    }
    
    public List<ProcesoAdopcionEntity> findAll(){
        TypedQuery query = em.createQuery("select u from ProcesoAdopcionEntity u", ProcesoAdopcionEntity.class);
        return query.getResultList();
    }
    
}
