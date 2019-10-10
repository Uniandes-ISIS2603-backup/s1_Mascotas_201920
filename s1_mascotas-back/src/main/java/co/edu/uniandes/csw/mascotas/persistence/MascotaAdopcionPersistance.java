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

    /**
     * Crea la entidad
     * @param mascota entidad de MascotaAdopcion
     * @return La mascota creada
     */
    public MascotaAdopcionEntity create(MascotaAdopcionEntity mascota) {

        em.persist(mascota);
        return mascota;
    }
    /**
     * Busca una mascota
     * @param mascotaID el ID de la mascota a buscar
     * @return la mascota encontrada
     */
    public MascotaAdopcionEntity find(Long mascotaID) {

        return em.find(MascotaAdopcionEntity.class, mascotaID);
    }
    /**
     * Busca todas las macotas
     * @return las mactotas
     */
    public List<MascotaAdopcionEntity> findAll() {
        TypedQuery<MascotaAdopcionEntity> query = em.createQuery("select u from MascotaAdopcionEntity u", MascotaAdopcionEntity.class);
        return query.getResultList();
    }
    /**
     * Actualiza la mascota
     * @param mascota Mascota a actualizar
     * @return La mascota actualizada
     */
    public MascotaAdopcionEntity update(MascotaAdopcionEntity mascota) {
        
        em.merge(mascota);
        
        return em.find(MascotaAdopcionEntity.class, mascota.getId());
    }
    /**
     * Borra la mascota
     * @param mascotaID es el ID de la mascota a borrar
     */
    public void delete(Long mascotaID) {

        MascotaAdopcionEntity mascota = em.find(MascotaAdopcionEntity.class, mascotaID);

        em.remove(mascota);
    }

}
