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
        
        if (((Math.random()*10)%2)==1)
            entidad.setEspecie("Gato");
        else
            entidad.setEspecie("Perro");
         
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
    public void createMascotaAdopcionLugarNull() throws BusinessLogicException{
        MascotaEncontradaEntity entidad = factory.manufacturePojo(MascotaEncontradaEntity.class);
        entidad.setLugar(null);
        MascotaEncontradaEntity resultado = mel.createMascotaEncontrada(entidad);
 
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createMascotaAdopcionLugarCadenaVacia() throws BusinessLogicException{
        MascotaEncontradaEntity entidad = factory.manufacturePojo(MascotaEncontradaEntity.class);
        entidad.setLugar("");
        MascotaEncontradaEntity resultado = mel.createMascotaEncontrada(entidad);
 
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createMascotaAdopcionRazaNull() throws BusinessLogicException{
        MascotaEncontradaEntity entidad = factory.manufacturePojo(MascotaEncontradaEntity.class);
        entidad.setRaza(null);
        MascotaEncontradaEntity resultado = mel.createMascotaEncontrada(entidad);
 
    }
    @Test (expected = BusinessLogicException.class)
    public void createMascotaAdopcionRazaCadenaVacia() throws BusinessLogicException{
        MascotaEncontradaEntity entidad = factory.manufacturePojo(MascotaEncontradaEntity.class);
        entidad.setRaza("");
        MascotaEncontradaEntity resultado = mel.createMascotaEncontrada(entidad);
 
    }
    
     @Test (expected = BusinessLogicException.class)
    public void createMascotaAdopcionEspecieNull() throws BusinessLogicException{
        MascotaEncontradaEntity entidad = factory.manufacturePojo(MascotaEncontradaEntity.class);
        entidad.setEspecie(null);
        MascotaEncontradaEntity resultado = mel.createMascotaEncontrada(entidad);
 
    }
    
     @Test (expected = BusinessLogicException.class)
    public void createMascotaAdopcionDescripcionNull() throws BusinessLogicException{
        MascotaEncontradaEntity entidad = factory.manufacturePojo(MascotaEncontradaEntity.class);
        entidad.setDescripcion(null);
        MascotaEncontradaEntity resultado = mel.createMascotaEncontrada(entidad);
 
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createMascotaAdopcionDescripcionCadenaVacia() throws BusinessLogicException{
        MascotaEncontradaEntity entidad = factory.manufacturePojo(MascotaEncontradaEntity.class);
        entidad.setDescripcion("");
        MascotaEncontradaEntity resultado = mel.createMascotaEncontrada(entidad);
 
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createMascotaAdopcionEspecioNotAnimal() throws BusinessLogicException{
        MascotaEncontradaEntity entidad = factory.manufacturePojo(MascotaEncontradaEntity.class);
        entidad.setEspecie("Loro");
        MascotaEncontradaEntity resultado = mel.createMascotaEncontrada(entidad);
 
    }
}
