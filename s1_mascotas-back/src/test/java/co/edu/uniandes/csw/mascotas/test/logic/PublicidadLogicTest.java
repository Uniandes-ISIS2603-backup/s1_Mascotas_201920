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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

   
    @PersistenceContext()
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
    
    @Test(expected = BusinessLogicException.class)
    public void createCostoNegativo() throws BusinessLogicException
    {
        PublicidadEntity publicidad = factory.manufacturePojo(PublicidadEntity.class);
        publicidad.setCosto(-1);
        PublicidadEntity resultado = pl.createPublicidad(publicidad);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createDiasNegativos() throws BusinessLogicException
    {
        PublicidadEntity publicidad = factory.manufacturePojo(PublicidadEntity.class);
        publicidad.setDiasPorSemana(-1);
        PublicidadEntity resultado = pl.createPublicidad(publicidad);
    }
    
    @Test
    public void updateTest() throws BusinessLogicException {
        
        PublicidadEntity publicidad = factory.manufacturePojo(PublicidadEntity.class);
        pl.createPublicidad(publicidad);
        PublicidadEntity prueva = factory.manufacturePojo(PublicidadEntity.class);
                
        publicidad.setCosto(prueva.getCosto());
        publicidad.setDiasPorSemana(prueva.getDiasPorSemana());
        publicidad.setFecchaFin(prueva.getFecchaFin());
        publicidad.setFechaInicio(prueva.getFechaInicio());
        publicidad.setMensaje(prueva.getMensaje());

        pl.updatePublicidad(publicidad);
        PublicidadEntity entity = em.find(PublicidadEntity.class, publicidad.getId());
        
        Assert.assertEquals(entity.getId(), publicidad.getId());
        Assert.assertEquals(entity.getCosto(), publicidad.getCosto());
        Assert.assertEquals(entity.getDiasPorSemana(), publicidad.getDiasPorSemana());
        Assert.assertEquals(entity.getMensaje(), publicidad.getMensaje());
        Assert.assertEquals(entity.getFechaInicio(), publicidad.getFechaInicio());
        Assert.assertEquals(entity.getFecchaFin(), publicidad.getFecchaFin());
    }

    @Test(expected = BusinessLogicException.class)
    public void updateCostoNull() throws BusinessLogicException
    {
        PublicidadEntity publicidad = factory.manufacturePojo(PublicidadEntity.class);
        publicidad.setCosto(null);
        PublicidadEntity resultado = pl.updatePublicidad(publicidad);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateDiasPorSemanaNull() throws BusinessLogicException
    {
        PublicidadEntity publicidad = factory.manufacturePojo(PublicidadEntity.class);
        publicidad.setDiasPorSemana(null);
        PublicidadEntity resultado = pl.updatePublicidad(publicidad);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateFechaFinNull() throws BusinessLogicException
    {
        PublicidadEntity publicidad = factory.manufacturePojo(PublicidadEntity.class);
        publicidad.setFecchaFin(null);
        PublicidadEntity resultado = pl.updatePublicidad(publicidad);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateFechaInicioNull() throws BusinessLogicException
    {
        PublicidadEntity publicidad = factory.manufacturePojo(PublicidadEntity.class);
        publicidad.setFechaInicio(null);
        PublicidadEntity resultado = pl.updatePublicidad(publicidad);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void updateMensajeNull() throws BusinessLogicException
    {
        PublicidadEntity publicidad = factory.manufacturePojo(PublicidadEntity.class);
        publicidad.setMensaje(null);
        PublicidadEntity resultado = pl.updatePublicidad(publicidad);
    }
   
    @Test
    public void deleteTest() throws BusinessLogicException
    {
        PodamFactory factory = new PodamFactoryImpl();
        PublicidadEntity p = factory.manufacturePojo(PublicidadEntity.class);
        pl.createPublicidad(p);
        pl.deletePublicidad(p.getId());

        Assert.assertNull(em.find(PublicidadEntity.class,p.getId()));
    }
    
    @Test
    public void findAllTest() throws BusinessLogicException
    {
        PodamFactory factory = new PodamFactoryImpl();

        ArrayList< PublicidadEntity> resultados = new ArrayList();

        int j = (int) (Math.random() * ((100 - 1) + 1)) + 1;
        for (int i = 0; i <= j; i++) {
            PublicidadEntity publicidad = factory.manufacturePojo(PublicidadEntity.class);
            pl.createPublicidad(publicidad);
            Assert.assertNotNull(publicidad);
            resultados.add(publicidad);
        }

        List<PublicidadEntity> r = pl.findAllPublicidad();
        Iterator iter = resultados.iterator();

        while (iter.hasNext()) {
            PublicidadEntity next = (PublicidadEntity) iter.next();
            Assert.assertTrue(r.contains(next));
        }
    }
    
    @Test
    public void findTest() throws BusinessLogicException
    {
        PodamFactory factory = new PodamFactoryImpl();

        PublicidadEntity publicidad = factory.manufacturePojo(PublicidadEntity.class);
        pl.createPublicidad(publicidad);
        Assert.assertNotNull(publicidad);
        PublicidadEntity r = pl.findPublicidad(publicidad.getId());
        Assert.assertNotNull(r);
    }
}
