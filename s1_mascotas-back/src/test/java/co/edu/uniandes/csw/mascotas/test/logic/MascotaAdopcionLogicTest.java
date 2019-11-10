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
import co.edu.uniandes.csw.mascotas.podam.TipoEspecies;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
 * @author Tomás Langebaek
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

    /**
     * Test para crear una mascota
     *
     * @throws BusinessLogicException
     */
    @Test
    public void createMascotaAdopcion() throws BusinessLogicException {
        MascotaAdopcionEntity entidad = factory.manufacturePojo(MascotaAdopcionEntity.class);

        MascotaAdopcionEntity resultado = mascotaLogic.createMascotaAdopcion(entidad);
        Assert.assertNotNull(resultado);

        MascotaAdopcionEntity entidad2 = em.find(MascotaAdopcionEntity.class, resultado.getId());
        Assert.assertEquals(entidad2.getLugar(), resultado.getLugar());
        Assert.assertEquals(entidad2.getRaza(), resultado.getRaza());
        Assert.assertEquals(entidad2.getEspecie(), resultado.getEspecie());
        Assert.assertEquals(entidad2.getDescripcion(), resultado.getDescripcion());
        Assert.assertEquals(entidad2.getUsuario(), resultado.getUsuario());

    }

    /**
     * Test en el que se espera error al crear una macota con lugar nulo
     *
     * @throws BusinessLogicException si la logica funciona
     */
    @Test(expected = BusinessLogicException.class)
    public void createMascotaAdopcionLugarNull() throws BusinessLogicException {
        MascotaAdopcionEntity entidad = factory.manufacturePojo(MascotaAdopcionEntity.class);
        entidad.setLugar(null);
        MascotaAdopcionEntity resultado = mascotaLogic.createMascotaAdopcion(entidad);

    }

    /**
     * Test en el que se espera error al crear una macota con lugar vacio
     *
     * @throws BusinessLogicException si la logica funciona
     */
    @Test(expected = BusinessLogicException.class)
    public void createMascotaAdopcionLugarCadenaVacia() throws BusinessLogicException {
        MascotaAdopcionEntity entidad = factory.manufacturePojo(MascotaAdopcionEntity.class);
        entidad.setLugar("");
        MascotaAdopcionEntity resultado = mascotaLogic.createMascotaAdopcion(entidad);

    }

    /**
     * Test en el que se espera error al crear una macota con raza nula
     *
     * @throws BusinessLogicException si la logica funciona
     */
    @Test(expected = BusinessLogicException.class)
    public void createMascotaAdopcionRazaNull() throws BusinessLogicException {
        MascotaAdopcionEntity entidad = factory.manufacturePojo(MascotaAdopcionEntity.class);
        entidad.setRaza(null);
        MascotaAdopcionEntity resultado = mascotaLogic.createMascotaAdopcion(entidad);

    }

    /**
     * Test en el que se espera error al crear una macota con raza vacia
     *
     * @throws BusinessLogicException si la logica funciona
     */
    @Test(expected = BusinessLogicException.class)
    public void createMascotaAdopcionRazaCadenaVacia() throws BusinessLogicException {
        MascotaAdopcionEntity entidad = factory.manufacturePojo(MascotaAdopcionEntity.class);
        entidad.setRaza("");
        MascotaAdopcionEntity resultado = mascotaLogic.createMascotaAdopcion(entidad);

    }

    /**
     * Test en el que se espera error al crear una macota con especie nula
     *
     * @throws BusinessLogicException si la logica funciona
     */
    @Test(expected = BusinessLogicException.class)
    public void createMascotaAdopcionEspecieNull() throws BusinessLogicException {
        MascotaAdopcionEntity entidad = factory.manufacturePojo(MascotaAdopcionEntity.class);
        entidad.setEspecie(null);
        MascotaAdopcionEntity resultado = mascotaLogic.createMascotaAdopcion(entidad);

    }

    /**
     * Test en el que se espera error al crear una macota con descripcion nula
     *
     * @throws BusinessLogicException si la logica funciona
     */
    @Test(expected = BusinessLogicException.class)
    public void createMascotaAdopcionDescripcionNull() throws BusinessLogicException {
        MascotaAdopcionEntity entidad = factory.manufacturePojo(MascotaAdopcionEntity.class);
        entidad.setDescripcion(null);
        MascotaAdopcionEntity resultado = mascotaLogic.createMascotaAdopcion(entidad);

    }

    /**
     * Test en el que se espera error al crear una macota con descripcion vacia
     *
     * @throws BusinessLogicException si la logica funciona
     */
    @Test(expected = BusinessLogicException.class)
    public void createMascotaAdopcionDescripcionCadenaVacia() throws BusinessLogicException {
        MascotaAdopcionEntity entidad = factory.manufacturePojo(MascotaAdopcionEntity.class);
        entidad.setDescripcion("");
        MascotaAdopcionEntity resultado = mascotaLogic.createMascotaAdopcion(entidad);

    }

    /**
     * Test en el que se espera error al crear una macota que no esta en nungina
     * de las especies especificadas
     *
     * @throws BusinessLogicException si la logica funciona
     */
    @Test(expected = BusinessLogicException.class)
    public void createMascotaAdopcionEspecioNotAnimal() throws BusinessLogicException {
        MascotaAdopcionEntity entidad = factory.manufacturePojo(MascotaAdopcionEntity.class);

        Integer i = new Integer((int) (Math.random() * TipoEspecies.values().length * 50));
        while (i >= 0 && i < TipoEspecies.values().length) {
            i = new Integer((int) (Math.random() * TipoEspecies.values().length * 50));
        }
        entidad.setEspecie(i);

        MascotaAdopcionEntity resultado = mascotaLogic.createMascotaAdopcion(entidad);

    }

    /**
     * Test en el que se prueba que la logica se cumpla al actualizar una
     * mascota
     *
     * @throws BusinessLogicException
     */
    @Test
    public void updateMascotaAdopcion() throws BusinessLogicException {
        MascotaAdopcionEntity entidad = factory.manufacturePojo(MascotaAdopcionEntity.class);

        mascotaLogic.createMascotaAdopcion(entidad);
        MascotaAdopcionEntity entidad2 = factory.manufacturePojo(MascotaAdopcionEntity.class);
        entidad2.setId(entidad.getId());

        entidad2.setEspecie(entidad.getEspecie());

        mascotaLogic.updateMascotaAdopcion(entidad2);
        MascotaAdopcionEntity entidad3 = em.find(MascotaAdopcionEntity.class, entidad2.getId());

        Assert.assertEquals(entidad2.getLugar(), entidad3.getLugar());
        Assert.assertEquals(entidad2.getRaza(), entidad3.getRaza());
        Assert.assertEquals(entidad2.getEspecie(), entidad3.getEspecie());
        Assert.assertEquals(entidad2.getDescripcion(), entidad3.getDescripcion());

    }

    /**
     * Test en el que se verifica que la logica se cumpla al traer todas las
     * mascotas
     *
     * @throws BusinessLogicException
     */
    @Test
    public void findAllTest() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();

        ArrayList< MascotaAdopcionEntity> resultados = new ArrayList();

        int j = (int) (Math.random() * ((100 - 1) + 1)) + 1;
        for (int i = 0; i <= j; i++) {
            MascotaAdopcionEntity mascota = factory.manufacturePojo(MascotaAdopcionEntity.class);
            mascotaLogic.createMascotaAdopcion(mascota);
            Assert.assertNotNull(mascota);
            resultados.add(mascota);
        }

        List<MascotaAdopcionEntity> r = mascotaLogic.getMascotasAdopcion();
        Iterator iter = resultados.iterator();

        while (iter.hasNext()) {
            MascotaAdopcionEntity next = (MascotaAdopcionEntity) iter.next();
            Assert.assertTrue(r.contains(next));
        }
    }

    /**
     * Test en el que se verifia que la logica se cumpla al traer una sola
     * mascota
     */
    @Test
    public void getMascotaAdopcionTest() throws BusinessLogicException {

        MascotaAdopcionEntity entidad = factory.manufacturePojo(MascotaAdopcionEntity.class);

        MascotaAdopcionEntity entity = mascotaLogic.createMascotaAdopcion(entidad);

        MascotaAdopcionEntity resultEntity = mascotaLogic.getMascotaAdopcion(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getLugar(), resultEntity.getLugar());
        Assert.assertEquals(entity.getRaza(), resultEntity.getRaza());
        Assert.assertEquals(entity.getEspecie(), resultEntity.getEspecie());
        Assert.assertEquals(entity.getDescripcion(), resultEntity.getDescripcion());

    }

    /**
     * Test en el que se prueba el borrado de una mascota. Se verifica que se
     * cumpla la logica en ete caso
     *
     * @throws BusinessLogicException
     */
    @Test
    public void deleteMascotaAdopcionTest() throws BusinessLogicException {

        MascotaAdopcionEntity entidad = factory.manufacturePojo(MascotaAdopcionEntity.class);

        MascotaAdopcionEntity entity = mascotaLogic.createMascotaAdopcion(entidad);

        mascotaLogic.deleteMascotaAdopcion(entity.getId());
        MascotaAdopcionEntity deleted = em.find(MascotaAdopcionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
