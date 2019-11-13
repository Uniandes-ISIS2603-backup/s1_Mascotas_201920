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



import co.edu.uniandes.csw.mascotas.entities.MascotaPerdidaEntity;
import co.edu.uniandes.csw.mascotas.entities.RecompensaEntity;
import co.edu.uniandes.csw.mascotas.persistence.RecompensaPersistence;
import co.edu.uniandes.csw.mascotas.persistence.MascotaPerdidaPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de MascotaEncontrada y MascotaPerdida.
 *
 * @author lily Duque
 */
@Stateless
public class MascotaPerdidaRecompensaLogic {

    private static final Logger LOGGER = Logger.getLogger(MascotaPerdidaRecompensaLogic.class.getName());

    @Inject
    private RecompensaPersistence recompensa;

    @Inject
    private MascotaPerdidaPersistence mascotaPersistence;

    /**
     * Remplazar el mascotaPerdida de una mascota encontrada.
     *
     * @param recompensaId id de la recompesa que se quiere actualizar.
     * @param mascotaPerdidaId El id del mascotaPerdida que se será de la mascota.
     * @return la nueva recompensa
     */
    public RecompensaEntity replaceMascotaPerdida(Long recompensaId, Long mascotaPerdidaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar mascota encontrada con id = {0}", recompensaId);
        MascotaPerdidaEntity mascotaPerdidaEntity = mascotaPersistence.find(mascotaPerdidaId);
        RecompensaEntity recompensaEntity = recompensa.find(recompensaId);
        recompensaEntity.setMascotaPerdida(mascotaPerdidaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar mascota encontrada con id = {0}", recompensaEntity.getId());
        return recompensaEntity;
    }

    /**
     * Borrar un mascotaPerdida de una mascota encontrada. Este metodo se utiliza para borrar la
     * relacion de una mascota.
     *
     * @param mascotaId La mascota encontrada que se le borrarà el mascotaPerdida.
     */
    public void removeRecompensa(Long mascotaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el mascotaPerdida de la mascota con id = {0}", mascotaId);
        RecompensaEntity recompensaEntity = recompensa.find(mascotaId);
        MascotaPerdidaEntity mascotaEntity = mascotaPersistence.find(recompensaEntity.getMascotaPerdida().getId());
        mascotaEntity.setRecompensa(null);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el mascotaPerdida de la mascota con id = {0}", mascotaEntity.getId());
    }
}
