/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.RecompensaLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaPerdidaEntity;
import co.edu.uniandes.csw.mascotas.entities.ProcesoAdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.RecompensaEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPerdidaPersistence;
import co.edu.uniandes.csw.mascotas.persistence.RecompensaPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author William Smith
 */
@RunWith(Arquillian.class)
public class RecompensaLogicTest {
    
    private PodamFactory factory=new PodamFactoryImpl();
    
    @Inject
    private RecompensaLogic recompensaLogic;
    
    @PersistenceContext
    private EntityManager em;
   
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(RecompensaEntity.class.getPackage())
                .addPackage(RecompensaPersistence.class.getPackage())
                .addPackage(RecompensaLogic.class.getPackage())
                .addPackage(MascotaPerdidaEntity.class.getPackage())
                .addPackage(MascotaPerdidaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
                
    }
    
    @Test
    public void createRecompensaTest() throws BusinessLogicException{
       RecompensaEntity newEntity=factory.manufacturePojo(RecompensaEntity.class);
       newEntity.setPagado(false);
       RecompensaEntity result=recompensaLogic.createRecompensa(newEntity);
       assertNotNull(result);
       
       RecompensaEntity entity= em.find(RecompensaEntity.class, result.getId());
       assertEquals(entity.getMonto(), result.getMonto());
       assertEquals(entity.getPagado(), result.getPagado());
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createRecompensaPagadoTrueTest() throws BusinessLogicException{
       RecompensaEntity newEntity=factory.manufacturePojo(RecompensaEntity.class);
       newEntity.setPagado(true);
       RecompensaEntity result=recompensaLogic.createRecompensa(newEntity);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createRecompensaMontoNegativoTest() throws BusinessLogicException{
       RecompensaEntity newEntity=factory.manufacturePojo(RecompensaEntity.class);
       newEntity.setMonto(-1);
       newEntity.setPagado(false);
       RecompensaEntity result=recompensaLogic.createRecompensa(newEntity);
    }
    
    @Test
    public void updateRecompensaTest() throws BusinessLogicException{
       RecompensaEntity newEntity=factory.manufacturePojo(RecompensaEntity.class);
       newEntity.setPagado(false);
       recompensaLogic.createRecompensa(newEntity);
       RecompensaEntity entityToUpdate=factory.manufacturePojo(RecompensaEntity.class);
       entityToUpdate.setId(newEntity.getId());
       recompensaLogic.updateRecompensa(entityToUpdate);
       
       RecompensaEntity entity= em.find(RecompensaEntity.class, newEntity.getId());
       assertNotNull(entity);
       assertEquals(entity.getMonto(), entityToUpdate.getMonto());
       assertEquals(entity.getPagado(), entityToUpdate.getPagado());
    }
    
    @Test (expected = BusinessLogicException.class)
    public void updateRecompensaMontoNegativoTest() throws BusinessLogicException{
       RecompensaEntity newEntity=factory.manufacturePojo(RecompensaEntity.class);
   
       recompensaLogic.createRecompensa(newEntity);
       RecompensaEntity entityToUpdate=factory.manufacturePojo(RecompensaEntity.class);
       entityToUpdate.setId(newEntity.getId());
       entityToUpdate.setMonto(new Integer(-1));
       entityToUpdate.setPagado(false);
       recompensaLogic.updateRecompensa(entityToUpdate);
    }
    
    @Test
    public void findRecompensaTest() throws BusinessLogicException{
        RecompensaEntity entity=factory.manufacturePojo(RecompensaEntity.class);
        entity.setMonto(1000);
        entity.setPagado(false);
        recompensaLogic.createRecompensa(entity);
        RecompensaEntity resultEntity=recompensaLogic.findRecompensa(entity.getId());
        assertNotNull(resultEntity);
        assertEquals(entity.getId(),resultEntity.getId());
        assertEquals(entity.getMonto(),resultEntity.getMonto());
        assertEquals(entity.getPagado(),resultEntity.getPagado());
    }
    
    @Test
    public void deleteProcesoAdopcionTest() throws BusinessLogicException{
        RecompensaEntity entity=factory.manufacturePojo(RecompensaEntity.class);
        entity.setMonto(1000);
        entity.setPagado(false);
        entity=recompensaLogic.createRecompensa(entity);
        recompensaLogic.deleteRecompensa(entity.getId());
        RecompensaEntity deleted=em.find(RecompensaEntity.class,entity.getId());
        assertNull(deleted);
    }
    
    
    
    
    
    
}
