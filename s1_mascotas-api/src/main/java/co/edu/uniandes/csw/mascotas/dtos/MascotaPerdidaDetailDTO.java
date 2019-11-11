/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.MascotaPerdidaEntity;
import co.edu.uniandes.csw.mascotas.entities.MultimediaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * MascotaPerdidaDetailDTO
 * @author Lily Duque
 */
public class MascotaPerdidaDetailDTO  extends MascotaPerdidaDTO implements Serializable{
       private List<MultimediaDTO> multimedia;
         public MascotaPerdidaDetailDTO() {
        
    }
    
     public MascotaPerdidaDetailDTO(MascotaPerdidaEntity mascotaEntity) {
        super(mascotaEntity);
       
            if (mascotaEntity != null && mascotaEntity.getMultimedia() != null) {
                multimedia = new ArrayList<>();
                for (MultimediaEntity multimediaEntity : mascotaEntity.getMultimedia()) {
                   multimedia.add(new MultimediaDTO(multimediaEntity));
                }
            }
        }
  

    /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO de la editorial para transformar a Entity
     */
    @Override
    public MascotaPerdidaEntity toEntity() {
        MascotaPerdidaEntity mascotaEntity = super.toEntity();
       
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
