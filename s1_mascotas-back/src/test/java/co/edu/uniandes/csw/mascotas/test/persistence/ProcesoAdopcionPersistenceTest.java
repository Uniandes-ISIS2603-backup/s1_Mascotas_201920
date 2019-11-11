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
        Assert.assertEquals(newProceso_AdopcionEntity.getMascotaAdopcion(), entity.getMascotaAdopcion()); 
        
          Assert.assertEquals(newProceso_AdopcionEntity.getCalificacion(), entity.getCalificacion()); 
          
            Assert.assertEquals(newProceso_AdopcionEntity.getComentario(), entity.getComentario()); 
            
              Assert.assertEquals(newProceso_AdopcionEntity.getEstado(), entity.getEstado()); 
              
                Assert.assertEquals(newProceso_AdopcionEntity.getId(), entity.getId()); 
                
                  Assert.assertEquals(newProceso_AdopcionEntity.getMascotaAdopcion(), entity.getMascotaAdopcion()); 
                  
                    Assert.assertEquals(newProceso_AdopcionEntity.getUsuario(), entity.getUsuario()); 
        
        pp.delete(proceso.getId());
    }
    
    @Test
    public void findTest()
    {
       PodamFactory factory = new PodamFactoryImpl();
       ProcesoAdopcionEntity proceso = factory.manufacturePojo(ProcesoAdopcionEntity.class);
       ProcesoAdopcionEntity result = pp.create(proceso);
       
       Assert.assertNotNull(result);        
       
       Assert.assertEquals(pp.find(result.getId()),result);
       
       Assert.assertEquals(pp.find(result.getId()),result);
       
        Assert.assertEquals(pp.find(result.getId()).getCalificacion(),result.getCalificacion());
        
         Assert.assertEquals(pp.find(result.getId()).getComentario(),result.getComentario());
         
          Assert.assertEquals(pp.find(result.getId()).getEstado(),result.getEstado());
          
           Assert.assertEquals(pp.find(result.getId()).getId(),result.getId());
           
            Assert.assertEquals(pp.find(result.getId()).getMascotaAdopcion(),result.getMascotaAdopcion());
            
             Assert.assertEquals(pp.find(result.getId()).getUsuario(),result.getUsuario());
       
       pp.delete(result.getId());
       
    }
    
    @Test
    public void findAllTest()
    {
       PodamFactory factory = new PodamFactoryImpl();
       
       
       int n=10;
       ProcesoAdopcionEntity procesos[] = new ProcesoAdopcionEntity[n];
       for (int i=0;i<n;i++)
       {
           procesos[i] = factory.manufacturePojo(ProcesoAdopcionEntity.class);
           pp.create(procesos[i]);
       }
       
       List <ProcesoAdopcionEntity> result = pp.findAll();
       Assert.assertEquals(result.size(),procesos.length);
       for (ProcesoAdopcionEntity a: result)
       {
           boolean ya=true;
           for (int i=0;i<n && ya;++i)
           {
               if (procesos[i].equals(a))
                   ya=false;
           }
           Assert.assertFalse(ya);     
       }
        
    }
    
    @Test
    public void  updateTest() {
        
        
        
        PodamFactory factory = new PodamFactoryImpl();
        ProcesoAdopcionEntity entity = factory.manufacturePojo(ProcesoAdopcionEntity.class);
        ProcesoAdopcionEntity newEntity = factory.manufacturePojo(ProcesoAdopcionEntity.class);
        pp.create(entity);
        newEntity.setId(entity.getId());

        pp.update(newEntity);

        ProcesoAdopcionEntity resp = em.find(ProcesoAdopcionEntity.class, entity.getId());

        Assert.assertEquals(newEntity, resp);
        
        pp.delete(entity.getId());
    }
    
    @Test
    public void deleteTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ProcesoAdopcionEntity entity = factory.manufacturePojo(ProcesoAdopcionEntity.class);
        pp.create(entity);
        pp.delete(entity.getId());

        ProcesoAdopcionEntity deleted = em.find(ProcesoAdopcionEntity.class, entity.getId());

        Assert.assertNull(deleted);
    }
    
}
