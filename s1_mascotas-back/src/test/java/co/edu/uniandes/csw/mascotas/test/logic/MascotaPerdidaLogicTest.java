/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.entities.MascotaPerdidaEntity;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPerdidaPersistence;
import co.edu.uniandes.csw.mascotas.ejb.MascotaPerdidaLogic;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import javax.inject.Inject;
import junit.framework.Assert;
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
 * @author Lily
 */
@RunWith(Arquillian.class)
public class MascotaPerdidaLogicTest {
    
    private PodamFactory fac = new PodamFactoryImpl();
    
    @Inject
    private MascotaPerdidaLogic mascotaLogic;
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MascotaPerdidaEntity.class.getPackage())
                .addPackage(MascotaPerdidaLogic.class.getPackage())
                .addPackage(MascotaPerdidaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Test 
    public void createMascotaPerdida ( ) throws BusinessLogicException
    {
        MascotaPerdidaEntity newEntity = fac.manufacturePojo(MascotaPerdidaEntity.class);
        if (((Math.random()*10)%2)==1)
            newEntity.setEspecie("Gato");
        else
            newEntity.setEspecie("Perro");
        MascotaPerdidaEntity result = mascotaLogic.createMascotaPerdida(newEntity);
        Assert.assertNotNull(result);
        
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createMascotaPerdidaRazaNull () throws BusinessLogicException
    {
        MascotaPerdidaEntity newEntity = fac.manufacturePojo(MascotaPerdidaEntity.class);
        if (((Math.random()*10)%2)==1)
            newEntity.setEspecie("Gato");
        else
            newEntity.setEspecie("Perro");
        newEntity.setRaza(null);
        MascotaPerdidaEntity result = mascotaLogic.createMascotaPerdida(newEntity);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createMascotaPerdidaRazaCadenaVacia () throws BusinessLogicException
    {
        MascotaPerdidaEntity newEntity = fac.manufacturePojo(MascotaPerdidaEntity.class);
        if (((Math.random()*10)%2)==1)
            newEntity.setEspecie("Gato");
        else
            newEntity.setEspecie("Perro");
        newEntity.setRaza("");
        MascotaPerdidaEntity result = mascotaLogic.createMascotaPerdida(newEntity);
    }

    @Test (expected = BusinessLogicException.class)
    public void createMascotaPerdidaEspecieDifetenteGatoPerro () throws BusinessLogicException
    {
        MascotaPerdidaEntity newEntity = fac.manufacturePojo(MascotaPerdidaEntity.class);
        while (newEntity.getEspecie().equals("Perro") || newEntity.getEspecie().equals("Gato"))
        {
             newEntity = fac.manufacturePojo(MascotaPerdidaEntity.class);
        }
        MascotaPerdidaEntity result = mascotaLogic.createMascotaPerdida(newEntity);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createMascotaPerdidaEspecieNull () throws BusinessLogicException
    {
        MascotaPerdidaEntity newEntity = fac.manufacturePojo(MascotaPerdidaEntity.class);
        
        newEntity.setEspecie(null);
        MascotaPerdidaEntity result = mascotaLogic.createMascotaPerdida(newEntity);
    }
    @Test (expected = BusinessLogicException.class)
    public void createMascotaPerdidaLugarNull () throws BusinessLogicException
    {
        MascotaPerdidaEntity newEntity = fac.manufacturePojo(MascotaPerdidaEntity.class);
        if (((Math.random()*10)%2)==1)
            newEntity.setEspecie("Gato");
        else
            newEntity.setEspecie("Perro");
        newEntity.setLugar(null);
        MascotaPerdidaEntity result = mascotaLogic.createMascotaPerdida(newEntity);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createMascotaPerdidaLugarCadenaVacia () throws BusinessLogicException
    {
        MascotaPerdidaEntity newEntity = fac.manufacturePojo(MascotaPerdidaEntity.class);
        if (((Math.random()*10)%2)==1)
            newEntity.setEspecie("Gato");
        else
            newEntity.setEspecie("Perro");
        newEntity.setLugar("");
        MascotaPerdidaEntity result = mascotaLogic.createMascotaPerdida(newEntity);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createMascotaPerdidaDescripcionNull () throws BusinessLogicException
    {
        MascotaPerdidaEntity newEntity = fac.manufacturePojo(MascotaPerdidaEntity.class);
        if (((Math.random()*10)%2)==1)
            newEntity.setEspecie("Gato");
        else
            newEntity.setEspecie("Perro");
        newEntity.setDescripcion(null);
        MascotaPerdidaEntity result = mascotaLogic.createMascotaPerdida(newEntity);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createMascotaPerdidaDescripcionCadenaVacia () throws BusinessLogicException
    {
        MascotaPerdidaEntity newEntity = fac.manufacturePojo(MascotaPerdidaEntity.class);
        if (((Math.random()*10)%2)==1)
            newEntity.setEspecie("Gato");
        else
            newEntity.setEspecie("Perro");
        newEntity.setDescripcion("");
        MascotaPerdidaEntity result = mascotaLogic.createMascotaPerdida(newEntity);
    }
   
}
