/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.adapters.DateAdapter;
import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author ja.avelino
 */
public class MascotaEncontradaDTO implements Serializable{
    
    private Long id;
    private String especie;
    private String raza;
    private String lugar;
    private String descripcion;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaEncontrada;
    
    public MascotaEncontradaDTO()
    {
        
    }
    
    public MascotaEncontradaDTO(MascotaEncontradaEntity entity)
    {
        if(entity != null)
        {
            this.descripcion = entity.getDescripcion();
            this.especie = entity.getEspecie();
            this.fechaEncontrada = entity.getFechaEncontrada();
            this.id = entity.getId();
            this.raza = entity.getRaza();
            this.lugar = entity.getLugar();
        }
    }
    
    public MascotaEncontradaEntity toEntity()
    {
        MascotaEncontradaEntity mee = new MascotaEncontradaEntity();
        mee.setDescripcion(descripcion);
        mee.setEspecie(especie);
        mee.setFechaEncontrada(fechaEncontrada);
        mee.setId(id);
        mee.setLugar(lugar);
        mee.setRaza(raza);
        return mee;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
