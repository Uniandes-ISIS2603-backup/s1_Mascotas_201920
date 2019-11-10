/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.MascotaPerdidaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para Book. 
 * Se conecta a través del Entity Manager de javax.persistance con la base de datos SQL.
 * @author Lily
 */
@Stateless
public class MascotaPerdidaPersistence {
     
    @PersistenceContext (unitName="mascotasPU")
    protected EntityManager em;

    /**
     *  ePrsiste una mascota <br> 
     *  @param mascotaPerdidaEntity mascota que va a persitir
     *  @return  mascotaPerdidaEntity
     */
    public MascotaPerdidaEntity create  (MascotaPerdidaEntity mascota)
    {
        em.persist(mascota);
        return mascota;
    }
    /**
     * Se busca una mascota con el Id que pasan por parámetro.
     * @param mascotaId El id de la mascota que se va a buscar.
     * @return MascotaPerdidaEntity la mascota que buscaba, si no se encuenta null.
     */
    public MascotaPerdidaEntity find (Long mascotaId)
    {
        return em.find(MascotaPerdidaEntity.class, mascotaId);
    }
    /**
     *  Retorna todas las mascotas perdidas que hay en la tabla.
     *  @return List <MascotaPerdidaEntity> Lista con todas las mascotas perdidas que hay.
     */
    public List <MascotaPerdidaEntity> findAll()
    {
        TypedQuery <MascotaPerdidaEntity> query = em.createQuery("select u from MascotaPerdidaEntity u",MascotaPerdidaEntity.class);
        return query.getResultList();
    }
    
     /**
     * Actualiza una mascota.
     *
     * @param mascotaEntity: la mascota que viene con los nuevos cambios. Por
     * ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una mascota con los cambios aplicados.
     */
    public MascotaPerdidaEntity update(MascotaPerdidaEntity mascota) {
        
        em.merge(mascota);
        

        return em.find(MascotaPerdidaEntity.class, mascota.getId());
    }
     /*
     * Borra un mascota de la base de datos recibiendo como argumento el id del
     * premio
     *
     *   @param mascotaId: id correspondiente a la mascota a borrar.
     */
    public void delete(Long mascotaID) {

        MascotaPerdidaEntity mascota = em.find(MascotaPerdidaEntity.class, mascotaID);

        em.remove(mascota);
    }
}
