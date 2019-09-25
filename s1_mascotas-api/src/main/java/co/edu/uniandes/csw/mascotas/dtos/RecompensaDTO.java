/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.dtos;

import co.edu.uniandes.csw.mascotas.entities.RecompensaEntity;
import java.io.Serializable;

/**
 *
 * @author William Smith
 */
public class RecompensaDTO implements Serializable{
    
    private Long id;
    private int monto;
    private Boolean pagado;
    
//    private MascotaPerdidaDTO mascotaPerdida;
    
    public RecompensaDTO(){
        
    }
    
    public RecompensaDTO(RecompensaEntity recompensaEntity){
        if(recompensaEntity!=null){
            this.id=recompensaEntity.getId();
            this.monto=recompensaEntity.getMonto();
            this.pagado=recompensaEntity.getPagado();
//            if(recompensaEntity.getMascotaPerdida()!=null){
//                this.mascotaPerdida=new MascotaPerdidaDTO(recompensaEntity.getMascotaPerdida());
//            }
//            else{
//                this.mascotaPerdida=null;
//            }
        }
    }
    
    public RecompensaEntity toEntity(){
        RecompensaEntity recompensaEntity = new RecompensaEntity();
        recompensaEntity.setId(this.getId());
        recompensaEntity.setMonto(this.getMonto());
        recompensaEntity.setPagado(this.getPagado());
//        if(this.getMascotaPerdida()!=null){
//            recompensaEntity.setMascotaAdopcion(this.mascotaAdopcion().toEntity());
//        }
        return recompensaEntity;
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
     * @return the monto
     */
    public int getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(int monto) {
        this.monto = monto;
    }

    /**
     * @return the pagado
     */
    public Boolean getPagado() {
        return pagado;
    }

    /**
     * @param pagado the pagado to set
     */
    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }
    
}
