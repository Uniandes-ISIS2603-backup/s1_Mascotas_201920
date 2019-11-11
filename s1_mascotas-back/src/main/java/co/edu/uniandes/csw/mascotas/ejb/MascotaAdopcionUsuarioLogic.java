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
package co.edu.uniandes.csw.mascotas.ejb;


import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import co.edu.uniandes.csw.mascotas.persistence.UsuarioPersistence;
import co.edu.uniandes.csw.mascotas.persistence.MascotaAdopcionPersistance;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de MascotaAdopcion y Usuario.
 *
 * @author t.langebaek
 */
@Stateless
public class MascotaAdopcionUsuarioLogic {

    private static final Logger LOGGER = Logger.getLogger(MascotaAdopcionUsuarioLogic.class.getName());

    @Inject
    private MascotaAdopcionPersistance mascotaPersistence;

    @Inject
    private UsuarioPersistence usuarioPersistence;

    /**
     * Remplazar el usuario de una mascota encontrada.
     *
     * @param mascotaAdopcionId id de las mascota que se quiere actualizar.
     * @param usuarioId El id del usuario que se será de la mascota.
     * @return la nueva mascota.
     */
    public MascotaAdopcionEntity replaceUsuario(Long mascotaAdopcionId, Long usuarioId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar mascota encontrada con id = {0}", mascotaAdopcionId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        MascotaAdopcionEntity mascotaAdopcionEntity = mascotaPersistence.find(mascotaAdopcionId);
        mascotaAdopcionEntity.setUsuario(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar mascota encontrada con id = {0}", mascotaAdopcionEntity.getId());
        return mascotaAdopcionEntity;
    }

    /**
     * Borrar un usuario de una mascota encontrada. Este metodo se utiliza para borrar la
     * relacion de una mascota.
     *
     * @param mascotaId La mascota encontrada que se le borrarà el usuario.
     */
    public void removeUsuario(Long mascotaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el usuario de la mascota con id = {0}", mascotaId);
        MascotaAdopcionEntity mascotaEntity = mascotaPersistence.find(mascotaId);
        //UsuarioEntity usuarioEntity = usuarioPersistence.find(mascotaEntity.getUsuario().getId());
        mascotaEntity.setUsuario(null);
        //usuarioEntity.getMascotasAdopcions().remove(mascotaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el usuario de la mascota con id = {0}", mascotaEntity.getId());
    }
}
