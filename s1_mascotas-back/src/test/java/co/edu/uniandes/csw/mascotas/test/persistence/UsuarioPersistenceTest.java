/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import co.edu.uniandes.csw.mascotas.persistence.UsuarioPersistence;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import javax.inject.Inject;
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
 * @author Estudiante
 */
@RunWith(Arquillian.class)
public class UsuarioPersistenceTest
{
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class).addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    private UsuarioPersistence usuarioPrueba;
    
    @PersistenceContext
    private EntityManager em;
    
    @Test
    public void testCreate()
    {
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
        UsuarioEntity resultado = usuarioPrueba.create(usuario);
        Assert.assertNotNull(resultado);
        
        UsuarioEntity entity = em.find(UsuarioEntity.class, resultado.getId());
        Assert.assertEquals(usuario.getNombre(), entity.getNombre());
    }
}
