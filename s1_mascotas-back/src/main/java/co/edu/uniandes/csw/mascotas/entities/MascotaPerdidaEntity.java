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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 * Clase que representa una mascota perdida en la persistencia y permite su serialización
 * @author Lily Duque
 */
@Entity
public class MascotaPerdidaEntity extends BaseEntity implements Serializable
{
     /**
     * El usuario asociado a la mascota
     */
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;
    
    /**
     * Recompensa
     */
    @PodamExclude
    @OneToOne
    private RecompensaEntity recompensa;
    /**
     * Raza de la mascota perdida
     */
    private String raza;
    
    /**
     * La especie de la mascota perdida
     */
    @PodamStrategyValue (EspecieEstrategy.class)
    private Integer especie;
    
    /**
     * La descripción de la mascota perdida
     */
    private String descripcion;
    
    /**
     * El lugar donde se perdió la mascota
     */
    private String lugar;
    
    private Boolean encontrado;
    
    
    /**
     * La fecha en la que se perdió la mascota
     */
    @Temporal(TemporalType.DATE)
    @PodamStrategyValue (DateStrategy.class)
    private Date fechaPerdida;
    
    @PodamExclude
    @OneToMany(mappedBy = "mascotaPerdida", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<MultimediaEntity> multimedia = new ArrayList<>();


    /**
     * Compara dos objetos
     * @param o El objeto a comparar
     * @return true si son iguales, false si no
     */
    @Override
    public boolean equals(Object o)
    {
        if(o == null) return false;
        else if(!(o instanceof MascotaPerdidaEntity)) return false;
        else 
        {
            MascotaPerdidaEntity m = (MascotaPerdidaEntity) o;
            return m.hashCode() == this.hashCode() && m.getId().equals(this.getId());
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.getUsuario());
        hash = 79 * hash + Objects.hashCode(this.getRecompensa());
        hash = 79 * hash + Objects.hashCode(this.getRaza());
        hash = 79 * hash + Objects.hashCode(this.getEspecie());
        hash = 79 * hash + Objects.hashCode(this.getDescripcion());
        hash = 79 * hash + Objects.hashCode(this.getLugar());
        hash = 79 * hash + Objects.hashCode(this.getFechaPerdida());
        hash = 79 * hash + Objects.hashCode(this.getMultimedia());
        hash = 79 * hash + Objects.hashCode(this.getEncontrado());
        return hash;
    }

    /**
     * @return the usuario
     */
    public UsuarioEntity getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the recompensa
     */
    public RecompensaEntity getRecompensa() {
        return recompensa;
    }

    /**
     * @param recompensa the recompensa to set
     */
    public void setRecompensa(RecompensaEntity recompensa) {
        this.recompensa = recompensa;
    }

   

    /**
     * @param raza the raza to set
     */
    public void setRaza(String raza) {
        this.raza = raza;
    }

    /**
     * @return the especie
     */
    public Integer getEspecie() {
        return especie;
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
     * @return the lugar
     */
    public String getLugar() {
        return lugar;
    }
     /**
     * @return the raza
     */
    public String getRaza() {
        return raza;
    }
    

    /**
     * @return the encontrado
     */
    public Boolean getEncontrado() {
        return encontrado;
    }

    /**
     * @param encontrado the encontrado to set
     */
    public void setEncontrado(Boolean encontrado) {
        this.encontrado = encontrado;
    }
    
     /**
     * @param especie the especie to set
     */
    public void setEspecie(Integer especie) {
        this.especie = especie;
    }
    /**
     * @return the fechaPerdida
     */
    public Date getFechaPerdida() {
        return fechaPerdida;
    }

    /**
     * @param fechaPerdida the fechaPerdida to set
     */
    public void setFechaPerdida(Date fechaPerdida) {
        this.fechaPerdida = fechaPerdida;
    }

    /**
     * @return the multimedia
     */
    public List<MultimediaEntity> getMultimedia() {
        return multimedia;
    }

    /**
     * @param multimedia the multimedia to set
     */
    public void setMultimedia(List<MultimediaEntity> multimedia) {
        this.multimedia = multimedia;
    }
    
    /**
     * @param lugar the lugar to set
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    
}
