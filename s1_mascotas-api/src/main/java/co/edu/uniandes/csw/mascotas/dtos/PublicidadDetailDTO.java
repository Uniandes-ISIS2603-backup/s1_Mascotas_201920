/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.MultimediaEntity;
import co.edu.uniandes.csw.mascotas.entities.PublicidadEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO Publicidad con la informacion de multimedia
 * @author German Rozo
 */
public class PublicidadDetailDTO extends PublicidadDTO implements Serializable {

    /**
     * Fotos y videos de la publicidad
     */
    private List<MultimediaDTO> multimedia = new ArrayList<>();
    
    /**
     * Contructor default
     */
    public PublicidadDetailDTO() {}

    /**
     * Constructor con convierte el parametro en un DTO
     * @param publicidad 
     */
    public PublicidadDetailDTO(PublicidadEntity publicidad) {
        super(publicidad);
        if (publicidad != null) 
        {
            for (MultimediaEntity multimediaEntity :  publicidad.getMultimedia()) 
            {
                multimedia.add(new MultimediaDTO(multimediaEntity));
            }
        }
    }

    /**
     * Retorna la entidad con la informacion del DTO
     * @return PublicidadEntity
     */
    public PublicidadEntity toEntity()
    {
        PublicidadEntity entity= super.toEntity();
        ArrayList<MultimediaEntity> multimediaEntity =new ArrayList();
        
        for (MultimediaDTO multimediaDTO : multimedia) {
            multimediaEntity.add(multimediaDTO.toEntity());
        }
        
        entity.setMultimedia(multimediaEntity);
        
        return entity;
    }
 
}

