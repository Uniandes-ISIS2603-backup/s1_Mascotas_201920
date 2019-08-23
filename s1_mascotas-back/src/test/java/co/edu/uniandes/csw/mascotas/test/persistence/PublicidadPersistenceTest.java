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
}
