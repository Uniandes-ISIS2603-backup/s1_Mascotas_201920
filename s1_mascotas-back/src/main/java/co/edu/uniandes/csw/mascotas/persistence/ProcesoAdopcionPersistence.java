/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.persistence;

import co.edu.uniandes.csw.mascotas.entities.ProcesoAdopcionEntity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.Stateless;

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
    
}
