/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.MascotaPerdidaEntity;
import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * MascotaPerdidaDTO. Los DTO contienen
 * las representaciones de los JSON que se transfieren entre el cliente y el
 * servidor.
 *
 * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 * {
        "descripcion": string,
        "especie": int,
        "fechaPerdida": date,
        "id": int,
        "lugar": string,
        "raza": string,
        *usuario": ,
        "recompensa"
    }
 *   {
 *      "id": number,
 *      "name": string,
 *      "isbn": string,
 *      "image: string,
 *      "description": string,
 *      "publishingdate": date,
 *      "editorial": {@link EditorialDTO}
 *   }
 * </pre> Por ejemplo una editorial se representa asi:<br>
 *
 * <pre>
 *
 *  {
        "descripcion": "BUENAS",
        "especie": 0,
        "fechaPerdida": "2668-11-05T05:00:00Z[UTC]",
        "id": 1,
        "lugar": "BUENAS",
        "raza": "BUENAS",
        *usuario": ,
        "recompensa"
    }
 *
 * </pre>
 * @author lily 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MascotaPerdidaDTO implements Serializable{
    
    /**
     * Recompensa
     */
    private RecompensaDTO recompensa;
    /**
     * Id de la mascota
     */
    private Long id;
    
    /**
     * Raza de la mascota perdida
     */
    private String raza;
    
    /**
     * La especie de la mascota perdida
     */
    private Integer especie;
    
    /**
     * La descripción de la mascota perdida
     */
    private String descripcion;
    
    /**
     * El lugar donde se perdió la mascota
     */
    private String lugar;

    /**
     * La fecha en la que se perdió la mascota
     */
    private Date fechaPerdida;
    
    /**
     * Usuario dueño de la mascota
     */
    private UsuarioDTO usuario;
    
    /**
     * Constructor vacio
     */
    public MascotaPerdidaDTO() {

    }
    /**
     * Constructor mascotas perdidas
     * @param entidad mascota 
     */
    public MascotaPerdidaDTO(MascotaPerdidaEntity entidad) {
        if( entidad != null){
        setDescripcion(entidad.getDescripcion());
        
        setEspecie((entidad.getEspecie()));
        
        setFechaPerdida(entidad.getFechaPerdida());
        
        setRaza(entidad.getRaza());
        
        setLugar(entidad.getLugar());
        
        if (entidad.getRecompensa() != null) {
            setRecompensa(new RecompensaDTO (entidad.getRecompensa()));
        } else {
            setRecompensa(null);
        }
        
        if (entidad.getUsuario() != null) {
            setUsuario(new UsuarioDTO (entidad.getUsuario()));
        } else {
            setUsuario(null);
        }
        setId(entidad.getId());
        }
    }
    /**
     * MascotaPerdidaDTO a entity 
     * @return MascotaPerdidaEntity
     */
    public MascotaPerdidaEntity toEntity() {

        MascotaPerdidaEntity entidad = new MascotaPerdidaEntity();

        entidad.setDescripcion((this.getDescripcion()));

        entidad.setEspecie(this.getEspecie());

        entidad.setFechaPerdida( this.getFechaPerdida() );

        entidad.setLugar(this.getLugar());

        entidad.setRaza(this.getRaza());
        
        if (this.recompensa != null)
            entidad.setRecompensa(this.getRecompensa().toEntity());
        
        if (this.usuario != null)
            entidad.setUsuario(this.getUsuario().toEntity());
        
        entidad.setId(this.getId());
        
        return entidad;

    }
    /**
     * 
     * @return recompensa 
     */
    public RecompensaDTO getRecompensa() {
        return recompensa;
    }
    /**
     * 
     * @param recompensa La recompensa a cambiar 
     */
    public void setRecompensa(RecompensaDTO recompensa) {
        this.recompensa = recompensa;
    }
    
    /**
     * 
     * @return ID
     */
    public Long getId() {
        return id;
    }
    /**
     * 
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
     * @return the fechaPerdida
     */
    public Date getFechaPerdida() {
        return fechaPerdida;
    }

    /**
     * @param fechaPerdida the fechaPerdida to set
     */
    public void setFechaPerdida(Date fechaPerdida) {
        this.fechaPerdida = fechaPerdida;
    }
    /**
     * 
     * @return usuario
     */
    public UsuarioDTO getUsuario() {
        return usuario;
    }
    /**
     * 
     * @param usuario el usuario a cambiar
     */

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
    
    
}
