/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Tobia Gasparoni
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable
{
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

    @PodamExclude
    @OneToMany(mappedBy = "usuario")
    private List<MascotaEncontradaEntity> mascotasEncontradas = new ArrayList<MascotaEncontradaEntity>();
    
    @PodamExclude
    @OneToMany(mappedBy = "usuario")
    private List<MascotaPerdidaEntity> mascotasPerdidas = new ArrayList<MascotaPerdidaEntity>();
    
    @PodamExclude
    @OneToMany(mappedBy = "usuario")
    private List<MascotaAdopcionEntity> mascotasAdopcion = new ArrayList<MascotaAdopcionEntity>();
    
    @PodamExclude
    @OneToMany(mappedBy = "usuario")
    private List<ProcesoAdopcionEntity> procesosAdopcion = new ArrayList<ProcesoAdopcionEntity>();
    
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
    public Long getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(Long celular) {
        this.celular = celular;
    }

    /**
     * @return the mascotasEncontradas
     */
    public List<MascotaEncontradaEntity> getMascotasEncontradas() {
        return mascotasEncontradas;
    }

    /**
     * @param mascotasEncontradas the mascotasEncontradas to set
     */
    public void setMascotasEncontradas(List<MascotaEncontradaEntity> mascotasEncontradas) {
        this.mascotasEncontradas = mascotasEncontradas;
    }

    /**
     * @return the mascotasPerdidas
     */
    public List<MascotaPerdidaEntity> getMascotasPerdidas() {
        return mascotasPerdidas;
    }

    /**
     * @param mascotasPerdidas the mascotasPerdidas to set
     */
    public void setMascotasPerdidas(List<MascotaPerdidaEntity> mascotasPerdidas) {
        this.mascotasPerdidas = mascotasPerdidas;
    }

    /**
     * @return the mascotasAdopcion
     */
    public List<MascotaAdopcionEntity> getMascotasAdopcion() {
        return mascotasAdopcion;
    }

    /**
     * @param mascotasAdopcion the mascotasAdopcion to set
     */
    public void setMascotasAdopcion(List<MascotaAdopcionEntity> mascotasAdopcion) {
        this.mascotasAdopcion = mascotasAdopcion;
    }

    /**
     * @return the procesosAdopcion
     */
    public List<ProcesoAdopcionEntity> getProcesosAdopcion() {
        return procesosAdopcion;
    }

    /**
     * @param procesosAdopcion the procesosAdopcion to set
     */
    public void setProcesosAdopcion(List<ProcesoAdopcionEntity> procesosAdopcion) {
        this.procesosAdopcion = procesosAdopcion;
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
        else if(!(o instanceof UsuarioEntity)) return false;
        else 
        {
            UsuarioEntity m = (UsuarioEntity) o;
            return m.hashCode() == this.hashCode() && m.getId().equals(this.getId());
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.nombre);
        hash = 59 * hash + Objects.hashCode(this.ciudad);
        hash = 59 * hash + Objects.hashCode(this.correo);
        hash = 59 * hash + Objects.hashCode(this.celular);
        return hash;
    }
    
}
