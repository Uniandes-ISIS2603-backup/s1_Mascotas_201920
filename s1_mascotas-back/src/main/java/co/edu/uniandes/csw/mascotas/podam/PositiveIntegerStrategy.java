/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.podam;

import uk.co.jemos.podam.common.AttributeStrategy;

/**
 *
 * @author Estudiante
 */
public class PositiveIntegerStrategy implements AttributeStrategy<Integer>
{

    @Override
    public Integer getValue() {
        return new Integer((int)((Math.random()*10)%2)); 
    }
    
}
