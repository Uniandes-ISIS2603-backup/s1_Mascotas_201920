/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.ProcesoAdopcionEntity;
import co.edu.uniandes.csw.mascotas.persistence.ProcesoAdopcionPersistence;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.Assert;
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
 * @author William Smith
 */
@RunWith(Arquillian.class)
public class ProcesoAdopcionPersistenceTest {
    
    @Inject
    private ProcesoAdopcionPersistence pp;
    @PersistenceContext
    private EntityManager em;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(ProcesoAdopcionEntity.class.getPackage())
                .addPackage(ProcesoAdopcionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Test
    public void createTest() {
        
        PodamFactory factory = new PodamFactoryImpl();
        ProcesoAdopcionEntity newProceso_AdopcionEntity = factory.manufacturePojo(ProcesoAdopcionEntity.class);
        
        ProcesoAdopcionEntity proceso = pp.create(newProceso_AdopcionEntity);
       
        Assert.assertNotNull(proceso);
        
        ProcesoAdopcionEntity entity = em.find(ProcesoAdopcionEntity.class, proceso.getId());
        
        Assert.assertEquals(newProceso_AdopcionEntity, entity); 
    }
    
    @Test
    public void findTest()
    {
       PodamFactory factory = new PodamFactoryImpl();
       ProcesoAdopcionEntity proceso = factory.manufacturePojo(ProcesoAdopcionEntity.class);
       ProcesoAdopcionEntity result = pp.create(proceso);
       
       Assert.assertNotNull(result);        
       
       Assert.assertEquals(pp.find(result.getId()),result);
       
    }
    
    @Test
    public void findAllTest()
    {
       PodamFactory factory = new PodamFactoryImpl();
       
       
       int n= (int)(Math.random()*50);
       ProcesoAdopcionEntity procesos[] = new ProcesoAdopcionEntity[n];
       for (int i=0;i<n;++i)
       {
           procesos[i] = factory.manufacturePojo(ProcesoAdopcionEntity.class);
           pp.create(procesos[i]);
       }
       
       List <ProcesoAdopcionEntity> result = pp.findAll();
       Assert.assertEquals(result.size(),n);
       for (ProcesoAdopcionEntity a: result)
       {
           boolean ya=true;
           for (int i=0;i<n && ya;++i)
           {
               if (procesos[i].equals(a))
                   ya=false;
           }
           if (ya)
               Assert.assertTrue(false);
           
       }
        
    }
    
}
