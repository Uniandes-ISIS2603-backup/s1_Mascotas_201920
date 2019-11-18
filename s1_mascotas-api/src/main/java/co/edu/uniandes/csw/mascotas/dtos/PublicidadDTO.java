/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.adapters.DateAdapter;
import co.edu.uniandes.csw.mascotas.entities.PublicidadEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author German Rozo
 */
@XmlRootElement(name = "publicidad")
@XmlAccessorType(XmlAccessType.FIELD)
public class PublicidadDTO implements Serializable {

    private Long id;
    private String mensaje;
    private Integer diasPorSemana;
    private Integer costo;

    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaInicio;

    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaFin;

    public PublicidadDTO() {
    }

    public PublicidadDTO(PublicidadEntity publicidad) {
        if (publicidad != null) {
            id= publicidad.getId();
            mensaje = publicidad.getMensaje();
            diasPorSemana = publicidad.getDiasPorSemana();
            costo = publicidad.getCosto();
            fechaInicio = publicidad.getFechaInicio();
            fechaFin = publicidad.getFecchaFin();
            
        }
    }

    public PublicidadEntity toEntity()
    {
        PublicidadEntity entity = new PublicidadEntity();
        entity.setId(id);
        entity.setCosto(costo);
        entity.setDiasPorSemana(diasPorSemana);
        entity.setFecchaFin(fechaFin);
        entity.setFechaInicio(fechaInicio);
        entity.setMensaje(mensaje);
        
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
        
    

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaFin(Date fecchaFin) {
        this.fechaFin = fecchaFin;
    }
    
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    public String getMensaje() {
        return mensaje;
    }

    public void setDiasPorSemana(Integer diasPorSemana) {
        this.diasPorSemana = diasPorSemana;
    }
    
    public Integer getDiasPorSemana() {
        return diasPorSemana;
    }   
    
    public void setCosto(Integer costo) {
        this.costo = costo;
    }
    
    public Integer getCosto() {
        return costo;
    }

}

