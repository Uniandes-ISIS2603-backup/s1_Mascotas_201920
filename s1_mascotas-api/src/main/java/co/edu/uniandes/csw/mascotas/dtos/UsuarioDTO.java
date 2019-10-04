/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import java.io.Serializable;

/**
 *
 * @author Tobia Gasparoni
 */
public class UsuarioDTO implements Serializable{
    
    /**
     * Nombre del usuario
     */
    private String nombre;
    
    /**
     * Ciudad en la que reside el usuario
     */
    private String ciudad;
    
    /**
     * Correo del usuario
     */
    private String correo;
    
    /**
     * Celular del usuario
     */
    private Integer celular;
    
    /**
     * ID del usuario
     */
    private Long id;
    
    public UsuarioDTO()
    {
        
    }
    
    public UsuarioDTO(UsuarioEntity usuario)
    {
        setCelular(usuario.getCelular());
        setCiudad(usuario.getCiudad());
        setCorreo(usuario.getCorreo());
        setNombre(usuario.getNombre());
    }
    
    public UsuarioEntity toEntity()
    {
        UsuarioEntity entity = new UsuarioEntity();
        
        entity.setCorreo(correo);
        entity.setCelular(celular);
        entity.setCiudad(ciudad);
        entity.setNombre(nombre);
        
        return entity;
    }
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * @return the celular
     */
    public Integer getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(Integer celular) {
        this.celular = celular;
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
    
}
