/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.ProcesoAdopcionEntity;
import java.io.Serializable;

/**
 *
 * @author William Smith
 */
public class ProcesoAdopcionDTO implements Serializable{
    
    private Long id;
    private String estado;
    private String comentario;
    private Integer calificacion;
    
    private UsuarioDTO usuario;
    private MascotaAdopcionDTO mascotaAdopcion;
    
    public ProcesoAdopcionDTO(){
        
    }
    public ProcesoAdopcionDTO(ProcesoAdopcionEntity procesoAdopcionEntity) {
        if (procesoAdopcionEntity != null) {
            this.id = procesoAdopcionEntity.getId();
            this.estado = procesoAdopcionEntity.getEstado();
            this.comentario=procesoAdopcionEntity.getComentario();
            this.calificacion=procesoAdopcionEntity.getCalificacion();
            if(procesoAdopcionEntity.getMascotaAdopcion()!=null){
                this.mascotaAdopcion=new MascotaAdopcionDTO(procesoAdopcionEntity.getMascotaAdopcion());
            }
            else{
                this.mascotaAdopcion=null;
            }
            if(procesoAdopcionEntity.getUsuario()!=null){
                this.usuario=new UsuarioDTO(procesoAdopcionEntity.getUsuario());
            }
            else{
                this.usuario=null;
            }
        }
    }
    
    public ProcesoAdopcionEntity toEntity() {
        ProcesoAdopcionEntity procesoAdopcionEntity = new ProcesoAdopcionEntity();
        procesoAdopcionEntity.setId(this.id);
        procesoAdopcionEntity.setEstado(this.estado);
        procesoAdopcionEntity.setComentario(this.comentario);
        procesoAdopcionEntity.setCalificacion(this.calificacion);
        if(this.getMascotaAdopcion()!=null){
            procesoAdopcionEntity.setMascotaAdopcion(this.getMascotaAdopcion().toEntity());
        }
        if(this.getUsuario()!=null){
            procesoAdopcionEntity.setUsuario(this.getUsuario().toEntity());
        }
        return procesoAdopcionEntity;
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
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }
    
    /**
     * @return the calificacion
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * @param mascotaAdopcion the mascotaAdopcion to set
     */
    public void setMascotaAdopcion(MascotaAdopcionDTO mascotaAdopcion) {
        this.mascotaAdopcion = mascotaAdopcion;
    }
    
    /**
     * @return the mascotaAdopcion
     */
    public MascotaAdopcionDTO getMascotaAdopcion() {
        return mascotaAdopcion;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
    
    /**
     * @return the usuario
     */
    public UsuarioDTO getUsuario() {
        return usuario;
    }
    
}
