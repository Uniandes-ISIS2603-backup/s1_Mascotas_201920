/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import co.edu.uniandes.csw.mascotas.podam.DateStrategy;
import co.edu.uniandes.csw.mascotas.podam.EspecieEstrategy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
    @PodamStrategyValue (EspecieEstrategy.class)
    private Integer especie;
    
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
    @OneToMany(mappedBy = "mascotaEncontrada", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<MultimediaEntity> multimedia = new ArrayList<>();
    
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;
    
    /**
     * Variable que modela la fecha cuando se encontro la mascota
     */
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue (DateStrategy.class)
    private Date fechaEncontrada;

    /**
     * @return the raza
     */
    public String getRaza() {
        return raza;
    }
    
    /**
     * @param especie the especie to set
     */
    public void setEspecie(Integer especie) {
        this.especie = especie;
    }
    
    /**
     * @return the especie
     */
    public Integer getEspecie() {
        return especie;
    }

    /**
     * @param raza the raza to set
     */
    public void setRaza(String raza) {
        this.raza = raza;
    }
    
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
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

    /**
     * @return the usuario
     */
    public UsuarioEntity getUsuario() {
        return usuario;
    }
    
    /**
     * @param multimedia the multimedia to set
     */
    public void setMultimedia(List<MultimediaEntity> multimedia) {
        this.multimedia = multimedia;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the multimedia
     */
    public List<MultimediaEntity> getMultimedia() {
        return multimedia;
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
        else if(!(o instanceof MascotaEncontradaEntity)) return false;
        else 
        {
            MascotaEncontradaEntity m = (MascotaEncontradaEntity) o;
            return m.hashCode() == this.hashCode() && m.getId().equals(this.getId());
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.especie);
        hash = 17 * hash + Objects.hashCode(this.raza);
        hash = 17 * hash + Objects.hashCode(this.lugar);
        hash = 17 * hash + Objects.hashCode(this.descripcion);
        hash = 17 * hash + Objects.hashCode(this.multimedia);
        hash = 17 * hash + Objects.hashCode(this.usuario);
        hash = 17 * hash + Objects.hashCode(this.fechaEncontrada);
        return hash;
    }
}