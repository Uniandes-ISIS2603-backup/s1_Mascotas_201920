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
    
    @Test (expected = BusinessLogicException.class)
    public void createUsuarioNombreNull() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setNombre(null);
        usuarioLogic.createUsuario(newUsuario);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createUsuarioCiudadNull() throws BusinessLogicException
    {
        UsuarioEntity newUsuario = factory.manufacturePojo(UsuarioEntity.class);
        newUsuario.setCiudad(null);
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
        newUsuario.setCorreo("tobia1999gmail.com");
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
        usuarioLogic.createUsuario(newUsuario);
    }
}
