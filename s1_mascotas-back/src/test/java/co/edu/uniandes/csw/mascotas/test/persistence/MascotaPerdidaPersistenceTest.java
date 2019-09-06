/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.MascotaPerdidaEntity;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPerdidaPersistence;
import java.util.List;
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
 * @author Lily 
 */
@RunWith(Arquillian.class)
public class MascotaPerdidaPersistenceTest 
{
    @Inject
    private MascotaPerdidaPersistence mascotaPrueba;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment()
    {
      return ShrinkWrap.create(JavaArchive.class)
              .addClass(MascotaPerdidaEntity.class)
              .addClass(MascotaPerdidaPersistence.class)
              .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
              .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    @Inject 
    MascotaPerdidaPersistence ma;
    
    @Test
    public void testCreate()
    {
       PodamFactory factory = new PodamFactoryImpl();
       MascotaPerdidaEntity mascota = factory.manufacturePojo(MascotaPerdidaEntity.class);
       MascotaPerdidaEntity result = ma.create(mascota);
       
       Assert.assertNotNull(result);
       
       MascotaPerdidaEntity entity =
           em.find(MascotaPerdidaEntity.class,result.getId());
        
      Assert.assertEquals(mascota,entity);
       
    }
    
    @Test
    public void testFind()
    {
       PodamFactory factory = new PodamFactoryImpl();
       MascotaPerdidaEntity mascota = factory.manufacturePojo(MascotaPerdidaEntity.class);
       MascotaPerdidaEntity result = ma.create(mascota);
       
       Assert.assertNotNull(result);
       
       MascotaPerdidaEntity entity =
           em.find(MascotaPerdidaEntity.class,result.getId());
        
       
       Assert.assertEquals(ma.find(result.getId()),result);
       
    }
    @Test
    public void testFindAll()
    {
       PodamFactory factory = new PodamFactoryImpl();
       
       
       int n= (int)(Math.random()*50);
       MascotaPerdidaEntity  mascotas [] = new MascotaPerdidaEntity[n];
       for (int i=0;i<n;++i)
       {
           mascotas[i] = factory.manufacturePojo(MascotaPerdidaEntity.class);
           ma.create(mascotas[i]);
       }
       
       List <MascotaPerdidaEntity> result = ma.findAll();
       Assert.assertEquals(result.size(),n);
       for (MascotaPerdidaEntity a: result)
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
        MascotaPerdidaEntity entity = factory.manufacturePojo(MascotaPerdidaEntity.class);
        ma.create(entity);
        ma.delete(entity.getId());

        MascotaPerdidaEntity deleted = em.find(MascotaPerdidaEntity.class, entity.getId());

        Assert.assertNull(deleted);
    }

    @Test
    public void  updateMascotaTest() {
        
        
        
        PodamFactory factory = new PodamFactoryImpl();
        MascotaPerdidaEntity entity = factory.manufacturePojo(MascotaPerdidaEntity.class);
        MascotaPerdidaEntity newEntity = factory.manufacturePojo(MascotaPerdidaEntity.class);
        ma.create(entity);
        newEntity.setId(entity.getId());

        ma.update(newEntity);

        MascotaPerdidaEntity resp = em.find(MascotaPerdidaEntity.class, entity.getId());

        Assert.assertEquals(newEntity, resp);
    }
}
