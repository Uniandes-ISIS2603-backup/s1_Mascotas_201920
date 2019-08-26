/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.Mascota_AdopcionEntity;
import java.util.ArrayList;
import java.util.List;
import co.edu.uniandes.csw.mascotas.persistence.Mascota_AdopcionPersistance;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
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

    List<Mascota_AdopcionEntity> data = new ArrayList();
    @Inject
    private Mascota_AdopcionPersistance mascotaPersistance;
    @PersistenceContext
    private EntityManager em;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(Mascota_AdopcionEntity.class.getPackage())
                .addPackage(Mascota_AdopcionPersistance.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Before
    public void insertData() {
        System.out.println(mascotaPersistance.findAll().size());
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            Mascota_AdopcionEntity newMascota_AdopcionEntity = factory.manufacturePojo(Mascota_AdopcionEntity.class);
            mascotaPersistance.create(newMascota_AdopcionEntity);
            data.add(newMascota_AdopcionEntity);
        }
    }

    @Test
    public void findAllMascotaTest() {

        List<Mascota_AdopcionEntity> lista = new ArrayList();
        lista = mascotaPersistance.findAll();
        Assert.assertEquals(data.size(), lista.size());
        for (int i = 0; i < lista.size(); i++) {
            Mascota_AdopcionEntity ent = lista.get(i);
            boolean found = false;
            for (int j = 0; j < data.size(); j++) {
                Mascota_AdopcionEntity entity = data.get(j);
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void createMascotaTest() {

        PodamFactory factory = new PodamFactoryImpl();
        Mascota_AdopcionEntity newMascota_AdopcionEntity = factory.manufacturePojo(Mascota_AdopcionEntity.class);

        Mascota_AdopcionEntity mascota = mascotaPersistance.create(newMascota_AdopcionEntity);

        Assert.assertNotNull(mascota);

        Mascota_AdopcionEntity entity = em.find(Mascota_AdopcionEntity.class, mascota.getId());

        Assert.assertEquals(newMascota_AdopcionEntity, entity);

    }

    @Test
    public void findMascotaTest() {

        Mascota_AdopcionEntity entity = data.get(0);

        Mascota_AdopcionEntity newEntity = mascotaPersistance.find(entity.getId());

        Assert.assertNotNull(newEntity);

        Assert.assertEquals(entity.getLugar(), newEntity.getLugar());
    }

    @Test
    public void deleteMascotaTest() {

        Mascota_AdopcionEntity entity = data.get(0);

        mascotaPersistance.delete(entity.getId());

        Mascota_AdopcionEntity deleted = em.find(Mascota_AdopcionEntity.class, entity.getId());

        Assert.assertNull(deleted);
    }

    @Test
    public void updateMascotaTest() {
        
        Mascota_AdopcionEntity entity = data.get(0);
        
        PodamFactory factory = new PodamFactoryImpl();
        Mascota_AdopcionEntity newEntity = factory.manufacturePojo(Mascota_AdopcionEntity.class);

        newEntity.setId(entity.getId());

        mascotaPersistance.update(newEntity);

        Mascota_AdopcionEntity resp = em.find(Mascota_AdopcionEntity.class, entity.getId());

        Assert.assertEquals(newEntity, resp);
    }

}
