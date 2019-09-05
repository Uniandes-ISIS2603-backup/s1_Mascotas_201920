/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
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
public class MascotaAdopcionPersistance {

    @PersistenceContext(unitName = "mascotasPU")
    protected EntityManager em;

    public MascotaAdopcionEntity create(MascotaAdopcionEntity mascota) {

        em.persist(mascota);
        return mascota;
    }

    public MascotaAdopcionEntity find(Long mascotaID) {

        return em.find(MascotaAdopcionEntity.class, mascotaID);
    }

    public List<MascotaAdopcionEntity> findAll() {
        TypedQuery<MascotaAdopcionEntity> query = em.createQuery("select u from MascotaAdopcionEntity u", MascotaAdopcionEntity.class);
        return query.getResultList();
    }

    public MascotaAdopcionEntity update(MascotaAdopcionEntity mascota) {
        
        em.merge(mascota);
        
        return em.find(MascotaAdopcionEntity.class, mascota.getId());
    }

    public void delete(Long mascotaID) {

        MascotaAdopcionEntity mascota = em.find(MascotaAdopcionEntity.class, mascotaID);

        em.remove(mascota);
    }

}
