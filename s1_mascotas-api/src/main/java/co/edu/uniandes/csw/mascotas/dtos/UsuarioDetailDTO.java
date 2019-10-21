/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.MascotaAdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaEncontradaEntity;
import co.edu.uniandes.csw.mascotas.entities.MascotaPerdidaEntity;
import co.edu.uniandes.csw.mascotas.entities.ProcesoAdopcionEntity;
import co.edu.uniandes.csw.mascotas.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tobia Gasparoni
 */
public class UsuarioDetailDTO extends UsuarioDTO implements Serializable {

    private List<MascotaEncontradaDTO> mascotasEncontradas = new ArrayList<MascotaEncontradaDTO>();
    
    private List<MascotaPerdidaDTO> mascotasPerdidas = new ArrayList<MascotaPerdidaDTO>();
    
    private List<MascotaAdopcionDTO> mascotasAdopcion = new ArrayList<MascotaAdopcionDTO>();
    
    private List<ProcesoAdopcionDTO> procesosAdopcion = new ArrayList<ProcesoAdopcionDTO>();
    
    private UsuarioEntity usuario;
    
    public UsuarioDetailDTO()
    {
        
    }
    
    public UsuarioDetailDTO(UsuarioEntity usuario)
    {
        super(usuario);
        if (usuario != null) {
            /**
             * Mascotas en adopcion
             */
            if (usuario.getMascotasAdopcion()!= null) {
                mascotasAdopcion = new ArrayList<MascotaAdopcionDTO>();
                
                for (MascotaAdopcionEntity mascotaAdopcionEntity : usuario.getMascotasAdopcion()) {
                   mascotasAdopcion.add(new MascotaAdopcionDTO(mascotaAdopcionEntity));
                }
            }
            
            /**
             * Mascotas encontradas
             */
            if (usuario.getMascotasEncontradas()!= null) {
                mascotasEncontradas = new ArrayList<MascotaEncontradaDTO>();
                
                for (MascotaEncontradaEntity mascotaEncontradaEntity : usuario.getMascotasEncontradas()) {
                   mascotasEncontradas.add(new MascotaEncontradaDTO(mascotaEncontradaEntity));
                }
            }
            
            /**
             * Mascotas perdidas
             */
            if (usuario.getMascotasPerdidas()!= null) {
                mascotasPerdidas = new ArrayList<MascotaPerdidaDTO>();
                
                for (MascotaPerdidaEntity mascotaPerdidaEntity : usuario.getMascotasPerdidas()) {
                   mascotasPerdidas.add(new MascotaPerdidaDTO(mascotaPerdidaEntity));
                }
            }
            
            /**
             * Procesos de adopcion
             */
            if (usuario.getProcesosAdopcion()!= null) {
                procesosAdopcion = new ArrayList<ProcesoAdopcionDTO>();
                
                for (ProcesoAdopcionEntity procesoAdopcionEntity : usuario.getProcesosAdopcion()) {
                   procesosAdopcion.add(new ProcesoAdopcionDTO(procesoAdopcionEntity));
                }
            }
        }
        
    }

    /**
     * @return the mascotasEncontradas
     */
    public List<MascotaEncontradaDTO> getMascotasEncontradas() {
        return mascotasEncontradas;
    }

    /**
     * @param mascotasEncontradas the mascotasEncontradas to set
     */
    public void setMascotasEncontradas(List<MascotaEncontradaDTO> mascotasEncontradas) {
        this.mascotasEncontradas = mascotasEncontradas;
    }

    /**
     * @return the mascotasPerdidas
     */
    public List<MascotaPerdidaDTO> getMascotasPerdidas() {
        return mascotasPerdidas;
    }

    /**
     * @param mascotasPerdidas the mascotasPerdidas to set
     */
    public void setMascotasPerdidas(List<MascotaPerdidaDTO> mascotasPerdidas) {
        this.mascotasPerdidas = mascotasPerdidas;
    }

    /**
     * @return the mascotasAdopcion
     */
    public List<MascotaAdopcionDTO> getMascotasAdopcion() {
        return mascotasAdopcion;
    }

    /**
     * @param mascotasAdopcion the mascotasAdopcion to set
     */
    public void setMascotasAdopcion(List<MascotaAdopcionDTO> mascotasAdopcion) {
        this.mascotasAdopcion = mascotasAdopcion;
    }

    /**
     * @return the procesosAdopcion
     */
    public List<ProcesoAdopcionDTO> getProcesosAdopcion() {
        return procesosAdopcion;
    }

    /**
     * @param procesosAdopcion the procesosAdopcion to set
     */
    public void setProcesosAdopcion(List<ProcesoAdopcionDTO> procesosAdopcion) {
        this.procesosAdopcion = procesosAdopcion;
    }
}
