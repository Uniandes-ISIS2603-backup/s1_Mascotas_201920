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
 *
 * @author Lily Duque
 */
public class MascotaPerdidaDetailDTO  extends MascotaPerdidaDTO implements Serializable{
       private List<MultimediaDTO> fotos;
       private List<MultimediaDTO> videos;
         public MascotaPerdidaDetailDTO() {
        
    }
    
     public MascotaPerdidaDetailDTO(MascotaPerdidaEntity mascotaEntity) {
        super(mascotaEntity);
       
            if (mascotaEntity.getFotos() != null) {
                fotos = new ArrayList<>();
                for (MultimediaEntity fotoEntity : mascotaEntity.getFotos()) {
                   fotos.add(new MultimediaDTO(fotoEntity));
                }
            }
            if (mascotaEntity.getVideos() != null) {
                fotos = new ArrayList<>();
                for (MultimediaEntity videoEntity : mascotaEntity.getVideos()) {
                   fotos.add(new MultimediaDTO(videoEntity));
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
       
         if (getFotos() != null) {
            List<MultimediaEntity> multimediaEntity = new ArrayList<>();
            for (MultimediaDTO dtoFoto : getFotos()) {
                multimediaEntity.add(dtoFoto.toEntity());
            }
            mascotaEntity.setFotos(multimediaEntity);
        }
        return mascotaEntity;
    }
    /**
     * @return the fotos
     */
    public List<MultimediaDTO> getFotos() {
        return fotos;
    }

    /**
     * @param fotos the fotos to set
     */
    public void setFotos(List<MultimediaDTO> fotos) {
        this.fotos = fotos;
    }
}
