/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.ProcesoAdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.RecompensaEntity;
import co.edu.uniandes.csw.mascotas.persistence.RecompensaPersistence;
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
 * @author William Smith
 */
@RunWith(Arquillian.class)
public class RecompensaPersistenceTest {
    
    @Inject
    private RecompensaPersistence pp;
    @PersistenceContext
    private EntityManager em;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(RecompensaEntity.class.getPackage())
                .addPackage(RecompensaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        RecompensaEntity newRecompensaEntity = factory.manufacturePojo(RecompensaEntity.class);
        
        RecompensaEntity recompensa = pp.create(newRecompensaEntity);
       
        Assert.assertNotNull(recompensa);
        
        RecompensaEntity entity = em.find(RecompensaEntity.class, recompensa.getId());
        
        Assert.assertEquals(newRecompensaEntity, entity); 
        
        pp.delete(recompensa.getId());
    }
    
    @Test
    public void findTest()
    {
       PodamFactory factory = new PodamFactoryImpl();
       RecompensaEntity recompensa = factory.manufacturePojo(RecompensaEntity.class);
       RecompensaEntity result = pp.create(recompensa);
       
       Assert.assertNotNull(result);        
       
       Assert.assertEquals(pp.find(result.getId()),result);
       
       pp.delete(result.getId());
       
    }
    
    @Test
    public void findAllTest()
    {
       PodamFactory factory = new PodamFactoryImpl();
       
       
       int n=10;
       RecompensaEntity recompensas[] = new RecompensaEntity[n];
       for (int i=0;i<n;i++)
       {
           recompensas[i] = factory.manufacturePojo(RecompensaEntity.class);
           pp.create(recompensas[i]);
       }
       
       List <RecompensaEntity> result = pp.findAll();
       Assert.assertEquals(result.size(),recompensas.length);
       for (RecompensaEntity a: result)
       {
           boolean ya=true;
           for (int i=0;i<n && ya;++i)
           {
               if (recompensas[i].equals(a))
                   ya=false;
           }
           Assert.assertFalse(ya);     
       }
        
    }
    
    @Test
    public void  updateTest() {
        PodamFactory factory = new PodamFactoryImpl();
        RecompensaEntity entity = factory.manufacturePojo(RecompensaEntity.class);
        RecompensaEntity newEntity = factory.manufacturePojo(RecompensaEntity.class);
        pp.create(entity);
        newEntity.setId(entity.getId());

        pp.update(newEntity);

        RecompensaEntity resp = em.find(RecompensaEntity.class, entity.getId());

        Assert.assertEquals(newEntity, resp);
        
        pp.delete(entity.getId());
    }
    
    @Test
    public void deleteTest() {
        PodamFactory factory = new PodamFactoryImpl();
        RecompensaEntity entity = factory.manufacturePojo(RecompensaEntity.class);
        pp.create(entity);
        pp.delete(entity.getId());

        RecompensaEntity deleted = em.find(RecompensaEntity.class, entity.getId());

        Assert.assertNull(deleted);
    }
}
