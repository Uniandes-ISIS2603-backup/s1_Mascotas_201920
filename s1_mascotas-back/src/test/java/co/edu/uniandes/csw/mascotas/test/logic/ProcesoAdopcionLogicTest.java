/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.ProcesoAdopcionLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.ProcesoAdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.MascotaAdopcionPersistance;
import co.edu.uniandes.csw.mascotas.persistence.ProcesoAdopcionPersistence;
import co.edu.uniandes.csw.mascotas.persistence.UsuarioPersistence;
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
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(MascotaAdopcionEntity.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addPackage(MascotaAdopcionPersistance.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
                
    }
    
    @Test
    public void createProcesoCanceladoTest() throws BusinessLogicException{
       ProcesoAdopcionEntity newEntity=factory.manufacturePojo(ProcesoAdopcionEntity.class);
       newEntity.setEstado("Cancelado");
    
       ProcesoAdopcionEntity result=procesoLogic.createProcesoAdopcion(newEntity);
       assertNotNull(result);
       
       ProcesoAdopcionEntity entity= em.find(ProcesoAdopcionEntity.class, result.getId());
       assertEquals(entity.getEstado(), result.getEstado());
       assertEquals(entity.getCalificacion(), result.getCalificacion());
       assertEquals(entity.getComentario(), result.getComentario());
       assertEquals(entity.getUsuario(), result.getUsuario());
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createProcesoEstadoNullTest() throws BusinessLogicException{
        ProcesoAdopcionEntity newEntity = factory.manufacturePojo(ProcesoAdopcionEntity.class);
        newEntity.setEstado(null);
        ProcesoAdopcionEntity result = procesoLogic.createProcesoAdopcion(newEntity);
    }
    
     @Test (expected = BusinessLogicException.class)
    public void createProcesoEstadoInvalidoTest() throws BusinessLogicException{
        ProcesoAdopcionEntity newEntity = factory.manufacturePojo(ProcesoAdopcionEntity.class);
        newEntity.setEstado("Invalido");
        ProcesoAdopcionEntity result = procesoLogic.createProcesoAdopcion(newEntity);
    }
    
     @Test (expected = BusinessLogicException.class)
    public void createProcesoComentarioNullTest() throws BusinessLogicException{
        ProcesoAdopcionEntity newEntity = factory.manufacturePojo(ProcesoAdopcionEntity.class);
        newEntity.setEstado("Cancelado");
        newEntity.setComentario(null);
        ProcesoAdopcionEntity result = procesoLogic.createProcesoAdopcion(newEntity);
    }
     @Test (expected = BusinessLogicException.class)
    public void createProcesoCalificacionCeroTest() throws BusinessLogicException{
        ProcesoAdopcionEntity newEntity = factory.manufacturePojo(ProcesoAdopcionEntity.class);
        newEntity.setEstado("Cancelado");
        newEntity.setCalificacion(0);
        ProcesoAdopcionEntity result = procesoLogic.createProcesoAdopcion(newEntity);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createProcesoCalificacionSeisTest() throws BusinessLogicException{
        ProcesoAdopcionEntity newEntity = factory.manufacturePojo(ProcesoAdopcionEntity.class);
        newEntity.setEstado("Cancelado");
        newEntity.setCalificacion(6);
        ProcesoAdopcionEntity result = procesoLogic.createProcesoAdopcion(newEntity);
    }
    
    @Test
    public void updateProcesoTest() throws BusinessLogicException{
       ProcesoAdopcionEntity newEntity=factory.manufacturePojo(ProcesoAdopcionEntity.class);
       newEntity.setEstado("Cancelado");
       procesoLogic.createProcesoAdopcion(newEntity);
       ProcesoAdopcionEntity entityToUpdate=factory.manufacturePojo(ProcesoAdopcionEntity.class);
       entityToUpdate.setId(newEntity.getId());
       entityToUpdate.setEstado("Cancelado");
    
       procesoLogic.updateProcesoAdopcion(entityToUpdate);
       
       ProcesoAdopcionEntity entity= em.find(ProcesoAdopcionEntity.class, newEntity.getId());
       assertNotNull(entity);
       assertEquals(entity.getComentario(), entityToUpdate.getComentario());
    }
    @Test
    public void updateProcesoTerminadoTest() throws BusinessLogicException{
       ProcesoAdopcionEntity newEntity=factory.manufacturePojo(ProcesoAdopcionEntity.class);
       newEntity.setEstado("Cancelado");
       procesoLogic.createProcesoAdopcion(newEntity);
       ProcesoAdopcionEntity entityToUpdate=factory.manufacturePojo(ProcesoAdopcionEntity.class);
       entityToUpdate.setId(newEntity.getId());
       entityToUpdate.setEstado("Terminado");
    
       procesoLogic.updateProcesoAdopcion(entityToUpdate);
       
       ProcesoAdopcionEntity entity= em.find(ProcesoAdopcionEntity.class, newEntity.getId());
       assertNotNull(entity);
       assertEquals(entity.getComentario(), entityToUpdate.getComentario());
    }
    @Test
    public void updateProcesoEnProcesoTest() throws BusinessLogicException{
       ProcesoAdopcionEntity newEntity=factory.manufacturePojo(ProcesoAdopcionEntity.class);
       newEntity.setEstado("Cancelado");
       procesoLogic.createProcesoAdopcion(newEntity);
       ProcesoAdopcionEntity entityToUpdate=factory.manufacturePojo(ProcesoAdopcionEntity.class);
       entityToUpdate.setId(newEntity.getId());
       entityToUpdate.setEstado("En Proceso");
    
       procesoLogic.updateProcesoAdopcion(entityToUpdate);
       
       ProcesoAdopcionEntity entity= em.find(ProcesoAdopcionEntity.class, newEntity.getId());
       assertNotNull(entity);
       assertEquals(entity.getComentario(), entityToUpdate.getComentario());
    }
    @Test (expected = BusinessLogicException.class)
    public void updateProcesoEstadoNullTest() throws BusinessLogicException{
        ProcesoAdopcionEntity newEntity=factory.manufacturePojo(ProcesoAdopcionEntity.class);
       newEntity.setEstado("Cancelado");
       procesoLogic.createProcesoAdopcion(newEntity);
       ProcesoAdopcionEntity entityToUpdate=factory.manufacturePojo(ProcesoAdopcionEntity.class);
       entityToUpdate.setId(newEntity.getId());
       entityToUpdate.setEstado(null);
       procesoLogic.updateProcesoAdopcion(entityToUpdate);
    }
    
     @Test (expected = BusinessLogicException.class)
    public void updateProcesoEstadoInvalidoTest() throws BusinessLogicException{
        ProcesoAdopcionEntity newEntity=factory.manufacturePojo(ProcesoAdopcionEntity.class);
       newEntity.setEstado("Cancelado");
       procesoLogic.createProcesoAdopcion(newEntity);
       ProcesoAdopcionEntity entityToUpdate=factory.manufacturePojo(ProcesoAdopcionEntity.class);
       entityToUpdate.setId(newEntity.getId());
       entityToUpdate.setEstado("Invalido");
       procesoLogic.updateProcesoAdopcion(entityToUpdate);
    }
    
     @Test (expected = BusinessLogicException.class)
    public void updateProcesoComentarioNullTest() throws BusinessLogicException{
        ProcesoAdopcionEntity newEntity=factory.manufacturePojo(ProcesoAdopcionEntity.class);
       newEntity.setEstado("Cancelado");
       procesoLogic.createProcesoAdopcion(newEntity);
       ProcesoAdopcionEntity entityToUpdate=factory.manufacturePojo(ProcesoAdopcionEntity.class);
       entityToUpdate.setId(newEntity.getId());
       entityToUpdate.setEstado("Cancelado");
       entityToUpdate.setComentario(null);
       procesoLogic.updateProcesoAdopcion(entityToUpdate);
    }
     @Test (expected = BusinessLogicException.class)
    public void updateProcesoCalificacionCeroTest() throws BusinessLogicException{
       ProcesoAdopcionEntity newEntity=factory.manufacturePojo(ProcesoAdopcionEntity.class);
       newEntity.setEstado("Cancelado");
       procesoLogic.createProcesoAdopcion(newEntity);
       ProcesoAdopcionEntity entityToUpdate=factory.manufacturePojo(ProcesoAdopcionEntity.class);
       entityToUpdate.setId(newEntity.getId());
       entityToUpdate.setCalificacion(0);
       entityToUpdate.setEstado("Cancelado");
       procesoLogic.updateProcesoAdopcion(entityToUpdate);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void updateProcesoCalificacionSeisTest() throws BusinessLogicException{
        ProcesoAdopcionEntity newEntity=factory.manufacturePojo(ProcesoAdopcionEntity.class);
       newEntity.setEstado("Cancelado");
       procesoLogic.createProcesoAdopcion(newEntity);
       ProcesoAdopcionEntity entityToUpdate=factory.manufacturePojo(ProcesoAdopcionEntity.class);
       entityToUpdate.setId(newEntity.getId());
       entityToUpdate.setCalificacion(6);
       entityToUpdate.setEstado("Cancelado");
       procesoLogic.updateProcesoAdopcion(entityToUpdate);
    }
    
    @Test
    public void findProcesoAdopcionTest() throws BusinessLogicException{
        ProcesoAdopcionEntity entity=factory.manufacturePojo(ProcesoAdopcionEntity.class);
    
        entity.setEstado("Cancelado");
        procesoLogic.createProcesoAdopcion(entity);
        ProcesoAdopcionEntity resultEntity=procesoLogic.findProcesoAdopcion(entity.getId());
        assertNotNull(resultEntity);
        assertEquals(entity.getId(),resultEntity.getId());
        assertEquals(entity.getEstado(),resultEntity.getEstado());
        assertEquals(entity.getComentario(),resultEntity.getComentario());
        assertEquals(entity.getCalificacion(),resultEntity.getCalificacion());
    }
    
    @Test
    public void deleteProcesoAdopcionTest() throws BusinessLogicException{
        ProcesoAdopcionEntity entity=factory.manufacturePojo(ProcesoAdopcionEntity.class);
        entity.setEstado("Cancelado");
        entity=procesoLogic.createProcesoAdopcion(entity);
        procesoLogic.deleteProcesoAdopcion(entity.getId());
        ProcesoAdopcionEntity deleted=em.find(ProcesoAdopcionEntity.class,entity.getId());
        assertNull(deleted);
    }
    @Test
    public void createProcesoEnProcesoTest() throws BusinessLogicException{
       ProcesoAdopcionEntity newEntity=factory.manufacturePojo(ProcesoAdopcionEntity.class);
       newEntity.setEstado("En Proceso");
       ProcesoAdopcionEntity result=procesoLogic.createProcesoAdopcion(newEntity);
       assertNotNull(result);
       
       ProcesoAdopcionEntity entity= em.find(ProcesoAdopcionEntity.class, result.getId());
       assertEquals(entity.getEstado(), result.getEstado());
       assertEquals(entity.getCalificacion(), result.getCalificacion());
       assertEquals(entity.getComentario(), result.getComentario());
       assertEquals(entity.getUsuario(), result.getUsuario());
    }
    
    @Test
    public void createProcesoTerminadoTest() throws BusinessLogicException{
       ProcesoAdopcionEntity newEntity=factory.manufacturePojo(ProcesoAdopcionEntity.class);
       newEntity.setEstado("Terminado");
       ProcesoAdopcionEntity result=procesoLogic.createProcesoAdopcion(newEntity);
       assertNotNull(result);
       
       ProcesoAdopcionEntity entity= em.find(ProcesoAdopcionEntity.class, result.getId());
       assertEquals(entity.getEstado(), result.getEstado());
       assertEquals(entity.getCalificacion(), result.getCalificacion());
       assertEquals(entity.getComentario(), result.getComentario());
       assertEquals(entity.getUsuario(), result.getUsuario());
    }
    
}
