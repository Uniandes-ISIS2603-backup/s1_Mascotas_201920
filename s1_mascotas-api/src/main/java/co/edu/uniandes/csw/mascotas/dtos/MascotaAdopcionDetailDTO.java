/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.ProcesoAdopcionEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estudiante
 */
public class MascotaAdopcionDetailDTO extends MascotaAdopcionDTO implements Serializable {

    private List<ProcesoAdopcionDTO> procesosAdopcion;

    public MascotaAdopcionDetailDTO() {
        super();
    }
    
     public MascotaAdopcionDetailDTO(MascotaAdopcionEntity mascotaEntity) {
        super(mascotaEntity);
        if (mascotaEntity != null) {
            if (mascotaEntity.getProcesos() != null) {
                procesosAdopcion = new ArrayList<>();
                for (ProcesoAdopcionEntity entityProceso : mascotaEntity.getProcesos()) {
              //     procesos.add(new ProcesoAdopcionDTO(entityProceso));
                }
            }
        }
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO de la editorial para transformar a Entity
     */
    @Override
    public MascotaAdopcionEntity toEntity() {
        MascotaAdopcionEntity mascotaEntity = super.toEntity();
        if (procesosAdopcion != null) {
            List<ProcesoAdopcionEntity> procesosEntity = new ArrayList<>();
            for (ProcesoAdopcionDTO dtoProceso : procesosAdopcion) {
         //       procesosEntity.add(dtoProceso.toEntity());
            }
            mascotaEntity.setProcesos(procesosEntity);
        }
        return mascotaEntity;
    }

    

    /**
     * @return the procesosAdopcion
     */
    public List<ProcesoAdopcionDTO> getProcesosAdopcion() {
        return procesosAdopcion;
    }

    /**
     * @param procesosAdopcion the procesosAdopcion to set
     */
    public void setProcesosAdopcion(List<ProcesoAdopcionDTO> procesosAdopcion) {
        this.procesosAdopcion = procesosAdopcion;
    }
}
