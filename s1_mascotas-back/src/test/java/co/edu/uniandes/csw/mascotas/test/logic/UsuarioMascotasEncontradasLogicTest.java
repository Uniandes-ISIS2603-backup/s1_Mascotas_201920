/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.csw.mascotas.test.logic;

import co.edu.uniandes.csw.mascotas.ejb.UsuarioLogic;
import co.edu.uniandes.csw.mascotas.ejb.UsuarioMascotasEncontradasLogic;
import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.UsuarioPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Pruebas de logica de la relacion Usuario - Mascotas
 *
 * @author ja.avelino
 */
@RunWith(Arquillian.class)
public class UsuarioMascotasEncontradasLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private UsuarioLogic editorialLogic;
    @Inject
    private UsuarioMascotasEncontradasLogic editorialBooksLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<UsuarioEntity> data = new ArrayList<UsuarioEntity>();

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
                .addPackage(UsuarioLogic.class.getPackage())
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
        em.createQuery("delete from MascotaEncontradaEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 5; i++) {
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
     * Prueba para asociar un Books existente a un Editorial.
     */
    @Test
    public void addBooksTest() {
        UsuarioEntity entity = data.get(0);
        MascotaEncontradaEntity bookEntity = booksData.get(1);
        MascotaEncontradaEntity response = editorialBooksLogic.addMascota(bookEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(bookEntity.getId(), response.getId());
    }

    /**
     * Prueba para obtener una colección de instancias de Books asociadas a una
     * instancia Editorial.
     */
    @Test
    public void getBooksTest() {
        List<MascotaEncontradaEntity> list = editorialBooksLogic.getMascotas(data.get(0).getId());

        Assert.assertEquals(1, list.size());
    }

    /**
     * Prueba para obtener una instancia de Books asociada a una instancia
     * Editorial.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void getBookTest() throws BusinessLogicException {
        UsuarioEntity entity = data.get(0);
        MascotaEncontradaEntity bookEntity = booksData.get(0);
        MascotaEncontradaEntity response = editorialBooksLogic.getMascota(entity.getId(), bookEntity.getId());

        Assert.assertEquals(bookEntity.getId(), response.getId());
        Assert.assertEquals(bookEntity.getEspecie(), response.getEspecie());
        Assert.assertEquals(bookEntity.getDescripcion(), response.getDescripcion());
        Assert.assertEquals(bookEntity.getRaza(), response.getRaza());
        Assert.assertEquals(bookEntity.getMultimedia(), response.getMultimedia());
    }

    /**
     * Prueba para obtener una instancia de Books asociada a una instancia
     * Editorial que no le pertenece.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void getBookNoAsociadoTest() throws BusinessLogicException {
        UsuarioEntity entity = data.get(0);
        MascotaEncontradaEntity bookEntity = booksData.get(1);
        editorialBooksLogic.getMascota(entity.getId(), bookEntity.getId());
    }

    /**
     * Prueba para remplazar las instancias de Books asociadas a una instancia
     * de Editorial.
     */
    @Test
    public void replaceBooksTest() {
        UsuarioEntity entity = data.get(0);
        List<MascotaEncontradaEntity> list = booksData.subList(1, 3);
        editorialBooksLogic.replaceMascotas(entity.getId(), list);

        entity = editorialLogic.findUsuario(entity.getId());
        Assert.assertFalse(entity.getMascotasEncontradas().contains(booksData.get(1)));
    }
}
