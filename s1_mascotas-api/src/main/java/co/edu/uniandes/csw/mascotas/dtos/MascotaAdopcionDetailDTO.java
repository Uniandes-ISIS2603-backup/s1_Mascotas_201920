/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Estudiante
 */
public class MascotaAdopcionDetailDTO extends MascotaAdopcionDTO implements Serializable{
    
    private List<MascotaAdopcionDTO> mascotasAdopcion;
    
    public MascotaAdopcionDetailDTO(){
        
    }

    /**
     * @return the mascotasAdopcion
     */
    public List<MascotaAdopcionDTO> getMascotasAdopcion() {
        return mascotasAdopcion;
    }

    /**
     * @param mascotasAdopcion the mascotasAdopcion to set
     */
    public void setMascotasAdopcion(List<MascotaAdopcionDTO> mascotasAdopcion) {
        this.mascotasAdopcion = mascotasAdopcion;
    }
}

