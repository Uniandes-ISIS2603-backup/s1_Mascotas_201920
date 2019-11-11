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

    /**
     * Arreglo datos a testear
     */
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

    /**
     * Método para poblar los datos
     */
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

    /**
     * Test buscar todas la mascotas
     */
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

    /**
     * Test crear una nueva mascota
     */
    @Test
    public void createMascotaTest() {

        PodamFactory factory = new PodamFactoryImpl();
        MascotaAdopcionEntity newMascota_AdopcionEntity = factory.manufacturePojo(MascotaAdopcionEntity.class);

        MascotaAdopcionEntity mascota = mascotaPersistance.create(newMascota_AdopcionEntity);

        Assert.assertNotNull(mascota);

        MascotaAdopcionEntity entity = em.find(MascotaAdopcionEntity.class, mascota.getId());

        Assert.assertEquals(newMascota_AdopcionEntity, entity);

        Assert.assertEquals(newMascota_AdopcionEntity.getHistoria(), entity.getHistoria());

        Assert.assertEquals(newMascota_AdopcionEntity.getLugar(), entity.getLugar());

        Assert.assertEquals(newMascota_AdopcionEntity.getEspecie(), entity.getEspecie());

        Assert.assertEquals(newMascota_AdopcionEntity.getDescripcion(), entity.getDescripcion());

        Assert.assertEquals(newMascota_AdopcionEntity.getRaza(), entity.getRaza());

        Assert.assertEquals(newMascota_AdopcionEntity.getMultimedia(), entity.getMultimedia());

        Assert.assertEquals(newMascota_AdopcionEntity.getProcesos(), entity.getProcesos());

        //
    }

    /**
     * Test buscar una mascota específica
     */
    @Test
    public void findMascotaTest() {

        MascotaAdopcionEntity entity = data.get(0);

        MascotaAdopcionEntity newEntity = mascotaPersistance.find(entity.getId());

        Assert.assertNotNull(newEntity);

        Assert.assertEquals(entity, newEntity);

        Assert.assertEquals(entity.getDescripcion(), newEntity.getDescripcion());

        Assert.assertEquals(entity.getEspecie(), newEntity.getEspecie());

        Assert.assertEquals(entity.getId(), newEntity.getId());

        Assert.assertEquals(entity.getLugar(), newEntity.getLugar());

        Assert.assertEquals(entity.getMultimedia(), newEntity.getMultimedia());

        Assert.assertEquals(entity.getProcesos(), newEntity.getProcesos());

        Assert.assertEquals(entity.getRaza(), newEntity.getRaza());

        Assert.assertEquals(entity.getUsuario(), newEntity.getUsuario());
    }

    /**
     * Test borra una mascota
     */
    @Test
    public void deleteMascotaTest() {

        MascotaAdopcionEntity entity = data.get(0);

        mascotaPersistance.delete(entity.getId());

        MascotaAdopcionEntity deleted = em.find(MascotaAdopcionEntity.class, entity.getId());

        Assert.assertNull(deleted);
    }

    /**
     * Test actualizar una mascota
     */
    @Test
    public void updateMascotaTest() {

        MascotaAdopcionEntity entity = data.get(0);
        mascotaPersistance.create(entity);

        PodamFactory factory = new PodamFactoryImpl();
        MascotaAdopcionEntity newEntity = factory.manufacturePojo(MascotaAdopcionEntity.class);

        newEntity.setId(entity.getId());

        mascotaPersistance.update(newEntity);

        MascotaAdopcionEntity resp = em.find(MascotaAdopcionEntity.class, entity.getId());

         Assert.assertEquals(newEntity, resp);

        Assert.assertEquals(newEntity.getDescripcion(), resp.getDescripcion());

        Assert.assertEquals(newEntity.getEspecie(), resp.getEspecie());

        Assert.assertEquals(newEntity.getId(), resp.getId());

        Assert.assertEquals(newEntity.getLugar(), resp.getLugar());

        Assert.assertEquals(newEntity.getMultimedia(), resp.getMultimedia());

        Assert.assertEquals(newEntity.getProcesos(), resp.getProcesos());

        Assert.assertEquals(newEntity.getRaza(), resp.getRaza());

        Assert.assertEquals(newEntity.getUsuario(), resp.getUsuario());
    }

}
