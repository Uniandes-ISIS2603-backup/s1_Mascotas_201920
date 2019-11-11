/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.MascotaPerdidaLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaPerdidaUsuarioLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaPerdidaEntity;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.UsuarioPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
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
 * @author Lily
 */
@RunWith(Arquillian.class)
public class MascotaPerdidaUsuarioLogicTest {


    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private MascotaPerdidaLogic perdidaLogic;
    @Inject
    private MascotaPerdidaUsuarioLogic perdidaUsuarioLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<UsuarioEntity> data = new ArrayList<>();

    private List<MascotaPerdidaEntity> perdidasData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(MascotaPerdidaLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

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
        } catch (IllegalStateException | SecurityException | HeuristicMixedException | HeuristicRollbackException | NotSupportedException | RollbackException | SystemException e) {
            try {
                utx.rollback();
            } catch (IllegalStateException | SecurityException | SystemException e1) {
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from MascotaPerdidaEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            MascotaPerdidaEntity perdidas = factory.manufacturePojo(MascotaPerdidaEntity.class);
            em.persist(perdidas);
            perdidasData.add(perdidas);
        }
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                perdidasData.get(i).setUsuario(entity);
            }
        }
    }

    /**
     * Prueba para remplazar las instancias de Books asociadas a una instancia
     * de Usuario.
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test
    public void replaceUsuarioTest() throws BusinessLogicException {
        MascotaPerdidaEntity entity = perdidasData.get(0);
        perdidaUsuarioLogic.replaceUsuario(entity.getId(), data.get(1).getId());
        entity = perdidaLogic.getMascotaPerdida(entity.getId());
        Assert.assertEquals(entity.getUsuario(), data.get(1));
    }

    /**
     * Prueba para desasociar un Book existente de un Usuario existente
     *
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test
    public void removeBooksTest() throws BusinessLogicException {
        perdidaUsuarioLogic.removeUsuario(perdidasData.get(0).getId());
        MascotaPerdidaEntity response = perdidaLogic.getMascotaPerdida(perdidasData.get(0).getId());
        Assert.assertNull(response.getUsuario());
    }
}
