/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.MultimediaEntity;
import co.edu.uniandes.csw.mascotas.entities.ProcesoAdopcionEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tomás Langebaek
 */
public class MascotaAdopcionDetailDTO extends MascotaAdopcionDTO implements Serializable {

    private List<ProcesoAdopcionDTO> procesosAdopcion;
    
   private List<MultimediaDTO> multimedia;

    public MascotaAdopcionDetailDTO() {
        
    }
    
     public MascotaAdopcionDetailDTO(MascotaAdopcionEntity mascotaEntity) {
        super(mascotaEntity);
        if (mascotaEntity != null) {
            if (mascotaEntity.getProcesos() != null) {
                procesosAdopcion = new ArrayList<>();
                
                for (ProcesoAdopcionEntity procesoEntity : mascotaEntity.getProcesos()) {
                   procesosAdopcion.add(new ProcesoAdopcionDTO(procesoEntity));
                }
            }
            if (mascotaEntity.getMultimedia() != null) {
                multimedia = new ArrayList<>();
                for (MultimediaEntity multimediaEntity : mascotaEntity.getMultimedia()) {
                   multimedia.add(new MultimediaDTO(multimediaEntity));
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
                procesosEntity.add(dtoProceso.toEntity());
            }
            mascotaEntity.setProcesos(procesosEntity);
        }
         if (getMultimedia() != null) {
            List<MultimediaEntity> multimediaEntity = new ArrayList<>();
            for (MultimediaDTO dtoMultimedia : getMultimedia()) {
                multimediaEntity.add(dtoMultimedia.toEntity());
            }
            mascotaEntity.setMultimedia(multimediaEntity);
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

    /**
     * @return the multimedia
     */
    public List<MultimediaDTO> getMultimedia() {
        return multimedia;
    }

    /**
     * @param multimedia the multimedia to set
     */
    public void setMultimedia(List<MultimediaDTO> multimedia) {
        this.multimedia = multimedia;
    }
}
