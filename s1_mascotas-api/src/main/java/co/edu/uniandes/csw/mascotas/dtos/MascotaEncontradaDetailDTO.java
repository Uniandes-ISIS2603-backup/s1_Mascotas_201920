/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;
import co.edu.uniandes.csw.mascotas.entities.MultimediaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ja.avelino
 */
public class MascotaEncontradaDetailDTO  extends MascotaEncontradaDTO implements Serializable{
    
       private List<MultimediaDTO> multimedia;
       
       public MascotaEncontradaDetailDTO() {
        
    }
    
     public MascotaEncontradaDetailDTO(MascotaEncontradaEntity mascotaEntity) {
        super(mascotaEntity);
       
            if (mascotaEntity.getMultimedia() != null) {
                multimedia = new ArrayList<>();
                for (MultimediaEntity multimediaEntity : mascotaEntity.getMultimedia()) {
                   multimedia.add(new MultimediaDTO(multimediaEntity));
                }
            }
        }
  
     /**
     * @param multimedia the multimedia to set
     */
    public void setMultimedia(List<MultimediaDTO> multimedia) {
        this.multimedia = multimedia;
    }
    
    /**
     * @return the multimedia
     */
    public List<MultimediaDTO> getMultimedia() {
        return multimedia;
    }
     
    /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO de la mascota para transformar a Entity
     */
    @Override
    public MascotaEncontradaEntity toEntity() {
        MascotaEncontradaEntity mascotaEntity = super.toEntity();
       
        if (getMultimedia() != null) {
            List<MultimediaEntity> multimediaEntity = new ArrayList<>();
            for (MultimediaDTO dtoMultimedia : getMultimedia()) {
                multimediaEntity.add(dtoMultimedia.toEntity());
            }
            mascotaEntity.setMultimedia(multimediaEntity);
        }
        return mascotaEntity;
    }
}
