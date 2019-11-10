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
    
    private Long id;
    
    private String url;
  
    private String nombre;
    
    private String tipo;
    
    private MascotaAdopcionDTO mascotaAdopcion;
    
    private MascotaEncontradaDTO mascotaEncontrada;
    
    private MascotaPerdidaDTO mascotaPerdida;
    
    private PublicidadDTO publicidad;
   
    public MultimediaDTO ()
    {
        
    }
    public MultimediaDTO (MultimediaEntity multi)
    {
        if (multi != null)
        {
            id =  multi.getId();
            tipo = multi.getTipo();
            nombre = multi.getNombre();
            url = multi.getUrl();
            if (multi.getMascota()!= null) {
                this.mascotaAdopcion = new MascotaAdopcionDTO(multi.getMascota());
            } else {
                this.mascotaAdopcion = null;
            }
            if (multi.getMascotaEncontrada()!= null) {
                this.mascotaEncontrada = new MascotaEncontradaDTO(multi.getMascotaEncontrada());
            } else {
                this.mascotaEncontrada = null;
            }
            if (multi.getMascotaPerdida()!= null) {
                this.mascotaPerdida = new MascotaPerdidaDTO(multi.getMascotaPerdida());
            } else {
                this.mascotaPerdida = null;
            }
            if (multi.getPublicidad()!= null) {
                this.publicidad = new PublicidadDTO(multi.getPublicidad());
            } else {
                this.publicidad = null;
            }
            
        }
    }
     public MultimediaEntity  toEntity()
    {
        MultimediaEntity  entity = new MultimediaEntity ();
        entity.setId(getId());
        entity.setNombre(nombre);
        entity.setTipo(tipo);
        entity.setUrl(url);
        
        if(this.getMascotaAdopcion() != null)
            entity.setMascota(this.getMascotaAdopcion().toEntity());
        if(this.getMascotaEncontrada() != null)
            entity.setMascotaEncontrada(this.getMascotaEncontrada().toEntity());
        if(this.getMascotaPerdida() != null)
            entity.setMascotaPerdida(this.getMascotaPerdida().toEntity());
        if(this.getPublicidad() != null)
            entity.setPublicidad(this.getPublicidad().toEntity());
        
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
     * @return the mascotaAdopcion
     */
    public MascotaAdopcionDTO getMascotaAdopcion() {
        return mascotaAdopcion;
    }

    /**
     * @param mascotaAdopcion the mascotaAdopcion to set
     */
    public void setMascotaAdopcion(MascotaAdopcionDTO mascotaAdopcion) {
        this.mascotaAdopcion = mascotaAdopcion;
    }

    /**
     * @return the mascotaEncontrada
     */
    public MascotaEncontradaDTO getMascotaEncontrada() {
        return mascotaEncontrada;
    }

    /**
     * @param mascotaEncontrada the mascotaEncontrada to set
     */
    public void setMascotaEncontrada(MascotaEncontradaDTO mascotaEncontrada) {
        this.mascotaEncontrada = mascotaEncontrada;
    }

    /**
     * @return the mascotaPerdida
     */
    public MascotaPerdidaDTO getMascotaPerdida() {
        return mascotaPerdida;
    }

    /**
     * @param mascotaPerdida the mascotaPerdida to set
     */
    public void setMascotaPerdida(MascotaPerdidaDTO mascotaPerdida) {
        this.mascotaPerdida = mascotaPerdida;
    }

    /**
     * @return the publicidad
     */
    public PublicidadDTO getPublicidad() {
        return publicidad;
    }

    /**
     * @param publicidad the publicidad to set
     */
    public void setPublicidad(PublicidadDTO publicidad) {
        this.publicidad = publicidad;
    }
    
}
