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
    @Temporal(TemporalType.DATE)
    //@PodamStrategyValue(DateStrategy.class)
    @PodamExclude
    private Date ultimaPublicacion;
    
    @PodamExclude
    private Integer publicaciones;
    
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

    public Date getUltimaPublicacion() {
        return ultimaPublicacion;
    }

    public void setUltimaPublicacion(Date ultimaPublicacion) {
        this.ultimaPublicacion = ultimaPublicacion;
    }

    public Integer getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(Integer publicaciones) {
        this.publicaciones = publicaciones;
    }

    
    
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


    /**
     * Compara dos objetos
     * @param o El objeto a comparar
     * @return true si son iguales, false si no
     */
    @Override
    public boolean equals(Object o)
    {
        if(o == null) return false;
        else if(!(o instanceof PublicidadEntity)) return false;
        else 
        {
            PublicidadEntity m = (PublicidadEntity) o;
            return m.hashCode() == this.hashCode() && m.getId().equals(this.getId());
        }
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
