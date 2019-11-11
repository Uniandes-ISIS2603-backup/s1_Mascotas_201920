/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;
import co.edu.uniandes.csw.mascotas.persistence.MascotaEncontradaPersistence;
import java.util.List;
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
                .addPackage(MascotaEncontradaEntity.class.getPackage())
                .addPackage(MascotaEncontradaPersistence.class.getPackage())
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
        Assert.assertEquals(mascota.getEspecie(), entity.getEspecie());
        Assert.assertEquals(mascota.getFechaEncontrada(), entity.getFechaEncontrada());
        Assert.assertEquals(mascota.getLugar(), entity.getLugar());
        Assert.assertEquals(mascota.getRaza(), entity.getRaza());
        Assert.assertEquals(mascota.getMultimedia(), entity.getMultimedia());
     
        
    }
    
    @Test
    public void testFind()
    {
       PodamFactory factory = new PodamFactoryImpl();
       MascotaEncontradaEntity mascota = factory.manufacturePojo(MascotaEncontradaEntity.class);
       MascotaEncontradaEntity result = mep.create(mascota);
       
       Assert.assertNotNull(result);     
       
       Assert.assertEquals(mep.find(result.getId()),result);
       
    }
    @Test
    public void testFindAll()
    {
       PodamFactory factory = new PodamFactoryImpl();
       
        
       int n= (int)(Math.random()*20);
       MascotaEncontradaEntity  mascotas [] = new MascotaEncontradaEntity[n];
       for (int i=0;i<n;++i)
       {
           mascotas[i] = factory.manufacturePojo(MascotaEncontradaEntity.class);
           mep.create(mascotas[i]);
       }
       
        List <MascotaEncontradaEntity> result = mep.findAll();
       Assert.assertEquals(result.size(),n);
       for (MascotaEncontradaEntity a: result)
       {
           boolean ya=true;
           for (int i=0;i<n && ya;++i)
           {
               if (mascotas[i].equals(a))
                   ya=false;
           }
           if (ya)
               Assert.assertTrue(false);
           
       }
        
    }
    
    @Test
    public void deleteMascotaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        MascotaEncontradaEntity entity = factory.manufacturePojo(MascotaEncontradaEntity.class);
        mep.create(entity);
        mep.delete(entity.getId());

        MascotaEncontradaEntity deleted = em.find(MascotaEncontradaEntity.class, entity.getId());

        Assert.assertNull(deleted);
    }

    @Test
    public void  updateMascotaTest() {
        
        
        
        PodamFactory factory = new PodamFactoryImpl();
        MascotaEncontradaEntity entity = factory.manufacturePojo(MascotaEncontradaEntity.class);
        MascotaEncontradaEntity newEntity = factory.manufacturePojo(MascotaEncontradaEntity.class);
        mep.create(entity);
        newEntity.setId(entity.getId());

        mep.update(newEntity);

        MascotaEncontradaEntity resp = em.find(MascotaEncontradaEntity.class, entity.getId());

        Assert.assertEquals(newEntity, resp);
    }
}
