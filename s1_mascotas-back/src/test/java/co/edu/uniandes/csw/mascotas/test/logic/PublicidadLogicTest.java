/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.PublicidadLogic;
import co.edu.uniandes.csw.mascotas.entities.PublicidadEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.PublicidadPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import org.junit.Assert;

/**
 *
 * @author German Rozo
 */
@RunWith(Arquillian.class)
public class PublicidadLogicTest {

   
    @PersistenceContext(unitName = "mascotasPU")
    protected EntityManager em;

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    PublicidadLogic pl;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PublicidadEntity.class.getPackage())
                .addPackage(PublicidadPersistence.class.getPackage())
                .addPackage(PublicidadLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Test
    public void createTest() throws BusinessLogicException {
        
        PublicidadEntity publicidad = factory.manufacturePojo(PublicidadEntity.class);
        PublicidadEntity resultado = pl.createPublicidad(publicidad);
        Assert.assertNotNull(resultado);
        
        PublicidadEntity entity = em.find(PublicidadEntity.class, resultado.getId());
        
        Assert.assertEquals(entity.getId(), resultado.getId());
        Assert.assertEquals(entity.getCosto(), resultado.getCosto());
        Assert.assertEquals(entity.getDiasPorSemana(), resultado.getDiasPorSemana());
        Assert.assertEquals(entity.getMensaje(), resultado.getMensaje());
        Assert.assertEquals(entity.getFechaInicio(), resultado.getFechaInicio());
        Assert.assertEquals(entity.getFecchaFin(), resultado.getFecchaFin());
    }

    @Test(expected = BusinessLogicException.class)
    public void createCostoNull() throws BusinessLogicException
    {
        PublicidadEntity publicidad = factory.manufacturePojo(PublicidadEntity.class);
        publicidad.setCosto(null);
        PublicidadEntity resultado = pl.createPublicidad(publicidad);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createDiasPorSemanaNull() throws BusinessLogicException
    {
        PublicidadEntity publicidad = factory.manufacturePojo(PublicidadEntity.class);
        publicidad.setDiasPorSemana(null);
        PublicidadEntity resultado = pl.createPublicidad(publicidad);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createFechaFinNull() throws BusinessLogicException
    {
        PublicidadEntity publicidad = factory.manufacturePojo(PublicidadEntity.class);
        publicidad.setFecchaFin(null);
        PublicidadEntity resultado = pl.createPublicidad(publicidad);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createFechaInicioNull() throws BusinessLogicException
    {
        PublicidadEntity publicidad = factory.manufacturePojo(PublicidadEntity.class);
        publicidad.setFechaInicio(null);
        PublicidadEntity resultado = pl.createPublicidad(publicidad);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createMensajeNull() throws BusinessLogicException
    {
        PublicidadEntity publicidad = factory.manufacturePojo(PublicidadEntity.class);
        publicidad.setMensaje(null);
        PublicidadEntity resultado = pl.createPublicidad(publicidad);
    }
}
