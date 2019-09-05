/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import java.util.ArrayList;
import java.util.List;
import co.edu.uniandes.csw.mascotas.persistence.MascotaAdopcionPersistance;
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
public class MascotaAdopcionPersistanceTest {

    List<MascotaAdopcionEntity> data = new ArrayList();
    @Inject
    private MascotaAdopcionPersistance mascotaPersistance;
    @PersistenceContext
    private EntityManager em;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(MascotaAdopcionEntity.class.getPackage())
                .addPackage(MascotaAdopcionPersistance.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Before
    public void insertData() {
        System.out.println(mascotaPersistance.findAll().size());
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            MascotaAdopcionEntity newMascota_AdopcionEntity = factory.manufacturePojo(MascotaAdopcionEntity.class);
            mascotaPersistance.create(newMascota_AdopcionEntity);
            data.add(newMascota_AdopcionEntity);
        }
    }

    @Test
    public void findAllMascotaTest() {

        List<MascotaAdopcionEntity> lista = new ArrayList();
        lista = mascotaPersistance.findAll();
        Assert.assertEquals(data.size(), lista.size());
        for (int i = 0; i < lista.size(); i++) {
            MascotaAdopcionEntity ent = lista.get(i);
            boolean found = false;
            for (int j = 0; j < data.size(); j++) {
                MascotaAdopcionEntity entity = data.get(j);
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
        MascotaAdopcionEntity newMascota_AdopcionEntity = factory.manufacturePojo(MascotaAdopcionEntity.class);

        MascotaAdopcionEntity mascota = mascotaPersistance.create(newMascota_AdopcionEntity);

        Assert.assertNotNull(mascota);

        MascotaAdopcionEntity entity = em.find(MascotaAdopcionEntity.class, mascota.getId());

        Assert.assertEquals(newMascota_AdopcionEntity, entity);

    }

    @Test
    public void findMascotaTest() {

        MascotaAdopcionEntity entity = data.get(0);

        MascotaAdopcionEntity newEntity = mascotaPersistance.find(entity.getId());

        Assert.assertNotNull(newEntity);

        Assert.assertEquals(entity.getLugar(), newEntity.getLugar());
    }

    @Test
    public void deleteMascotaTest() {

        MascotaAdopcionEntity entity = data.get(0);

        mascotaPersistance.delete(entity.getId());

        MascotaAdopcionEntity deleted = em.find(MascotaAdopcionEntity.class, entity.getId());

        Assert.assertNull(deleted);
    }

    @Test
    public void updateMascotaTest() {
        
        MascotaAdopcionEntity entity = data.get(0);
        
        PodamFactory factory = new PodamFactoryImpl();
        MascotaAdopcionEntity newEntity = factory.manufacturePojo(MascotaAdopcionEntity.class);

        newEntity.setId(entity.getId());

        mascotaPersistance.update(newEntity);

        MascotaAdopcionEntity resp = em.find(MascotaAdopcionEntity.class, entity.getId());

        Assert.assertEquals(newEntity, resp);
    }

}
