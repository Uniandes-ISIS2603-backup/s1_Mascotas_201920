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

    private List<MascotaEncontradaDTO> mascotasEncontradas;
    
    private List<MascotaPerdidaDTO> mascotasPerdidas;
    
    private List<MascotaAdopcionDTO> mascotasAdopcion;
    
    private List<ProcesoAdopcionDTO> procesosAdopcion;
    
    //private UsuarioEntity usuario;
    
    public UsuarioDetailDTO()
    {
        mascotasEncontradas = new ArrayList<>();
    
        mascotasPerdidas = new ArrayList<>();

        mascotasAdopcion = new ArrayList<>();

        procesosAdopcion = new ArrayList<>();
    }
    
    public UsuarioDetailDTO(UsuarioEntity usuario)
    {
        super(usuario);
        
        mascotasEncontradas = new ArrayList<>();
    
        mascotasPerdidas = new ArrayList<>();

        mascotasAdopcion = new ArrayList<>();

        procesosAdopcion = new ArrayList<>();
        
        if (usuario != null) {
            /**
             * Mascotas en adopcion
             */
            if (usuario.getMascotasAdopcion()!= null) {
                mascotasAdopcion = new ArrayList<>();
                
                for (MascotaAdopcionEntity mascotaAdopcionEntity : usuario.getMascotasAdopcion()) {
                   mascotasAdopcion.add(new MascotaAdopcionDTO(mascotaAdopcionEntity));
                }
            }
            
            /**
             * Mascotas encontradas
             */
            if (usuario.getMascotasEncontradas()!= null) {
                mascotasEncontradas = new ArrayList<>();
                
                for (MascotaEncontradaEntity mascotaEncontradaEntity : usuario.getMascotasEncontradas()) {
                   mascotasEncontradas.add(new MascotaEncontradaDTO(mascotaEncontradaEntity));
                }
            }
            
            /**
             * Mascotas perdidas
             */
            if (usuario.getMascotasPerdidas()!= null) {
                mascotasPerdidas = new ArrayList<>();
                
                for (MascotaPerdidaEntity mascotaPerdidaEntity : usuario.getMascotasPerdidas()) {
                   mascotasPerdidas.add(new MascotaPerdidaDTO(mascotaPerdidaEntity));
                }
            }
            
            /**
             * Procesos de adopcion
             */
            if (usuario.getProcesosAdopcion()!= null) {
                procesosAdopcion = new ArrayList<>();
                
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
    
    @Override
    public UsuarioEntity toEntity() {
        UsuarioEntity usuarioEntity = super.toEntity();
        
        if(mascotasAdopcion != null)
        {
            List<MascotaAdopcionEntity> mascotasEntity = new ArrayList<>();
            for(MascotaAdopcionDTO dtoMascota: mascotasAdopcion)
            {
                mascotasEntity.add(dtoMascota.toEntity());
            }
            usuarioEntity.setMascotasAdopcion(mascotasEntity);
        }
        if(mascotasEncontradas != null)
        {
            List<MascotaEncontradaEntity> mascotasEntity = new ArrayList<>();
            for(MascotaEncontradaDTO dtoMascota: mascotasEncontradas)
            {
                mascotasEntity.add(dtoMascota.toEntity());
            }
            usuarioEntity.setMascotasEncontradas(mascotasEntity);
        }
        if(mascotasPerdidas != null)
        {
            List<MascotaPerdidaEntity> mascotasEntity = new ArrayList<>();
            for(MascotaPerdidaDTO dtoMascota: mascotasPerdidas)
            {
                mascotasEntity.add(dtoMascota.toEntity());
            }
            usuarioEntity.setMascotasPerdidas(mascotasEntity);
        }
        if(procesosAdopcion != null)
        {
            List<ProcesoAdopcionEntity> procesosEntity = new ArrayList<>();
            for(ProcesoAdopcionDTO dtoProceso: procesosAdopcion)
            {
                procesosEntity.add(dtoProceso.toEntity());
            }
            usuarioEntity.setProcesosAdopcion(procesosEntity);
        }
        
        return usuarioEntity;
    }
}
