/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.ejb;

import co.edu.uniandes.csw.mascotas.entities.ProcesoAdopcionEntity;
import co.edu.uniandes.csw.mascotas.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.mascotas.persistence.ProcesoAdopcionPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author William Smith
 */
@Stateless
public class ProcesoAdopcionLogic {
    
    
    @Inject
    private ProcesoAdopcionPersistence persistence;
    
    public ProcesoAdopcionEntity createProcesoAdopcion(ProcesoAdopcionEntity proceso) throws BusinessLogicException{
        if(proceso.getEstado()==null){
            throw new BusinessLogicException("El estado del proceso se encuentra vacio");
        }
        proceso=persistence.create(proceso);
        return proceso;
    }
    
}
