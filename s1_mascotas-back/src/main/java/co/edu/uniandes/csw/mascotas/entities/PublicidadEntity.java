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
    /**
     * El contenido de la publicidad 
     */
    private String mensaje;
    
    /**
     * El numero de dias en los cuales la publicacion es mostrada al publico
     */
    @PodamExclude
    private Integer diasPorSemana;
    
    /**
     * Costo total de la publicidad
     */
    @PodamStrategyValue(PositiveIntegerStrategy.class)
    private Integer costo;
    
    /**
     * Fecha en la cual inicia la publicacion
     */
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fechaInicio;
    
    /**
     * Fecha en la cual la publicidad deja de aparecer en la aplicacion
     */
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue(DateStrategy.class)
    private Date fecchaFin;
    
    /**
     * Imagenes publicitarias
     */
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
    
    
    
}
