/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import co.edu.uniandes.csw.mascotas.persistence.UsuarioPersistence;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import javax.inject.Inject;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Tobia Gasparoni
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
    
    ArrayList<UsuarioEntity> usuarios;
    
    @Inject
    private UsuarioPersistence usuarioPrueba;
    
    @PersistenceContext
    private EntityManager em;
    
    @Before
    public void prepararDatos()
    {
        PodamFactory factory = new PodamFactoryImpl();
        usuarios = new ArrayList<>();
        
        for(int i = 0; i < (new Random()).nextInt((50-10) + 1) + 10; i++)
        {
            UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
            UsuarioEntity resultado = usuarioPrueba.create(usuario);
            Assert.assertNotNull(resultado);
            usuarios.add(resultado);
        }
    }
    
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
    
    @Test
    public void testFindAll()
    {        
        List<UsuarioEntity> segundaLista = (List) usuarioPrueba.findAll();
        Iterator it = usuarios.iterator();
        while(it.hasNext())
        {
            UsuarioEntity next = (UsuarioEntity) it.next();
            Assert.assertTrue(segundaLista.contains(next));
        }
    }
    
    @Test
    public void testFind()
    {
        UsuarioEntity usuarioLista = usuarios.get(0);
        Assert.assertNotNull(usuarioLista);
        
        UsuarioEntity usuarioPersistencia = usuarioPrueba.find(usuarioLista.getId());
        Assert.assertNotNull(usuarioPersistencia);
        
        Assert.assertEquals(usuarioPersistencia, usuarioLista);
        
    }
    
    @Test
    public void testFindByEmail()
    {
        UsuarioEntity usuarioLista = usuarios.get(0);
        Assert.assertNotNull(usuarioLista);
        
        LinkedList<UsuarioEntity> usuariosEncontrado = usuarioPrueba.findByEmail(usuarioLista.getCorreo());
        Assert.assertNotNull(usuariosEncontrado);
        
        Assert.assertEquals(1, usuariosEncontrado.size());
        Assert.assertEquals(usuarioLista, usuariosEncontrado.get(0));
        
    }
    
    @Test
    public void testDelete()
    {
        UsuarioEntity usuarioLista = usuarios.get(0);
        usuarios.remove(usuarioLista);
        usuarioPrueba.delete(usuarioLista.getId());
        UsuarioEntity usuarioQueDeberiaSerNull = usuarioPrueba.find(usuarioLista.getId());
        Assert.assertNull(usuarioQueDeberiaSerNull);
    }
    
    @Test
    public void testUpdate()
    {
        UsuarioEntity usuarioLista = usuarios.get(0);
        usuarioLista.setCorreo("tr.gasparoni@uniandes.edu.co");
        usuarioLista.setCelular(5743868);
        usuarioLista.setCiudad("Barranquilla");
        
        usuarioPrueba.update(usuarioLista);
        
        UsuarioEntity usuarioActualizado = usuarioPrueba.find(usuarioLista.getId());
        Assert.assertEquals(usuarioLista.getCorreo(), usuarioActualizado.getCorreo());
        Assert.assertEquals(usuarioLista.getCelular(), usuarioActualizado.getCelular());
        Assert.assertEquals(usuarioLista.getCiudad(), usuarioActualizado.getCiudad());
        
    }
}