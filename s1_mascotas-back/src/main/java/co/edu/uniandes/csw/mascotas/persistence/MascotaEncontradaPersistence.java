/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author ja.avelino
 */
@Stateless
public class MascotaEncontradaPersistence {
    
    @PersistenceContext(unitName = "mascotasPU")
    protected EntityManager em;
    
    
    /**
     * Crea en la base de datos el registro de la mascota entrada por parametro.
     * @param mascota El objeto mascota a guardar.
     * @return El objeto de tipo mascota pero esta vez con identificacion.
     */
    public MascotaEncontradaEntity create(MascotaEncontradaEntity mascota)
    {
        em.persist(mascota);
        return mascota;
    }
    
    /**
     * Busca la mascota encontrada por la identificacion del parametro.
     * @param mascotaId La identificacion de la mascota.
     * @return El objeto de MascotaEncontrada que tiene la identificacion.
     */
    public MascotaEncontradaEntity find(Long mascotaId)
    {
        return em.find(MascotaEncontradaEntity.class, mascotaId);
    }
    
    /**
     * Consigue todos los registros de la tabla de MascotaEncontradaEntity
     * @return La lista con todas las mascotas encontradas que hay en la base de datos.
     */
    public List<MascotaEncontradaEntity> findAll()
    {
        TypedQuery<MascotaEncontradaEntity> query = em.createQuery("select u from MascotaEncontradaEntity u", MascotaEncontradaEntity.class);
        return query.getResultList();
    }
    
    /**
     * Actualiza la informacion del registro de mascota encontrada en la base de datos.
     * @param mascota: Informacion del registro a actualizar.
     * @return el objeto con los cambios hechos.
     */
    public MascotaEncontradaEntity update(MascotaEncontradaEntity mascota)
    {
        em.find(MascotaEncontradaEntity.class, mascota.getId());
        em.merge(mascota);

        return em.find(MascotaEncontradaEntity.class, mascota.getId());
    }
    
     /*
     * Elimina un registro de Mascota Encontrada que coincida con el id del paramtero.
     *   @param mascotaId: id del registro a eliminar.
     */
    public void delete(Long mascotaID)
    {
        MascotaEncontradaEntity mascota = em.find(MascotaEncontradaEntity.class, mascotaID);
        em.remove(mascota);
    }
}
