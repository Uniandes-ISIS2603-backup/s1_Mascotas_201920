/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.MascotaAdopcionLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaAdopcionPersistance;
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
 * @author Estudiante
 */
@RunWith(Arquillian.class)
public class MascotaAdopcionLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private MascotaAdopcionLogic mascotaLogic;
    
    @PersistenceContext
    private EntityManager em;
    
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MascotaAdopcionEntity.class.getPackage())
                .addPackage(MascotaAdopcionLogic.class.getPackage())
                .addPackage(MascotaAdopcionPersistance.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
 
    @Test
    public void createMascotaAdopcion() throws BusinessLogicException{
        MascotaAdopcionEntity entidad = factory.manufacturePojo(MascotaAdopcionEntity.class);
        MascotaAdopcionEntity resultado = mascotaLogic.createMascotaAdopcion(entidad);
        Assert.assertNotNull(resultado);
        
        MascotaAdopcionEntity entidad2 = em.find(MascotaAdopcionEntity.class, resultado.getId());
        Assert.assertEquals(entidad.getLugar(), resultado.getLugar());
        
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createMascotaAdopcionLugarNull() throws BusinessLogicException{
        MascotaAdopcionEntity entidad = factory.manufacturePojo(MascotaAdopcionEntity.class);
        entidad.setLugar(null);
        MascotaAdopcionEntity resultado = mascotaLogic.createMascotaAdopcion(entidad);
 
    }
    
}
