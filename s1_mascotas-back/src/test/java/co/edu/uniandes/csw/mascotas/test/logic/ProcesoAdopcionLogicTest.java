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
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import org.junit.Before;

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
    
     @Inject
    private UserTransaction utx;
    private List<ProcesoAdopcionEntity> data = new ArrayList<>();
     /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from ProcesoAdopcionEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
        em.createQuery("delete from MascotaAdopcionEntity").executeUpdate();
    }
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ProcesoAdopcionEntity entity = factory.manufacturePojo(ProcesoAdopcionEntity.class);
            em.persist(entity);
            
            data.add(entity);
        }
        ProcesoAdopcionEntity e = data.get(2);
        MascotaAdopcionEntity entity = factory.manufacturePojo(MascotaAdopcionEntity.class);
        entity.setProcesos(new ArrayList<>());
        entity.getProcesos().add(e);
        em.persist(entity);
        e.setMascotaAdopcion(entity);
        UsuarioEntity entityU = factory.manufacturePojo(UsuarioEntity.class);
        entityU.setProcesosAdopcion(new ArrayList<>());
        entityU.getProcesosAdopcion().add(e);
        em.persist(entityU);
        e.setUsuario(entityU);
        
    }
    
    @Test
    public void createProcesoCanceladoTest() throws BusinessLogicException{
       ProcesoAdopcionEntity entidad=factory.manufacturePojo(ProcesoAdopcionEntity.class);
       entidad.setEstado("Cancelado");
    
       ProcesoAdopcionEntity result=procesoLogic.createProcesoAdopcion(entidad);
       assertNotNull(result);
       
       ProcesoAdopcionEntity entity= em.find(ProcesoAdopcionEntity.class, result.getId());
       assertEquals(entity.getEstado(), result.getEstado());
       assertEquals(entity.getCalificacion(), result.getCalificacion());
       assertEquals(entity.getComentario(), result.getComentario());
       assertEquals(entity.getUsuario(), result.getUsuario());
       
        Assert.assertFalse(entidad.equals(null));
        String test = "test";
        Assert.assertFalse(entidad.equals(test));
        ProcesoAdopcionEntity met = new ProcesoAdopcionEntity();
        met.setId(entidad.getId()+1);
        met.setEstado(entidad.getEstado());
        met.setComentario(entidad.getComentario());
        met.setCalificacion(entidad.getCalificacion());
        met.setMascotaAdopcion(entidad.getMascotaAdopcion());
        met.setUsuario(entidad.getUsuario());
        Assert.assertFalse( entidad.equals(met) );
        met.setId(entidad.getId());
        met.setEstado(met.getEstado()+"Chao");
        Assert.assertFalse( entidad.equals(met) );
        met.setEstado(entidad.getEstado());
        Assert.assertTrue(entidad.equals(met));
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
