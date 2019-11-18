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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author ja.avelino
 */
@XmlRootElement(name = "encontrada")
@XmlAccessorType(XmlAccessType.FIELD)
public class MascotaEncontradaDTO implements Serializable{
    
    /**
     * ID de la mascota encontrada
     */    
    private Long id;
    
    /**
     * Especie de la mascota encontrada
     */
    private Integer especie;
    
    /**
     * Raza de la mascota encontrada
     */
    private String raza;
    
    /**
     * Lugar de la mascota encontrada
     */
    private String lugar;
    
    /**
     * Descripcion de la mascota encontrada
     */
    private String descripcion;
    
    /**
     * Fecha de encuentro de la mascota encontrada
     */
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date fechaEncontrada;
    
    /**
     * Usuario de la mascota encontrada
     */
    private UsuarioDTO usuario;
    
    /**
     * Constructor vacio de la mascota encontrada
     */
    public MascotaEncontradaDTO()
    {
        
    }
    
    /**
     * Constructor a partir de MascotaEncontradaEntity
     * @param entity Entidad de donde se sacan los datos
     */
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
            if (entity.getUsuario() != null) {
                this.usuario = new UsuarioDTO(entity.getUsuario());
            } else {
                this.usuario = null;
            }
        }
    }
    
    /**
     * Devuelve la entidad relacionada con el DTO
     * @return MascotaEncontradaEntity la entidad
     */
    public MascotaEncontradaEntity toEntity()
    {
        MascotaEncontradaEntity mee = new MascotaEncontradaEntity();
        mee.setDescripcion(descripcion);
        mee.setEspecie(especie);
        mee.setFechaEncontrada(fechaEncontrada);
        mee.setId(id);
        mee.setLugar(lugar);
        mee.setRaza(raza);
        if(this.getUsuario() != null)
            mee.setUsuario(getUsuario().toEntity());
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

    /**Asigna la especie al DTO
     * @param especie Especie a asignar al DTO
     */
    public void setEspecie(Integer especie) {
        this.especie = especie;
    }
    
    /**Retorna la especie de la mascota DTO
     * @return especie of DTO
     */
    public Integer getEspecie() {
        return especie;
    }

    /**Asigna la raza al DTO
     * @param raza Raza a asignar al DTO
     */
    public void setRaza(String raza) {
        this.raza = raza;
    }
    
    /**Retorna la raza de la mascota DTO
     * @return raza of DTO
     */
    public String getRaza() {
        return raza;
    }

    /**Asigna el lugar al DTO
     * @param lugar Lugar a asignar al DTO
     */
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    
    /**Retorna el lugar de la mascota DTO
     * @return lugar of DTO
     */
    public String getLugar() {
        return lugar;
    }

    /**Asigna la descripcion al DTO
     * @param descripcion Descripcion a asignar al DTO
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**Retorna la descripcion de la mascota DTO
     * @return raza of DTO
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**Asigna la fecha al DTO
     * @param fechaEncontrada Fecha a asignar al DTO
     */
    public void setFechaEncontrada(Date fechaEncontrada) {
        this.fechaEncontrada = fechaEncontrada;
    }
    
        /**Retorna la fecha de la mascota DTO
     * @return fechaEncontrada of DTO
     */
    public Date getFechaEncontrada() {
        return fechaEncontrada;
    }

    /**Asigna el usuario al DTO
     * @param usuario Usuario a asignar al DTO
     */
    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
    
    /**Retorna el usuario de la mascota DTO
     * @return usuario of DTO
     */
    public UsuarioDTO getUsuario() {
        return usuario;
    }
}
