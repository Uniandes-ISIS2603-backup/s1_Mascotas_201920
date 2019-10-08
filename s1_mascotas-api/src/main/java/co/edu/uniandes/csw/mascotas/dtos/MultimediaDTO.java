/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.MultimediaEntity;
import java.io.Serializable;

/**
 *
 * @author Lily Duque
 */
public class MultimediaDTO implements Serializable {
   private String url;
  
    private String nombre;
    
    private String tipo; 
   
    public MultimediaDTO ()
    {
        
    }
    public MultimediaDTO (MultimediaEntity multi)
    {
        if (multi != null)
        {
            tipo = multi.getTipo();
            nombre = multi.getNombre();
            url = multi.getUrl();
        }
    }
     public MultimediaEntity  toEntity()
    {
        MultimediaEntity  entity = new MultimediaEntity ();
        entity.setNombre(nombre);
        entity.setTipo(tipo);
        entity.setUrl(url);
        
        return entity;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
