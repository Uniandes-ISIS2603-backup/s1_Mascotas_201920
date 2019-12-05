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
    private Long celular;
    
    /**
     * Password del usuario
     */
    private String password;
    
    /**
     * ID del usuario
     */
    private Long id;
    
    public UsuarioDTO()
    {
        
    }
    
    public UsuarioDTO(UsuarioEntity usuario)
    {
        if(usuario != null){
            this.id = usuario.getId();
            this.celular = usuario.getCelular();
            this.ciudad = usuario.getCiudad();
            this.correo = usuario.getCorreo();
            this.nombre = usuario.getNombre();
            this.password = usuario.getPassword();
        }
    }
    
    public UsuarioEntity toEntity()
    {
        UsuarioEntity entity = new UsuarioEntity();
        
        entity.setId(id);
        entity.setCorreo(correo);
        entity.setCelular(celular);
        entity.setCiudad(ciudad);
        entity.setNombre(nombre);
        entity.setPassword(password);
        
        return entity;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * @return the password
     */
    public String getpassword() {
        return password;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param correo the correo to set
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    /**
     * @return the correo
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(Long celular) {
        this.celular = celular;
    }
    
    /**
     * @return the celular
     */
    public Long getCelular() {
        return celular;
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
