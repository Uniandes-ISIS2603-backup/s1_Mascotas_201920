/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.PublicidadEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author German Rozo
 */
@Stateless
public class PublicidadPersistence {

    @PersistenceContext(unitName = "mascotasPU")
    protected EntityManager em;

    public PublicidadEntity create(PublicidadEntity publicidad) {

        em.persist(publicidad);
        return publicidad;

    }

    public PublicidadEntity find(Long id) {
        return em.find(PublicidadEntity.class, id);
    }
    
    public PublicidadEntity update(PublicidadEntity o)
    {
        
        PublicidadEntity en= find(o.getId());
        em.merge(o);
        return find(o.getId());
    }

    public List<PublicidadEntity> findAll() {

        TypedQuery<PublicidadEntity> query= em.createQuery("select u from PublicidadEntity u", PublicidadEntity.class);
        return query.getResultList();
    }
    
    public void delete(Long id)
    {
        PublicidadEntity en= find(id);
        em.remove(en);
    }
   
}
