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
 *
 * @author German Rozo
 */

public class PublicidadDetailDTO extends PublicidadDTO implements Serializable {

    private List<MultimediaDTO> multimedia;

    public PublicidadDetailDTO() {}

    public PublicidadDetailDTO(PublicidadEntity publicidad) 
    {
        super(publicidad);
        if (publicidad != null) 
        {
            multimedia = new ArrayList<>();
            
            for (MultimediaEntity multimediaEntity : publicidad.getMultimedia()) 
            {
                multimedia.add(new MultimediaDTO(multimediaEntity));
            }
        }
    }

    @Override
    public PublicidadEntity toEntity()
    {
        PublicidadEntity entity = super.toEntity();
        
        List<MultimediaEntity> list= new ArrayList<>();
        
        for (MultimediaDTO multimediaDTO : multimedia) {
            list.add(multimediaDTO.toEntity());
        }
        
        entity.setMultimedia(list);
        return entity;
    }

    public List<MultimediaDTO> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<MultimediaDTO> multimedia) {
        this.multimedia = multimedia;
    }
  
}

