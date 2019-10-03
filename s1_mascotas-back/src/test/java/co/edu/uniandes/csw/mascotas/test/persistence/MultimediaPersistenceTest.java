/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.MultimediaEntity;
import co.edu.uniandes.csw.mascotas.persistence.MultimediaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Tomas Langebaek
 */
@RunWith(Arquillian.class)
public class MultimediaPersistenceTest {
    
     List<MultimediaEntity> data = new ArrayList();
    @Inject
    private MultimediaPersistence pp;
    @PersistenceContext
    private EntityManager em;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(MultimediaEntity.class.getPackage())
                .addPackage(MultimediaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    
    public void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            MultimediaEntity newMultimediaEntity = factory.manufacturePojo(MultimediaEntity.class);
            pp.create(newMultimediaEntity);
            data.add(newMultimediaEntity);
        }
    }
    
        @Test
    public void findAllTest()
    {
   
    List<MultimediaEntity> lista = new ArrayList();
    data.clear();
    insertData();
        lista = pp.findAll();
        junit.framework.Assert.assertEquals(data.size(), lista.size());
        for (int i = 0; i < lista.size(); i++) {
            MultimediaEntity ent = lista.get(i);
            boolean found = false;
            for (int j = 0; j < data.size(); j++) {
                MultimediaEntity entity = data.get(j);
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            junit.framework.Assert.assertTrue(found);
        }     
        
    }
    
     @Test
    public void createTest() {
        
        PodamFactory factory = new PodamFactoryImpl();
        MultimediaEntity newMultimediaEntity = factory.manufacturePojo(MultimediaEntity.class);
        
        MultimediaEntity multimedia = pp.create(newMultimediaEntity);
       
        Assert.assertNotNull(multimedia);
        
        MultimediaEntity entity = em.find(MultimediaEntity.class, multimedia.getId());
        
        Assert.assertEquals(newMultimediaEntity, entity); 
        
        pp.delete(multimedia.getId());
    }
    
    @Test
    public void findTest()
    {
        
       PodamFactory factory = new PodamFactoryImpl();
       MultimediaEntity proceso = factory.manufacturePojo(MultimediaEntity.class);
       MultimediaEntity result = pp.create(proceso);
       
       Assert.assertNotNull(result);        
       
       Assert.assertEquals(pp.find(result.getId()),result);
       
       pp.delete(result.getId());
       
    }
    

    
    @Test
    public void  updateTest() {
        
        PodamFactory factory = new PodamFactoryImpl();
        MultimediaEntity entity = factory.manufacturePojo(MultimediaEntity.class);
        MultimediaEntity newEntity = factory.manufacturePojo(MultimediaEntity.class);
        pp.create(entity);
        newEntity.setId(entity.getId());

        pp.update(newEntity);

        MultimediaEntity resp = em.find(MultimediaEntity.class, entity.getId());

        Assert.assertEquals(newEntity, resp);
        
        pp.delete(entity.getId());
    }
    
    @Test
    public void deleteTest() {
        PodamFactory factory = new PodamFactoryImpl();
        MultimediaEntity entity = factory.manufacturePojo(MultimediaEntity.class);
        pp.create(entity);
        pp.delete(entity.getId());

        MultimediaEntity deleted = em.find(MultimediaEntity.class, entity.getId());

        Assert.assertNull(deleted);
    }
    
    
}
