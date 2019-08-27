/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.Mascota_AdopcionEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Tomás Langebaek
 */
@Stateless
public class Mascota_AdopcionPersistance {

    @PersistenceContext(unitName = "mascotasPU")
    protected EntityManager em;

    public Mascota_AdopcionEntity create(Mascota_AdopcionEntity mascota) {

        em.persist(mascota);
        return mascota;
        //throw new java.lang.UnsupportedOperationException("Not supported yet.");
    }

    public Mascota_AdopcionEntity find(Long mascotaID) {

        return em.find(Mascota_AdopcionEntity.class, mascotaID);
    }

    public List<Mascota_AdopcionEntity> findAll() {
        TypedQuery<Mascota_AdopcionEntity> query = em.createQuery("select u from Mascota_AdopcionEntity u", Mascota_AdopcionEntity.class);
        return query.getResultList();
    }

    public Mascota_AdopcionEntity update(Mascota_AdopcionEntity mascota) {

        Mascota_AdopcionEntity nueva = em.find(Mascota_AdopcionEntity.class, mascota.getId());
        
        em.merge(mascota);
        

        return em.find(Mascota_AdopcionEntity.class, mascota.getId());
    }

    public void delete(Long mascotaID) {

        Mascota_AdopcionEntity mascota = em.find(Mascota_AdopcionEntity.class, mascotaID);

        em.remove(mascota);
    }

}
