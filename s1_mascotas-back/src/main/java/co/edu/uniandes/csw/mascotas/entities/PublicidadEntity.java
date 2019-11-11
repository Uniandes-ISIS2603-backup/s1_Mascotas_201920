/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import co.edu.uniandes.csw.mascotas.podam.DateStrategy;
import co.edu.uniandes.csw.mascotas.podam.PositiveIntegerStrategy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author German Rozo
 */

@Entity
public class PublicidadEntity extends BaseEntity implements Serializable
{
    private String mensaje;
    
    @PodamStrategyValue(PositiveIntegerStrategy.class)
    private Integer diasPorSemana;
    
    @PodamStrategyValue(PositiveIntegerStrategy.class)
    private Integer costo;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaInicio;
    
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fecchaFin;
    
    @PodamExclude
    @OneToMany(mappedBy = "publicidad")
    private List<co.edu.uniandes.csw.mascotas.entities.MultimediaEntity> multimedia = new ArrayList<>();

    public List<co.edu.uniandes.csw.mascotas.entities.MultimediaEntity> getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(List<co.edu.uniandes.csw.mascotas.entities.MultimediaEntity> multimedia) {
        this.multimedia = multimedia;
    }
    
    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Integer getDiasPorSemana() {
        return diasPorSemana;
    }

    public void setDiasPorSemana(Integer diasPorSemana) {
        this.diasPorSemana = diasPorSemana;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFecchaFin() {
        return fecchaFin;
    }

    public void setFecchaFin(Date fecchaFin) {
        this.fecchaFin = fecchaFin;
    }


    @Override
    public boolean equals(Object obj) 
    {
        boolean resultado = true;
        
        if (this == obj) {
            resultado = true;
        }
        else if (obj == null) {
            resultado = false;
        }
        else if (getClass() != obj.getClass()) {
            resultado = false;
        }else{
        
        final PublicidadEntity other = (PublicidadEntity) obj;
        if (!Objects.equals(this.mensaje, other.mensaje)) {
            resultado = false;
        }
        else if (!Objects.equals(this.diasPorSemana, other.diasPorSemana)) {
            resultado = false;
        }
        else if (!Objects.equals(this.costo, other.costo)) {
            resultado = false;
        }
        else if (!Objects.equals(this.fechaInicio, other.fechaInicio)) {
            resultado = false;
        }
        else if (!Objects.equals(this.fecchaFin, other.fecchaFin)) {
            resultado = false;
        }
        else if (!Objects.equals(this.multimedia, other.multimedia)) {
            resultado = false;
        }
        }
        return resultado;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.mensaje);
        hash = 41 * hash + Objects.hashCode(this.diasPorSemana);
        hash = 41 * hash + Objects.hashCode(this.costo);
        hash = 41 * hash + Objects.hashCode(this.fechaInicio);
        hash = 41 * hash + Objects.hashCode(this.fecchaFin);
        hash = 41 * hash + Objects.hashCode(this.multimedia);
        return hash;
    }
    
    
    
}
