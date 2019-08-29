/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.PublicidadEntity;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import co.edu.uniandes.csw.mascotas.persistence.PublicidadPersistence;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author German Rozo
 */
@RunWith(Arquillian.class)
public class PublicidadPersistenceTest {

    @Inject
    PublicidadPersistence pp;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(PublicidadEntity.class)
                .addClass(PublicidadPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @PersistenceContext(unitName = "mascotasPU")
    protected EntityManager em;
    
    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PublicidadEntity publicidad = factory.manufacturePojo(PublicidadEntity.class);
        PublicidadEntity resultado = pp.create(publicidad);
        Assert.assertNotNull(resultado);

        PublicidadEntity entity = em.find(PublicidadEntity.class, resultado.getId());
        Assert.assertEquals(publicidad.getMensaje(),
                entity.getMensaje());

    }
    
    @Test
    public void findAllTest() {
        PodamFactory factory = new PodamFactoryImpl();
        
        ArrayList< PublicidadEntity> resultados= new ArrayList();
        
        int j =(int) (Math.random() * ((100 - 1) + 1)) + 1;
        for (int i = 0; i <= j; i++)
        {
            PublicidadEntity publicidad = factory.manufacturePojo(PublicidadEntity.class);
            PublicidadEntity resultado = pp.create(publicidad);
            Assert.assertNotNull(resultado);
            resultados.add(resultado);
        }
        
        List<PublicidadEntity> r = pp.findAll();
        Iterator iter= resultados.iterator();
        
        while (iter.hasNext()) 
        {
            PublicidadEntity next = (PublicidadEntity)iter.next();
            Assert.assertTrue(r.contains(next));
        }
    }
    
    @Test
    public void updateTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        PublicidadEntity publicidad = factory.manufacturePojo(PublicidadEntity.class);
        PublicidadEntity resultado1 = pp.create(publicidad);
        
        PublicidadEntity prueva = factory.manufacturePojo(PublicidadEntity.class);
        PublicidadEntity resultado2 = pp.create(prueva);
     
        resultado1.setId(resultado2.getId());
        pp.update(resultado1);
        Assert.assertEquals(resultado1.getId(), em.find(PublicidadEntity.class, resultado2.getId()).getId());
    }
}
