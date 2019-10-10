/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.MascotaPerdidaEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author lily 
 */
public class MascotaPerdidaDTO implements Serializable{
    
    /**
     * Recompensa
     */
    private RecompensaDTO recompensa;
    /**
     * Id de la mascota
     */
    private Long id;
    
    /**
     * Raza de la mascota perdida
     */
    private String raza;
    
    /**
     * La especie de la mascota perdida
     */
    private Integer especie;
    
    /**
     * La descripción de la mascota perdida
     */
    private String descripcion;
    
    /**
     * El lugar donde se perdió la mascota
     */
    private String lugar;

    /**
     * La fecha en la que se perdió la mascota
     */
    private Date fechaPerdida;
    
    
    public MascotaPerdidaDTO() {

    }

    public MascotaPerdidaDTO(MascotaPerdidaEntity entidad) {
        
        setDescripcion(entidad.getDescripcion());
        
        setEspecie((entidad.getEspecie()));
        
        setFechaPerdida(entidad.getFechaPerdida());
        
        setRaza(entidad.getRaza());
        
        setLugar(entidad.getLugar());
        
        if (entidad.getRecompensa() != null) {
                setRecompensa(new RecompensaDTO (entidad.getRecompensa()));
            } else {
                setRecompensa(null);
            }
        setId(entidad.getId());
        
    }

    public MascotaPerdidaEntity toEntity() {

        MascotaPerdidaEntity entidad = new MascotaPerdidaEntity();

        entidad.setDescripcion((this.getDescripcion()));

        entidad.setEspecie(this.getEspecie());

        entidad.setFechaPerdida( this.getFechaPerdida() );

        entidad.setLugar(this.getLugar());

        entidad.setRaza(this.getRaza());
        
        if (this.recompensa != null)
            entidad.setRecompensa(this.getRecompensa().toEntity());
        
        entidad.setId(this.getId());
        
        return entidad;

    }

    public RecompensaDTO getRecompensa() {
        return recompensa;
    }

    public void setRecompensa(RecompensaDTO recompensa) {
        this.recompensa = recompensa;
    }
    
    /**
     * 
     * @return ID
     */
    public Long getId() {
        return id;
    }
    /**
     * 
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
     * @return the especie
     */
    public Integer getEspecie() {
        return especie;
    }

    /**
     * @param especie the especie to set
     */
    public void setEspecie(Integer especie) {
        this.especie = especie;
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
     * @param lugar the lugar to set
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
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
    
}
