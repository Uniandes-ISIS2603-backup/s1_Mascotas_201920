/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.MascotaEncontradaLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaEncontradaPersistence;
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
import org.junit.Assert;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ja.avelino
 */
@RunWith(Arquillian.class)
public class MascotaEncontradaLogicTest {
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MascotaEncontradaEntity.class.getPackage())
                .addPackage(MascotaEncontradaLogic.class.getPackage())
                .addPackage(MascotaEncontradaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    private final PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private MascotaEncontradaLogic mel;
    
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void createMascotaAdopcion() throws BusinessLogicException{
        MascotaEncontradaEntity entidad = factory.manufacturePojo(MascotaEncontradaEntity.class);
         
        MascotaEncontradaEntity resultado = mel.createMascotaEncontrada(entidad);
        Assert.assertNotNull(resultado);
        
        MascotaEncontradaEntity entidadPrueba = em.find(MascotaEncontradaEntity.class, resultado.getId());
        Assert.assertEquals(entidadPrueba.getLugar(), resultado.getLugar());
        Assert.assertEquals(entidadPrueba.getRaza(), resultado.getRaza());
        Assert.assertEquals(entidadPrueba.getEspecie(), resultado.getEspecie());
        Assert.assertEquals(entidadPrueba.getDescripcion(), resultado.getDescripcion());
        Assert.assertEquals(entidadPrueba.getFechaEncontrada(), resultado.getFechaEncontrada());
        
        
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createMascotaEncontradaLugarNull() throws BusinessLogicException{
        MascotaEncontradaEntity entidad = factory.manufacturePojo(MascotaEncontradaEntity.class);
        entidad.setLugar(null);
        MascotaEncontradaEntity resultado = mel.createMascotaEncontrada(entidad);
 
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createMascotaEncontradaLugarCadenaVacia() throws BusinessLogicException{
        MascotaEncontradaEntity entidad = factory.manufacturePojo(MascotaEncontradaEntity.class);
        entidad.setLugar("");
        MascotaEncontradaEntity resultado = mel.createMascotaEncontrada(entidad);
 
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createMascotaEncontradaRazaNull() throws BusinessLogicException{
        MascotaEncontradaEntity entidad = factory.manufacturePojo(MascotaEncontradaEntity.class);
        entidad.setRaza(null);
        MascotaEncontradaEntity resultado = mel.createMascotaEncontrada(entidad);
 
    }
    @Test (expected = BusinessLogicException.class)
    public void createMascotaEncontradaRazaCadenaVacia() throws BusinessLogicException{
        MascotaEncontradaEntity entidad = factory.manufacturePojo(MascotaEncontradaEntity.class);
        entidad.setRaza("");
        MascotaEncontradaEntity resultado = mel.createMascotaEncontrada(entidad);
 
    }
    
     @Test (expected = BusinessLogicException.class)
    public void createMascotaEncontradaEspecieNull() throws BusinessLogicException{
        MascotaEncontradaEntity entidad = factory.manufacturePojo(MascotaEncontradaEntity.class);
        entidad.setEspecie(null);
        MascotaEncontradaEntity resultado = mel.createMascotaEncontrada(entidad);
 
    }
    
     @Test (expected = BusinessLogicException.class)
    public void createMascotaEncontradaDescripcionNull() throws BusinessLogicException{
        MascotaEncontradaEntity entidad = factory.manufacturePojo(MascotaEncontradaEntity.class);
        entidad.setDescripcion(null);
        MascotaEncontradaEntity resultado = mel.createMascotaEncontrada(entidad);
 
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createMascotaEncontradaDescripcionCadenaVacia() throws BusinessLogicException{
        MascotaEncontradaEntity entidad = factory.manufacturePojo(MascotaEncontradaEntity.class);
        entidad.setDescripcion("");
        MascotaEncontradaEntity resultado = mel.createMascotaEncontrada(entidad);
 
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createMascotaEncontradaEspecioNotAnimal() throws BusinessLogicException{
        MascotaEncontradaEntity entidad = factory.manufacturePojo(MascotaEncontradaEntity.class);
        entidad.setEspecie(-1);
        MascotaEncontradaEntity resultado = mel.createMascotaEncontrada(entidad);
 
    }
    
    @Test
    public void updateMascotaEncontrada() throws BusinessLogicException {
        MascotaEncontradaEntity entidad = factory.manufacturePojo(MascotaEncontradaEntity.class);
        
        mel.createMascotaEncontrada(entidad);
        MascotaEncontradaEntity entidad2 = factory.manufacturePojo(MascotaEncontradaEntity.class);
        entidad2.setId(entidad.getId());
        
        entidad2.setEspecie(entidad.getEspecie());
        
        mel.updateMascotaEncontrada(entidad2);
        MascotaEncontradaEntity entidad3 = em.find(MascotaEncontradaEntity.class, entidad2.getId());

        Assert.assertEquals(entidad2.getLugar(), entidad3.getLugar());
        Assert.assertEquals(entidad2.getRaza(), entidad3.getRaza());
        Assert.assertEquals(entidad2.getEspecie(), entidad3.getEspecie());
        Assert.assertEquals(entidad2.getDescripcion(), entidad3.getDescripcion());

    }
    
   @Test
    public void findAllTest() throws BusinessLogicException
    {
        PodamFactory factory = new PodamFactoryImpl();

        ArrayList< MascotaEncontradaEntity> resultados = new ArrayList<>();

        int j = (int) (Math.random() * ((100 - 1) + 1)) + 1;
        for (int i = 0; i <= j; i++) {
            MascotaEncontradaEntity mascota = factory.manufacturePojo(MascotaEncontradaEntity.class);
            mel.createMascotaEncontrada(mascota);
            Assert.assertNotNull(mascota);
            resultados.add(mascota);
        }

        List<MascotaEncontradaEntity> r = mel.findAllMascotaEncontrada();
        Iterator iter = resultados.iterator();

        while (iter.hasNext()) {
            MascotaEncontradaEntity next = (MascotaEncontradaEntity) iter.next();
            Assert.assertTrue(r.contains(next));
        }
    }
    
    @Test
    public void getMascotaEncontradaTest() throws BusinessLogicException {
        
        MascotaEncontradaEntity entidad = factory.manufacturePojo(MascotaEncontradaEntity.class);
        
        MascotaEncontradaEntity entity = mel.createMascotaEncontrada(entidad);
        
        MascotaEncontradaEntity resultEntity = mel.findMascotaEncontrada(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getLugar(), resultEntity.getLugar());
        Assert.assertEquals(entity.getRaza(), resultEntity.getRaza());
        Assert.assertEquals(entity.getEspecie(), resultEntity.getEspecie());
        Assert.assertEquals(entity.getDescripcion(), resultEntity.getDescripcion());
    }
    
    @Test
    public void deleteMascotaEncontradaTest() throws BusinessLogicException {
        
        MascotaEncontradaEntity entidad = factory.manufacturePojo(MascotaEncontradaEntity.class);

        MascotaEncontradaEntity entity = mel.createMascotaEncontrada(entidad);
        
        mel.deleteMascotaEncontrada(entity.getId());
        MascotaEncontradaEntity deleted = em.find(MascotaEncontradaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
