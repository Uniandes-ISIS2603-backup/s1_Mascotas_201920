/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.MultimediaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Tomas Langebaek
 */
@Stateless
public class MultimediaPersistence {
    
     @PersistenceContext(unitName = "mascotasPU")
    protected EntityManager em;
    
    public MultimediaEntity create(MultimediaEntity multimedia){
        em.persist(multimedia);
        
        return multimedia;
    }
    
    public MultimediaEntity find(Long multimediaID){
        return em.find(MultimediaEntity.class, multimediaID);
    }
    
    public List<MultimediaEntity> findAll(){
        TypedQuery query = em.createQuery("select u from MultimediaEntity u", MultimediaEntity.class);
        return query.getResultList();
    }
    
    public MultimediaEntity update(MultimediaEntity multimedia) {

        MultimediaEntity nuevo = em.find(MultimediaEntity.class, multimedia.getId());
        
        em.merge(multimedia);
        

        return em.find(MultimediaEntity.class, multimedia.getId());
    }
    
    public void delete(Long id) {

        MultimediaEntity proceso = em.find(MultimediaEntity.class, id);

        em.remove(proceso);
    }
    
}
