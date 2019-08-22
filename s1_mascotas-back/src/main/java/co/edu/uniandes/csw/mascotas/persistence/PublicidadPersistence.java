/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.PublicidadEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
