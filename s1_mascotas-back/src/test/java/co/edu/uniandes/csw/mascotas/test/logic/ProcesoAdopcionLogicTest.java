/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.ProcesoAdopcionLogic;
import co.edu.uniandes.csw.mascotas.entities.ProcesoAdopcionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.ProcesoAdopcionPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author William Smith
 */
@RunWith(Arquillian.class)
public class ProcesoAdopcionLogicTest {
    
    private PodamFactory factory=new PodamFactoryImpl();
    
    @Inject
    private ProcesoAdopcionLogic procesoLogic;
    
    @PersistenceContext
    private EntityManager em;
   
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProcesoAdopcionEntity.class.getPackage())
                .addPackage(ProcesoAdopcionPersistence.class.getPackage())
                .addPackage(ProcesoAdopcionLogic.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
                
    }
    
    @Test
    public void createProcesoTest() throws BusinessLogicException{
       ProcesoAdopcionEntity newEntity=factory.manufacturePojo(ProcesoAdopcionEntity.class);
       ProcesoAdopcionEntity result=procesoLogic.createProcesoAdopcion(newEntity);
       assertNotNull(result);
       
       ProcesoAdopcionEntity entity= em.find(ProcesoAdopcionEntity.class, result.getId());
       assertEquals(entity.getEstado(), result.getEstado());
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createProcesoEstadoNullTest() throws BusinessLogicException{
        ProcesoAdopcionEntity newEntity = factory.manufacturePojo(ProcesoAdopcionEntity.class);
        newEntity.setEstado(null);
        ProcesoAdopcionEntity result = procesoLogic.createProcesoAdopcion(newEntity);
    }
    
    
}
