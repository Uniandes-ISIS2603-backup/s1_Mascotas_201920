/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaAdopcionLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaEncontradaLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaPerdidaLogic;
import co.edu.uniandes.csw.mascotas.ejb.MultimediaLogic;
import co.edu.uniandes.csw.mascotas.ejb.PublicidadLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaPerdidaEntity;
import co.edu.uniandes.csw.mascotas.entities.MultimediaEntity;
import co.edu.uniandes.csw.mascotas.entities.PublicidadEntity;
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
    
    @Inject
    private MascotaAdopcionLogic mAdopcionLogic;
    
    @Inject
    private MascotaEncontradaLogic mEncontradaLogic;
    
    @Inject
    private MascotaPerdidaLogic mPerdidaLogic;
    
    @Inject
    private PublicidadLogic publicidadLogic;

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
    public void createMultimediaAdopcionTest() throws BusinessLogicException {
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);
        MascotaAdopcionEntity mascota = factory.manufacturePojo(MascotaAdopcionEntity.class);
        mascota = mAdopcionLogic.createMascotaAdopcion(mascota);
        MultimediaEntity resultado = multimedaLogic.createMultimedia(mascota.getId(), null, null, null, entidad);
        Assert.assertNotNull(resultado);

        MultimediaEntity entidad2 = em.find(MultimediaEntity.class, resultado.getId());
        Assert.assertEquals(entidad2.getMascotaAdopcion(), resultado.getMascotaAdopcion());
        Assert.assertEquals(entidad2.getMascota(), resultado.getMascota());
        Assert.assertEquals(entidad2.getNombre(), resultado.getNombre());
        Assert.assertEquals(entidad2.getTipo(), resultado.getTipo());
        Assert.assertEquals(entidad2.getUrl(), resultado.getUrl());
       

    }
    
    /**
     * Test para revisar las reglas de negocio al crear una multimedia.
     * @throws BusinessLogicException 
     */
    @Test
    public void createMultimediaEncontradaTest() throws BusinessLogicException {
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);
        MascotaEncontradaEntity mascota = factory.manufacturePojo(MascotaEncontradaEntity.class);
        mascota = mEncontradaLogic.createMascotaEncontrada(mascota);
        MultimediaEntity resultado = multimedaLogic.createMultimedia(null, mascota.getId(), null, null, entidad);
        Assert.assertNotNull(resultado);

        MultimediaEntity entidad2 = em.find(MultimediaEntity.class, resultado.getId());
        Assert.assertEquals(entidad2.getMascotaEncontrada(), resultado.getMascotaEncontrada());
        Assert.assertEquals(entidad2.getNombre(), resultado.getNombre());
        Assert.assertEquals(entidad2.getTipo(), resultado.getTipo());
        Assert.assertEquals(entidad2.getUrl(), resultado.getUrl());
       

    }
    
    /**
     * Test para revisar las reglas de negocio al crear una multimedia.
     * @throws BusinessLogicException 
     */
    @Test
    public void createMultimediaPerdidaTest() throws BusinessLogicException {
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);
        MascotaPerdidaEntity mascota = factory.manufacturePojo(MascotaPerdidaEntity.class);
        mascota = mPerdidaLogic.createMascotaPerdida(mascota);
        MultimediaEntity resultado = multimedaLogic.createMultimedia(null, null, mascota.getId(), null, entidad);
        Assert.assertNotNull(resultado);

        MultimediaEntity entidad2 = em.find(MultimediaEntity.class, resultado.getId());
        Assert.assertEquals(entidad2.getMascotaPerdida(), resultado.getMascotaPerdida());
        Assert.assertEquals(entidad2.getNombre(), resultado.getNombre());
        Assert.assertEquals(entidad2.getTipo(), resultado.getTipo());
        Assert.assertEquals(entidad2.getUrl(), resultado.getUrl());
       

    }
    
    /**
     * Test para revisar las reglas de negocio al crear una multimedia.
     * @throws BusinessLogicException 
     */
    @Test
    public void createMultimediaPublicidadTest() throws BusinessLogicException {
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);
        PublicidadEntity mascota = factory.manufacturePojo(PublicidadEntity.class);
        mascota = publicidadLogic.createPublicidad(mascota);
        MultimediaEntity resultado = multimedaLogic.createMultimedia(null, null, null, mascota.getId(), entidad);
        Assert.assertNotNull(resultado);

        MultimediaEntity entidad2 = em.find(MultimediaEntity.class, resultado.getId());
        Assert.assertEquals(entidad2.getPublicidad(), resultado.getPublicidad());
        Assert.assertEquals(entidad2.getNombre(), resultado.getNombre());
        Assert.assertEquals(entidad2.getTipo(), resultado.getTipo());
        Assert.assertEquals(entidad2.getUrl(), resultado.getUrl());
       

    }
    
    /**
     * Test en el que se espera una excepcion al crear una entidad con nombre nulo
     * @throws BusinessLogicException si el test sale bien
     */
    @Test(expected = BusinessLogicException.class)
    public void createMultimediaNombreNull() throws BusinessLogicException {
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);
        entidad.setNombre(null);
        MascotaAdopcionEntity mascota = factory.manufacturePojo(MascotaAdopcionEntity.class);
        mascota = mAdopcionLogic.createMascotaAdopcion(mascota);
        MultimediaEntity entity = multimedaLogic.createMultimedia(mascota.getId(), null, null, null, entidad);

    }
     /**
     * Test en el que se espera una excepcion al crear una entidad con nombre vacio
     * @throws BusinessLogicException si el test sale bien
     */
    @Test(expected = BusinessLogicException.class)
    public void createMultimediaNombreCadenaVacia() throws BusinessLogicException {
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);
        entidad.setNombre("");
        MascotaAdopcionEntity mascota = factory.manufacturePojo(MascotaAdopcionEntity.class);
        mascota = mAdopcionLogic.createMascotaAdopcion(mascota);
        MultimediaEntity entity = multimedaLogic.createMultimedia(mascota.getId(), null, null, null, entidad);
        
    }
     /**
     * Test en el que se espera una excepcion al crear una entidad tipo nulo
     * @throws BusinessLogicException si el test sale bien
     */
    @Test(expected = BusinessLogicException.class)
    public void createMultimediaTipoNull() throws BusinessLogicException {
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);
        entidad.setTipo(null);
        MascotaAdopcionEntity mascota = factory.manufacturePojo(MascotaAdopcionEntity.class);
        mascota = mAdopcionLogic.createMascotaAdopcion(mascota);
        MultimediaEntity entity = multimedaLogic.createMultimedia(mascota.getId(), null, null, null, entidad);

    }
     /**
     * Test en el que se espera una excepcion al crear una entidad tipo nulo
     * @throws BusinessLogicException si el test sale bien
     */
    @Test(expected = BusinessLogicException.class)
    public void createMultimediaTipoCadenaVacia() throws BusinessLogicException {
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);
        entidad.setTipo("");
        MascotaAdopcionEntity mascota = factory.manufacturePojo(MascotaAdopcionEntity.class);
        mascota = mAdopcionLogic.createMascotaAdopcion(mascota);
        MultimediaEntity entity = multimedaLogic.createMultimedia(mascota.getId(), null, null, null, entidad);

    }
     /**
     * Test en el que se espera una excepcion al crear una entidad con url nula
     * @throws BusinessLogicException si el test sale bien
     */
    @Test(expected = BusinessLogicException.class)
    public void createMultimediaUrlNull() throws BusinessLogicException {
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);
        entidad.setUrl(null);
        MascotaAdopcionEntity mascota = factory.manufacturePojo(MascotaAdopcionEntity.class);
        mascota = mAdopcionLogic.createMascotaAdopcion(mascota);
        MultimediaEntity entity = multimedaLogic.createMultimedia(mascota.getId(), null, null, null, entidad);

    }
     /**
     * Test en el que se espera una excepcion al crear una entidad con url vacia
     * @throws BusinessLogicException si el test sale bien
     */
    @Test(expected = BusinessLogicException.class)
    public void createMultimediaUrlCadenaVacia() throws BusinessLogicException {
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);
        entidad.setUrl("");
        MascotaAdopcionEntity mascota = factory.manufacturePojo(MascotaAdopcionEntity.class);
        mascota = mAdopcionLogic.createMascotaAdopcion(mascota);
        MultimediaEntity entity = multimedaLogic.createMultimedia(mascota.getId(), null, null, null, entidad);

    }

    /**
     * Test en el que se verifican las reglas de la logia para el caso de actualizar
     * @throws BusinessLogicException si se incumple una regla de negocio
     */
    @Test
    public void updateMultimedia() throws BusinessLogicException {
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);
        MascotaAdopcionEntity mascota = factory.manufacturePojo(MascotaAdopcionEntity.class);
        mascota = mAdopcionLogic.createMascotaAdopcion(mascota);
        MultimediaEntity entity = multimedaLogic.createMultimedia(mascota.getId(), null, null, null, entidad);
        
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
    public void findAllAdopcionTest() throws BusinessLogicException
    {
        PodamFactory factory = new PodamFactoryImpl();

        ArrayList< MultimediaEntity> resultados = new ArrayList();

        MascotaAdopcionEntity mascota = factory.manufacturePojo(MascotaAdopcionEntity.class);
        mascota = mAdopcionLogic.createMascotaAdopcion(mascota);
        
        int j = (int) (Math.random() * ((100 - 1) + 1)) + 1;
        for (int i = 0; i <= j; i++) {
            MultimediaEntity multi = factory.manufacturePojo(MultimediaEntity.class);
            multimedaLogic.createMultimedia(mascota.getId(), null, null, null, multi);
            Assert.assertNotNull(multi);
            resultados.add(multi);
        }

        List<MultimediaEntity> r = multimedaLogic.getMultimedias(mascota.getId(), null, null, null);
        Iterator iter = resultados.iterator();

        while (iter.hasNext()) {
            MultimediaEntity next = (MultimediaEntity) iter.next();
            Assert.assertTrue(r.contains(next));
        }
    }
    
    /**
     * Test en el que se verifican las reglas de la logia para el caso de traer todas las multimedias
     * @throws BusinessLogicException si se incumple una regla de negocio
     */
   @Test
    public void findAllEncontradaTest() throws BusinessLogicException
    {
        PodamFactory factory = new PodamFactoryImpl();

        ArrayList< MultimediaEntity> resultados = new ArrayList();

        MascotaEncontradaEntity mascota = factory.manufacturePojo(MascotaEncontradaEntity.class);
        mascota = mEncontradaLogic.createMascotaEncontrada(mascota);
        
        int j = (int) (Math.random() * ((100 - 1) + 1)) + 1;
        for (int i = 0; i <= j; i++) {
            MultimediaEntity multi = factory.manufacturePojo(MultimediaEntity.class);
            multimedaLogic.createMultimedia(null, mascota.getId(), null, null, multi);
            Assert.assertNotNull(multi);
            resultados.add(multi);
        }

        List<MultimediaEntity> r = multimedaLogic.getMultimedias( null, mascota.getId(), null, null);
        Iterator iter = resultados.iterator();

        while (iter.hasNext()) {
            MultimediaEntity next = (MultimediaEntity) iter.next();
            Assert.assertTrue(r.contains(next));
        }
    }
    
    /**
     * Test en el que se verifican las reglas de la logia para el caso de traer todas las multimedias
     * @throws BusinessLogicException si se incumple una regla de negocio
     */
   @Test
    public void findAllPerdidaTest() throws BusinessLogicException
    {
        PodamFactory factory = new PodamFactoryImpl();

        ArrayList< MultimediaEntity> resultados = new ArrayList();

        MascotaPerdidaEntity mascota = factory.manufacturePojo(MascotaPerdidaEntity.class);
        mascota = mPerdidaLogic.createMascotaPerdida(mascota);
        
        int j = (int) (Math.random() * ((100 - 1) + 1)) + 1;
        for (int i = 0; i <= j; i++) {
            MultimediaEntity multi = factory.manufacturePojo(MultimediaEntity.class);
            multimedaLogic.createMultimedia(null, null, mascota.getId(), null, multi);
            Assert.assertNotNull(multi);
            resultados.add(multi);
        }

        List<MultimediaEntity> r = multimedaLogic.getMultimedias( null, null, mascota.getId(), null);
        Iterator iter = resultados.iterator();

        while (iter.hasNext()) {
            MultimediaEntity next = (MultimediaEntity) iter.next();
            Assert.assertTrue(r.contains(next));
        }
    }
    
    /**
     * Test en el que se verifican las reglas de la logia para el caso de traer todas las multimedias
     * @throws BusinessLogicException si se incumple una regla de negocio
     */
   @Test
    public void findAllPublicidadTest() throws BusinessLogicException
    {
        PodamFactory factory = new PodamFactoryImpl();

        ArrayList< MultimediaEntity> resultados = new ArrayList();

        PublicidadEntity mascota = factory.manufacturePojo(PublicidadEntity.class);
        mascota = publicidadLogic.createPublicidad(mascota);
        
        int j = (int) (Math.random() * ((100 - 1) + 1)) + 1;
        for (int i = 0; i <= j; i++) {
            MultimediaEntity multi = factory.manufacturePojo(MultimediaEntity.class);
            multimedaLogic.createMultimedia(null, null, null, mascota.getId(), multi);
            Assert.assertNotNull(multi);
            resultados.add(multi);
        }

        List<MultimediaEntity> r = multimedaLogic.getMultimedias(null, null, null, mascota.getId());
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
    public void getMultimediaTest() throws BusinessLogicException {
        
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);
        MascotaAdopcionEntity mascota = factory.manufacturePojo(MascotaAdopcionEntity.class);
        mascota = mAdopcionLogic.createMascotaAdopcion(mascota);
        MultimediaEntity entity = multimedaLogic.createMultimedia(mascota.getId(), null, null, null, entidad);
        
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
    public void deleteMultimediaTest() throws BusinessLogicException {
        
        MultimediaEntity entidad = factory.manufacturePojo(MultimediaEntity.class);
        MascotaAdopcionEntity mascota = factory.manufacturePojo(MascotaAdopcionEntity.class);
        mascota = mAdopcionLogic.createMascotaAdopcion(mascota);
        MultimediaEntity entity = multimedaLogic.createMultimedia(mascota.getId(), null, null, null, entidad);
        
        multimedaLogic.deleteMultimedia(entity.getId());
        MultimediaEntity deleted = em.find(MultimediaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
    
    
}
