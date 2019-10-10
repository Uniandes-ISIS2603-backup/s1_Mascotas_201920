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
    /**
     * Persiste la multimedia entity
     * @param multimedia es la multimedia a persistir
     * @return la multimedia persistida
     */
    public MultimediaEntity create(MultimediaEntity multimedia){
        em.persist(multimedia);
        
        return multimedia;
    }
    /**
     * Busca la multimedia persistida
     * @param multimediaID es el ID de la multimedia a buscar
     * @return la multimedia encontrada
     */
    public MultimediaEntity find(Long multimediaID){
        return em.find(MultimediaEntity.class, multimediaID);
    }
    /**
     * Busca todas las multimedias persistidas
     * @return lista de todas las multimedias
     */
    public List<MultimediaEntity> findAll(){
        TypedQuery query = em.createQuery("select u from MultimediaEntity u", MultimediaEntity.class);
        return query.getResultList();
    }
    /**
     * Actualiza una miltimedia persistida
     * @param multimedia es la multimedia a actualizar
     * @return 
     */
    public MultimediaEntity update(MultimediaEntity multimedia) {

        MultimediaEntity nuevo = em.find(MultimediaEntity.class, multimedia.getId());
        
        em.merge(multimedia);
        

        return em.find(MultimediaEntity.class, multimedia.getId());
    }
    /**
     * Borra una multimedia
     * @param id es el id de la multimedia a borrar
     */
    public void delete(Long id) {

        MultimediaEntity proceso = em.find(MultimediaEntity.class, id);

        em.remove(proceso);
    }
    
}
