/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;
import co.edu.uniandes.csw.mascotas.persistence.MascotaEncontradaPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Estudiante
 */
@RunWith(Arquillian.class)
public class MascotaEncontradaPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(MascotaEncontradaEntity.class)
                .addClass(MascotaEncontradaPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private MascotaEncontradaPersistence mep;
    
    @PersistenceContext(unitName = "mascotasPU")
    private EntityManager em;
    
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        MascotaEncontradaEntity mascota = factory.manufacturePojo(MascotaEncontradaEntity.class);
        MascotaEncontradaEntity result = mep.create(mascota);
        Assert.assertNotNull(result);
        
        MascotaEncontradaEntity entity = em.find(MascotaEncontradaEntity.class, result.getId());
        
        Assert.assertEquals(mascota.getDescripcion(), entity.getDescripcion());
    }
    
}
