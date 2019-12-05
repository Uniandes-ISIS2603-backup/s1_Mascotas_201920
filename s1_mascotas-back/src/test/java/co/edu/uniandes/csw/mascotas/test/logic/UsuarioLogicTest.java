/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.UsuarioLogic;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.UsuarioPersistence;
import javax.inject.Inject;
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
 * @author Tobia Gasparoni
 */
@RunWith(Arquillian.class)
public class UsuarioLogicTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private UsuarioLogic usuarioLogic;
    
    /**
     * Tests para el metodo create()
     */
    
    @Test
    public void createUsuarioTodoBien() throws BusinessLogicException
    {
        UsuarioEntity entidad = factory.manufacturePojo(UsuarioEntity.class);
        entidad.setCorreo("tobia19991@gmail.com");
        usuarioLogic.createUsuario(entidad);
        
        Assert.assertFalse(entidad.equals(null));
        String test = "test";
        Assert.assertFalse(entidad.equals(test));
        UsuarioEntity met = new UsuarioEntity();
        met.setId(entidad.getId()+1);
        met.setNombre(entidad.getNombre());
        met.setCelular(entidad.getCelular());
        met.setCiudad(entidad.getCiudad());
        met.setCorreo(entidad.getCorreo());
        met.setMascotasAdopcion(entidad.getMascotasAdopcion());
        met.setProcesosAdopcion(entidad.getProcesosAdopcion());
        met.setMascotasEncontradas(entidad.getMascotasEncontradas());
        met.setMascotasPerdidas(entidad.getMascotasPerdidas());
        Assert.assertFalse( entidad.equals(met) );
        met.setId(entidad.getId());
        met.setNombre(met.getNombre()+"Chao");
        Assert.assertFalse( entidad.equals(met) );
        met.setNombre(entidad.getNombre());
        Assert.assertTrue(entidad.equals(met));
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createUsuarioNombreNull() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setNombre(null);
        newUsuario.setCorreo("tobia21999@gmail.com");
        usuarioLogic.createUsuario(newUsuario);
    }
    
    /*@Test (expected = BusinessLogicException.class)
    public void createUsuarioPasswordNull() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setPassword(null);
        newUsuario.setCorreo("tobia21999@gmail.com");
        usuarioLogic.createUsuario(newUsuario);
    }*/
    
    @Test (expected = BusinessLogicException.class)
    public void createUsuarioCiudadNull() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setCiudad(null);
        newUsuario.setCorreo("tobia31999@gmail.com");
        usuarioLogic.createUsuario(newUsuario);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createUsuarioCorreoNull() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setCorreo(null);
        usuarioLogic.createUsuario(newUsuario);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createUsuarioCorreoSinArroba() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setCorreo("tobia19949gmail.com");
        usuarioLogic.createUsuario(newUsuario);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createUsuarioCorreoParteIzquierdaVacia() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setCorreo("@gmail.com");
        usuarioLogic.createUsuario(newUsuario);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createUsuarioCorreoParteDerechaVacia() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setCorreo("tobia1999@");
        usuarioLogic.createUsuario(newUsuario);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createUsuarioCorreoSinPunto() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setCorreo("tobia1999@gmailcom");
        usuarioLogic.createUsuario(newUsuario);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createUsuarioCorreoPuntoIzquierdaVacia() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setCorreo("tobia1999@.com");
        usuarioLogic.createUsuario(newUsuario);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createUsuarioCorreoPuntoDerechaVacia() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setCorreo("tobia1999@gmail.");
        usuarioLogic.createUsuario(newUsuario);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createUsuarioCelularNull() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setCelular(null);
        newUsuario.setCorreo("tobia199912@gmail.com");
        usuarioLogic.createUsuario(newUsuario);
    }
    
    /**
     * Tests para el metodo update()
     */
    @Test (expected = BusinessLogicException.class)
    public void updateUsuarioNombreNull() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setCorreo("tobia199912@gmail.com");
        usuarioLogic.createUsuario(newUsuario);
        UsuarioEntity usuarioForUpdate = factory.manufacturePojo(UsuarioEntity.class);
        usuarioForUpdate.setId(newUsuario.getId());
        usuarioForUpdate.setCorreo("tobia199912@gmail.com");
        usuarioForUpdate.setNombre(null);
        usuarioLogic.updateUsuario(usuarioForUpdate);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void updateUsuarioCelularNull() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setCorreo("tobia19991221@gmail.com");
        usuarioLogic.createUsuario(newUsuario);
        UsuarioEntity usuarioForUpdate = factory.manufacturePojo(UsuarioEntity.class);
        usuarioForUpdate.setId(newUsuario.getId());
        usuarioForUpdate.setCorreo("tobia19991221@gmail.com");
        usuarioForUpdate.setCelular(null);
        usuarioLogic.updateUsuario(usuarioForUpdate);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void updateUsuarioCiudadNull() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setCorreo("tobia1999123@gmail.com");
        usuarioLogic.createUsuario(newUsuario);
        UsuarioEntity usuarioForUpdate = factory.manufacturePojo(UsuarioEntity.class);
        usuarioForUpdate.setId(newUsuario.getId());
        usuarioForUpdate.setCorreo("tobia1999123@gmail.com");
        usuarioForUpdate.setCiudad(null);
        usuarioLogic.updateUsuario(usuarioForUpdate);
    }
    
    /*@Test (expected = BusinessLogicException.class)
    public void updateUsuarioPasswordNull() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setCorreo("tobia1999123@gmail.com");
        usuarioLogic.createUsuario(newUsuario);
        UsuarioEntity usuarioForUpdate = factory.manufacturePojo(UsuarioEntity.class);
        usuarioForUpdate.setId(newUsuario.getId());
        usuarioForUpdate.setCorreo("tobia1999123@gmail.com");
        usuarioForUpdate.setPassword(null);
        usuarioLogic.updateUsuario(usuarioForUpdate);
    }*/
    
    @Test (expected = BusinessLogicException.class)
    public void updateUsuarioCorreoNull() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setCorreo("tobia1999123321@gmail.com");
        usuarioLogic.createUsuario(newUsuario);
        UsuarioEntity usuarioForUpdate = factory.manufacturePojo(UsuarioEntity.class);
        usuarioForUpdate.setId(newUsuario.getId());
        usuarioForUpdate.setCorreo(null);
        usuarioLogic.updateUsuario(usuarioForUpdate);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void updateUsuarioCorreoParteDerechaVacia() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setCorreo("tobia199912231@gmail.com");
        usuarioLogic.createUsuario(newUsuario);
        UsuarioEntity usuarioForUpdate = factory.manufacturePojo(UsuarioEntity.class);
        usuarioForUpdate.setId(newUsuario.getId());
        usuarioForUpdate.setCorreo("tobia1999@");
        usuarioLogic.updateUsuario(usuarioForUpdate);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void updateUsuarioCorreoParteIzquierdaVacia() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setCorreo("tobia1999212121@gmail.com");
        usuarioLogic.createUsuario(newUsuario);
        UsuarioEntity usuarioForUpdate = factory.manufacturePojo(UsuarioEntity.class);
        usuarioForUpdate.setId(newUsuario.getId());
        usuarioForUpdate.setCorreo("@gmail.com");
        usuarioLogic.updateUsuario(usuarioForUpdate);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void updateUsuarioCorreoPuntoDerechaVacia() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setCorreo("tobia1999121212@gmail.com");
        usuarioLogic.createUsuario(newUsuario);
        UsuarioEntity usuarioForUpdate = factory.manufacturePojo(UsuarioEntity.class);
        usuarioForUpdate.setId(newUsuario.getId());
        usuarioForUpdate.setCorreo("tobia1999@gmail.");
        usuarioLogic.updateUsuario(usuarioForUpdate);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void updateUsuarioCorreoPuntoIzquierdaVacia() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setCorreo("tobia19991232123212@gmail.com");
        usuarioLogic.createUsuario(newUsuario);
        UsuarioEntity usuarioForUpdate = factory.manufacturePojo(UsuarioEntity.class);
        usuarioForUpdate.setId(newUsuario.getId());
        usuarioForUpdate.setCorreo("tobia1999@.com");
        usuarioLogic.updateUsuario(usuarioForUpdate);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void updateUsuarioCorreoSinArroba() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setCorreo("tobia199965@gmail.com");
        usuarioLogic.createUsuario(newUsuario);
        UsuarioEntity usuarioForUpdate = factory.manufacturePojo(UsuarioEntity.class);
        usuarioForUpdate.setId(newUsuario.getId());
        usuarioForUpdate.setCorreo("tobia1999gmail.com");
        usuarioLogic.updateUsuario(usuarioForUpdate);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void updateUsuarioCorreoSinPunto() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setCorreo("tobia1999765@gmail.com");
        usuarioLogic.createUsuario(newUsuario);
        UsuarioEntity usuarioForUpdate = factory.manufacturePojo(UsuarioEntity.class);
        usuarioForUpdate.setId(newUsuario.getId());
        usuarioForUpdate.setCorreo("tobia1999@gmailcom");
        usuarioLogic.updateUsuario(usuarioForUpdate);
    }
    
     @Test
    public void testDelete()  throws BusinessLogicException
    {
         UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
         newUsuario.setCorreo("tobia19997555565@gmail.com");
         usuarioLogic.createUsuario(newUsuario);
         
        usuarioLogic.deleteUsuario(newUsuario.getId());
        UsuarioEntity usuarioQueDeberiaSerNull = usuarioLogic.findUsuario(newUsuario.getId());
        Assert.assertNull(usuarioQueDeberiaSerNull);
    }
    
     @Test
    public void testFindAllUsuario()  throws BusinessLogicException
    {
         
        Assert.assertNotNull(usuarioLogic.findAllUsuario());
    }
}