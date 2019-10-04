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
public class MascotaAdopcionEntity extends BaseEntity implements Serializable{
    
    /**
     * El número del enum especie del animal en adopcion.
     */
    @PodamStrategyValue (EspecieEstrategy.class)
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
    
    @PodamExclude
    @OneToMany(mappedBy = "mascotaAdopcion")
    private List<ProcesoAdopcionEntity> procesos = new ArrayList<>();
    
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;
    

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
}
