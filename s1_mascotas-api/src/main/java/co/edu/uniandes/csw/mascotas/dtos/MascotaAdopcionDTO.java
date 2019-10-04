/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import java.io.Serializable;

/**
 *
 * @author Tomás Langebaek
 */
public class MascotaAdopcionDTO implements Serializable {

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

    private Long id;

    private UsuarioDTO usuario;

    public MascotaAdopcionDTO() {

    }

    public MascotaAdopcionDTO(MascotaAdopcionEntity entidad) {

        setDescripcion(entidad.getDescripcion());

        setEspecie((entidad.getEspecie()));

        setHistoria(entidad.getHistoria());

        setId(entidad.getId());

        setRaza(entidad.getRaza());

        setLugar(entidad.getLugar());

        if (entidad.getUsuario() != null) {
            this.usuario = new UsuarioDTO(entidad.getUsuario());
        }

    }

    public MascotaAdopcionEntity toEntity() {

        MascotaAdopcionEntity entidad = new MascotaAdopcionEntity();

        entidad.setId(this.getId());

        entidad.setDescripcion((this.getDescripcion()));

        entidad.setEspecie(this.getEspecie());

        entidad.setHistoria(this.getHistoria());

        entidad.setLugar(this.getLugar());

        entidad.setRaza(this.getRaza());

         if (usuario != null) {
            entidad.setUsuario(usuario.toEntity());
        }

        return entidad;

    }

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
     * @return the usuario
     */
    public UsuarioDTO getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

}
