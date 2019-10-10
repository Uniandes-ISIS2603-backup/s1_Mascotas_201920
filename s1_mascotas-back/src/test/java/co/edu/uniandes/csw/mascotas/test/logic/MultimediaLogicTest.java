/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;
import co.edu.uniandes.csw.mascotas.ejb.MultimediaLogic;
import co.edu.uniandes.csw.mascotas.entities.MultimediaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MultimediaPersistence;
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
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Tomas Langebaek
 */
@RunWith(Arquillian.class)
public class MultimediaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private MultimediaLogic multimedaLogic;

    @PersistenceContext
    private EntityManager em;
    

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MultimediaEntity.class.getPackage())
                .addPackage(MultimediaLogic.class.getPackage())
                .addPackage(MultimediaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    /**
     * Test para revisar las reglas de negocio al crear una multimedia.
     * @throws BusinessLogicException 
     */
    @Test
    public void createMultimediaTest() throws BusinessLogicException {
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);

        MultimediaEntity resultado = multimedaLogic.createMultimedia(entidad);
        Assert.assertNotNull(resultado);

        MultimediaEntity entidad2 = em.find(MultimediaEntity.class, resultado.getId());
        Assert.assertEquals(entidad2.getNombre(), resultado.getNombre());
        Assert.assertEquals(entidad2.getTipo(), resultado.getTipo());
        Assert.assertEquals(entidad2.getUrl(), resultado.getUrl());
       

    }
    /**
     * Test en el que se espera una excepcion al crear una entidad con nombre nulo
     * @throws BusinessLogicException si el test sale bien
     */
    @Test(expected = BusinessLogicException.class)
    public void createMascotaAdopcionLugarNull() throws BusinessLogicException {
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);
        entidad.setNombre(null);
        MultimediaEntity resultado = multimedaLogic.createMultimedia(entidad);

    }
     /**
     * Test en el que se espera una excepcion al crear una entidad con nombre vacio
     * @throws BusinessLogicException si el test sale bien
     */
    @Test(expected = BusinessLogicException.class)
    public void createMascotaAdopcionLugarCadenaVacia() throws BusinessLogicException {
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);
        entidad.setNombre("");
        MultimediaEntity resultado = multimedaLogic.createMultimedia(entidad);

    }
     /**
     * Test en el que se espera una excepcion al crear una entidad tipo nulo
     * @throws BusinessLogicException si el test sale bien
     */
    @Test(expected = BusinessLogicException.class)
    public void createMascotaAdopcionRazaNull() throws BusinessLogicException {
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);
        entidad.setTipo(null);
        MultimediaEntity resultado = multimedaLogic.createMultimedia(entidad);

    }
     /**
     * Test en el que se espera una excepcion al crear una entidad tipo nulo
     * @throws BusinessLogicException si el test sale bien
     */
    @Test(expected = BusinessLogicException.class)
    public void createMascotaAdopcionRazaCadenaVacia() throws BusinessLogicException {
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);
        entidad.setTipo("");
        MultimediaEntity resultado = multimedaLogic.createMultimedia(entidad);

    }
     /**
     * Test en el que se espera una excepcion al crear una entidad con url nula
     * @throws BusinessLogicException si el test sale bien
     */
    @Test(expected = BusinessLogicException.class)
    public void createMascotaAdopcionEspecieNull() throws BusinessLogicException {
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);
        entidad.setUrl(null);
        MultimediaEntity resultado = multimedaLogic.createMultimedia(entidad);

    }
     /**
     * Test en el que se espera una excepcion al crear una entidad con url vacia
     * @throws BusinessLogicException si el test sale bien
     */
    @Test(expected = BusinessLogicException.class)
    public void createMascotaAdopcionDescripcionNull() throws BusinessLogicException {
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);
        entidad.setUrl("");
        MultimediaEntity resultado = multimedaLogic.createMultimedia(entidad);

    }

    /**
     * Test en el que se verifican las reglas de la logia para el caso de actualizar
     * @throws BusinessLogicException si se incumple una regla de negocio
     */
    @Test
    public void updateMascotaAdopcion() throws BusinessLogicException {
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);
        
        multimedaLogic.createMultimedia(entidad);
        MultimediaEntity entidad2 = factory.manufacturePojo(MultimediaEntity.class);
        entidad2.setId(entidad.getId());
        
        entidad2.setNombre(entidad.getNombre());
        
        multimedaLogic.updateMultimedia(entidad2);
        MultimediaEntity entidad3 = em.find(MultimediaEntity.class, entidad2.getId());

        Assert.assertEquals(entidad2.getNombre(), entidad3.getNombre());
        Assert.assertEquals(entidad2.getTipo(), entidad3.getTipo());
        Assert.assertEquals(entidad2.getUrl(), entidad3.getUrl());
        

    }
     /**
     * Test en el que se verifican las reglas de la logia para el caso de traer todas las multimedias
     * @throws BusinessLogicException si se incumple una regla de negocio
     */
   @Test
    public void findAllTest() throws BusinessLogicException
    {
        PodamFactory factory = new PodamFactoryImpl();

        ArrayList< MultimediaEntity> resultados = new ArrayList();

        int j = (int) (Math.random() * ((100 - 1) + 1)) + 1;
        for (int i = 0; i <= j; i++) {
            MultimediaEntity mascota = factory.manufacturePojo(MultimediaEntity.class);
            multimedaLogic.createMultimedia(mascota);
            Assert.assertNotNull(mascota);
            resultados.add(mascota);
        }

        List<MultimediaEntity> r = multimedaLogic.getMultimedias();
        Iterator iter = resultados.iterator();

        while (iter.hasNext()) {
            MultimediaEntity next = (MultimediaEntity) iter.next();
            Assert.assertTrue(r.contains(next));
        }
    }
     /**
     * Test en el que se verifican las reglas de la logia para el caso de busar una multimedia
     * @throws BusinessLogicException si se incumple una regla de negocio
     */
    @Test
    public void getMascotaAdopcionTest() throws BusinessLogicException {
        
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);

        MultimediaEntity entity = multimedaLogic.createMultimedia(entidad);
        
        MultimediaEntity resultEntity = multimedaLogic.getMultimedia(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getTipo(), resultEntity.getTipo());
        Assert.assertEquals(entity.getUrl(), resultEntity.getUrl());
    }
     /**
     * Test en el que se verifican las reglas de la logia para el caso de borrar una multimedia
     * @throws BusinessLogicException si se incumple una regla de negocio
     */
    @Test
    public void deleteMascotaAdopcionTest() throws BusinessLogicException {
        
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);

        MultimediaEntity entity = multimedaLogic.createMultimedia(entidad);
        
        multimedaLogic.deleteMultimedia(entity.getId());
        MultimediaEntity deleted = em.find(MultimediaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
    
    
}
