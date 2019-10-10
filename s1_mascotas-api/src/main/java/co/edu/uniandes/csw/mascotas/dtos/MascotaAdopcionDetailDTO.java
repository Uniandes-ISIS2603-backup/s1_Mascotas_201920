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
 * @author Estudiante
 */
public class MascotaAdopcionDetailDTO extends MascotaAdopcionDTO implements Serializable {

    private List<ProcesoAdopcionDTO> procesosAdopcion;
    
   private List<MultimediaDTO> fotos;
   
    private List<MultimediaDTO> videos;

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
            if (mascotaEntity.getFotos() != null) {
                fotos = new ArrayList<>();
                for (MultimediaEntity fotoEntity : mascotaEntity.getFotos()) {
                   fotos.add(new MultimediaDTO(fotoEntity));
                }
            }
            if (mascotaEntity.getVideos() != null) {
                videos = new ArrayList<>();
                for (MultimediaEntity videoEntity : mascotaEntity.getVideos()) {
                   videos.add(new MultimediaDTO(videoEntity));
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
         if (getFotos() != null) {
            List<MultimediaEntity> multimediaEntity = new ArrayList<>();
            for (MultimediaDTO dtoFoto : getFotos()) {
                multimediaEntity.add(dtoFoto.toEntity());
            }
            mascotaEntity.setFotos(multimediaEntity);
        }
         if (getVideos() != null) {
            List<MultimediaEntity> multimediaEntity = new ArrayList<>();
            for (MultimediaDTO detVideo : getVideos()) {
                multimediaEntity.add(detVideo.toEntity());
            }
            mascotaEntity.setVideos(multimediaEntity);
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

    /**
     * @return the videos
     */
    public List<MultimediaDTO> getVideos() {
        return videos;
    }

    /**
     * @param videos the videos to set
     */
    public void setVideos(List<MultimediaDTO> videos) {
        this.videos = videos;
    }
}
