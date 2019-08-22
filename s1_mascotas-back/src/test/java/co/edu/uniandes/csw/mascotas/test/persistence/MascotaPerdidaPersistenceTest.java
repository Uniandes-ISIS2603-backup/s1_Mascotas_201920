/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.MascotaPerdidaEntity;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPerdidaPersistence;
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
}
