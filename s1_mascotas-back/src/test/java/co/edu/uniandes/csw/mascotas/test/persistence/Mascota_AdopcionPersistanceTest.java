/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.Mascota_AdopcionEntity;
import co.edu.uniandes.csw.mascotas.persistence.Mascota_AdopcionPersistance;
//import co.edu.uniandes.csw.mascotas.persistence.Mascota_AdopcionPersistance;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Tomás Langebaek
 */
@RunWith(Arquillian.class)
public class Mascota_AdopcionPersistanceTest {

    @Inject
    private Mascota_AdopcionPersistance mp;
    @PersistenceContext
    private EntityManager em;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(Mascota_AdopcionEntity.class.getPackage())
                .addPackage(Mascota_AdopcionPersistance.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Test
    public void createMascotaTest() {
        
        PodamFactory factory = new PodamFactoryImpl();
        Mascota_AdopcionEntity newMascota_AdopcionEntity = factory.manufacturePojo(Mascota_AdopcionEntity.class);
        
        Mascota_AdopcionEntity mascota = mp.create(newMascota_AdopcionEntity);
        
       Assert.assertNotNull(mascota);
        
      Mascota_AdopcionEntity entity = em.find(Mascota_AdopcionEntity.class, mascota.getId());
        
       Assert.assertEquals(newMascota_AdopcionEntity, entity);
        
    }
}
