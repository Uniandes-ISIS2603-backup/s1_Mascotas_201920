/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.persistence;

import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import co.edu.uniandes.csw.mascotas.persistence.UsuarioPersistence;
import javax.inject.Inject;
import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Estudiante
 */
public class UsuarioPersistenceTest
{
    @Inject
    private UsuarioPersistence usuarioPrueba;
    
    @Test
    public void testCreate()
    {
        PodamFactory factory = new PodamFactoryImpl();
        UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
        UsuarioEntity resultado = usuarioPrueba.create(usuario);
        Assert.assertNotNull(resultado);
    }
}
