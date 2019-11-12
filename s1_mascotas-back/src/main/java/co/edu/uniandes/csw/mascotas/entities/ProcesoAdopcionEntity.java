/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;
import co.edu.uniandes.csw.mascotas.podam.CalificacionEstrategy;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author William Smith
 */
@Entity
public class ProcesoAdopcionEntity extends BaseEntity implements Serializable {
    
    private String estado;
    
    private String comentario;
    
    @PodamStrategyValue(CalificacionEstrategy.class)
    private Integer calificacion;
    
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;
    
    @PodamExclude
    @ManyToOne
    private MascotaAdopcionEntity mascotaAdopcion;
    
   
    
    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return the calificacion
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
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
     * @return the mascotaAdopcion
     */
    public MascotaAdopcionEntity getMascotaAdopcion() {
        return mascotaAdopcion;
    }

    /**
     * @param mascotaAdopcion the mascotaAdopcion to set
     */
    public void setMascotaAdopcion(MascotaAdopcionEntity mascotaAdopcion) {
        this.mascotaAdopcion = mascotaAdopcion;
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
        else if(!(o instanceof ProcesoAdopcionEntity)) return false;
        else 
        {
            ProcesoAdopcionEntity m = (ProcesoAdopcionEntity) o;
            return m.hashCode() == this.hashCode() && m.getId().equals(this.getId());
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.estado);
        hash = 67 * hash + Objects.hashCode(this.comentario);
        hash = 67 * hash + Objects.hashCode(this.calificacion);
        hash = 67 * hash + Objects.hashCode(this.usuario);
        return hash;
    }
    
}
