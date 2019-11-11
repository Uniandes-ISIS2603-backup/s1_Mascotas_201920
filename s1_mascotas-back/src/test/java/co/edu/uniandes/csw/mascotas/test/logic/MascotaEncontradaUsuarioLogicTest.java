/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.MascotaAdopcionLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaEncontradaLogic;
import co.edu.uniandes.csw.mascotas.ejb.MascotaEncontradaUsuarioLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;
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
 * @author ja.avelino
 */
@RunWith(Arquillian.class)
public class MascotaEncontradaUsuarioLogicTest {


    private final PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private MascotaEncontradaLogic bookLogic;
    @Inject
    private MascotaEncontradaUsuarioLogic bookEditorialLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<UsuarioEntity> data = new ArrayList<>();

    private List<MascotaEncontradaEntity> booksData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(MascotaAdopcionLogic.class.getPackage())
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
        em.createQuery("delete from MascotaEncontradaEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            MascotaEncontradaEntity books = factory.manufacturePojo(MascotaEncontradaEntity.class);
            em.persist(books);
            booksData.add(books);
        }
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                booksData.get(i).setUsuario(entity);
            }
        }
    }

    /**
     * Prueba para remplazar las instancias de Books asociadas a una instancia
     * de Editorial.
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test
    public void replaceEditorialTest() throws BusinessLogicException {
        MascotaEncontradaEntity entity = booksData.get(0);
        bookEditorialLogic.replaceUsuario(entity.getId(), data.get(1).getId());
        entity = bookLogic.findMascotaEncontrada(entity.getId());
        Assert.assertEquals(entity.getUsuario(), data.get(1));
    }
    
    /**
     * Prueba para remplazar las instancias de Books asociadas a una instancia
     * de Editorial.
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void replaceUsuarioSinMascotaTest() throws BusinessLogicException {
        bookEditorialLogic.replaceUsuario(new Long(0), new Long(0));
    }

    /**
     * Prueba para desasociar un Book existente de un Editorial existente
     *
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test
    public void removeBooksTest() throws BusinessLogicException {
        bookEditorialLogic.removeUsuario(booksData.get(0).getId());
        MascotaEncontradaEntity response = bookLogic.findMascotaEncontrada(booksData.get(0).getId());
        Assert.assertNull(response.getUsuario());
    }
    
    /**
     * Prueba para desasociar un Book existente de un Editorial existente
     *
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test
    public void removeSinUsuarioTest() throws BusinessLogicException {
        bookEditorialLogic.removeUsuario(booksData.get(0).getId());
        bookEditorialLogic.removeUsuario(booksData.get(0).getId());
        MascotaEncontradaEntity response = bookLogic.findMascotaEncontrada(booksData.get(0).getId());
        Assert.assertNull(response.getUsuario());
    }
    
    /**
     * Prueba para desasociar un Book existente de un Editorial existente
     *
     * @throws co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void removeSinMascotaTest() throws BusinessLogicException {
        bookEditorialLogic.removeUsuario(new Long(-1));
    }
}
