/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import co.edu.uniandes.csw.mascotas.podam.DateStrategy;
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
 * @author ja.avelino
 */
@Entity
public class MascotaEncontradaEntity extends BaseEntity implements Serializable{
    
    /**
     * Variable que modela la especie de la mascota
     */
    private String especie;
    
    /**
     * Variable que modela la raza de la mascota
     */
    private String raza;
    
    /**
     * Variable que modela el lugar donde se encontro la mascota
     */
    private String lugar;
    
    /**
     * Variable que modela la descripcion de la mascota
     */
    private String descripcion;
    @PodamExclude
    @OneToMany(mappedBy = "mascotaEncontrada")
    private List<MultimediaEntity> fotos = new ArrayList<>();
    
    /**
     * Variable que modela la fecha cuando se encontro la mascota
     */
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue (DateStrategy.class)
    private Date fechaEncontrada;

    public List<MultimediaEntity> getFotos() {
        return fotos;
    }

    public void setFotos(List<MultimediaEntity> fotos) {
        this.fotos = fotos;
    }

    
    /**
     * @return the especie
     */
    public String getEspecie() {
        return especie;
    }

    /**
     * @param especie the especie to set
     */
    public void setEspecie(String especie) {
        this.especie = especie;
    }

    /**
     * @return the raza
     */
    public String getRaza() {
        return raza;
    }

    /**
     * @param raza the raza to set
     */
    public void setRaza(String raza) {
        this.raza = raza;
    }

    /**
     * @return the lugar
     */
    public String getLugar() {
        return lugar;
    }

    /**
     * @param lugar the lugar to set
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the fechaEncontrada
     */
    public Date getFechaEncontrada() {
        return fechaEncontrada;
    }

    /**
     * @param fechaEncontrada the fechaEncontrada to set
     */
    public void setFechaEncontrada(Date fechaEncontrada) {
        this.fechaEncontrada = fechaEncontrada;
    }
    
}