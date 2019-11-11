/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Tomas Langebaek
 */
@Entity
public class MultimediaEntity extends BaseEntity implements Serializable{
    
    private String url;
  
    private String nombre;
    
    private String tipo;
    
    @PodamExclude
    @ManyToOne(optional = true, cascade = CascadeType.PERSIST)
    private PublicidadEntity publicidad;
    
    @PodamExclude
    @ManyToOne(optional = true,cascade = CascadeType.PERSIST)
    private MascotaAdopcionEntity mascotaAdopcion;
    
    @PodamExclude
    @ManyToOne(optional = true,cascade = CascadeType.PERSIST)
    private MascotaEncontradaEntity mascotaEncontrada;
    
    @PodamExclude
    @ManyToOne(optional = true,cascade = CascadeType.PERSIST)
    private MascotaPerdidaEntity mascotaPerdida;

    public PublicidadEntity getPublicidad() {
        return publicidad;
    }

    public void setPublicidad(PublicidadEntity publicidad) {
        this.publicidad = publicidad;
    }
    
    public MascotaEncontradaEntity getMascotaEncontrada() {
        return mascotaEncontrada;
    }

    public void setMascotaEncontrada(MascotaEncontradaEntity mascotaEncontrada) {
        this.mascotaEncontrada = mascotaEncontrada;
    }

    public MascotaPerdidaEntity getMascotaPerdida() {
        return mascotaPerdida;
    }

    public void setMascotaPerdida(MascotaPerdidaEntity mascotaPerdida) {
        this.mascotaPerdida = mascotaPerdida;
    }
    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
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
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the mascota
     */
    public MascotaAdopcionEntity getMascota() {
        return mascotaAdopcion;
    }

    /**
     * @param mascota the mascota to set
     */
    public void setMascota(MascotaAdopcionEntity mascota) {
        this.mascotaAdopcion = mascota;
    }
    
}