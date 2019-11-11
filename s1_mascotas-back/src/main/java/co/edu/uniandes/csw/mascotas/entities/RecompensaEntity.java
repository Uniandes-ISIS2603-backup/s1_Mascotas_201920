/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.entities;

import co.edu.uniandes.csw.mascotas.podam.PositiveIntegerStrategy;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;
import uk.co.jemos.podam.common.PodamStrategyValue;

/**
 *
 * @author William Smith
 */

@Entity
public class RecompensaEntity extends BaseEntity implements Serializable {
    
    @PodamStrategyValue(PositiveIntegerStrategy.class)
    private Integer monto;
    
    private Boolean pagado;
    
    @PodamExclude
    @OneToOne
    private MascotaPerdidaEntity mascotaPerdida;

    /**
     * @return the monto
     */
    public Integer getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(Integer monto) {
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

    /**
     * @return the mascotaPerdida
     */
    public MascotaPerdidaEntity getMascotaPerdida() {
        return mascotaPerdida;
    }

    /**
     * @param mascotaPerdida the mascotaPerdida to set
     */
    public void setMascotaPerdida(MascotaPerdidaEntity mascotaPerdida) {
        this.mascotaPerdida = mascotaPerdida;
    }
    
}
