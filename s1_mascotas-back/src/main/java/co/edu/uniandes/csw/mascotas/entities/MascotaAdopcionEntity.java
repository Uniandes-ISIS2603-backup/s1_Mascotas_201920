/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import co.edu.uniandes.csw.mascotas.podam.EspecieEstrategy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author Tomás Langebaek
 */
@Entity
public class MascotaAdopcionEntity extends BaseEntity implements Serializable {

    /**
     * El número del enum especie del animal en adopcion.
     */
    @PodamStrategyValue(EspecieEstrategy.class)
    private Integer especie;
    /**
     * La raza del animal en adopcion
     */
    private String raza;
    /**
     * La descripcion del animal en adopcion
     */
    private String descripcion;
    /**
     * El lugar donde el animal esta en adopcion
     */
    private String lugar;
    /**
     * La historia de vida del animal en adopcion
     */
    private String historia;
    /**
     * Los procesos de adopcion del animal
     */
    @PodamExclude
    @OneToMany(mappedBy = "mascotaAdopcion")
    private List<ProcesoAdopcionEntity> procesos = new ArrayList<>();
    /**
     * El uruario asociado a la mascota
     */
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;
    /**
     * Las fotos de la mascota
     */
    @PodamExclude
    @OneToMany(mappedBy = "mascotaAdopcion", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<MultimediaEntity> multimedia = new ArrayList<>();

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
     * @return the historia
     */
    public String getHistoria() {
        return historia;
    }

    /**
     * @param historia the historia to set
     */
    public void setHistoria(String historia) {
        this.historia = historia;
    }

    /**
     * @return the procesos
     */
    public List<ProcesoAdopcionEntity> getProcesos() {
        return procesos;
    }

    /**
     * @param procesos the procesos to set
     */
    public void setProcesos(List<ProcesoAdopcionEntity> procesos) {
        this.procesos = procesos;
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
     * Compara dos objetos
     * @param o El objeto a comparar
     * @return true si son iguales, false si no
     */
    @Override
    public boolean equals(Object o)
    {
        if(o == null) return false;
        else if(!(o instanceof MascotaAdopcionEntity)) return false;
        else 
        {
            MascotaAdopcionEntity m = (MascotaAdopcionEntity) o;
            return m.hashCode() == this.hashCode() && m.getId().equals(this.getId());
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.especie);
        hash = 67 * hash + Objects.hashCode(this.raza);
        hash = 67 * hash + Objects.hashCode(this.descripcion);
        hash = 67 * hash + Objects.hashCode(this.lugar);
        hash = 67 * hash + Objects.hashCode(this.historia);
        hash = 67 * hash + Objects.hashCode(this.procesos);
        hash = 67 * hash + Objects.hashCode(this.usuario);
        hash = 67 * hash + Objects.hashCode(this.multimedia);
        return hash;
    }
}
