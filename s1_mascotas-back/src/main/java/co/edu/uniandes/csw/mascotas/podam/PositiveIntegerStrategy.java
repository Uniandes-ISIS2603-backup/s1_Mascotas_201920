/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.podam;

import java.util.Random;
import uk.co.jemos.podam.common.AttributeStrategy;

/**
 *
 * @author Estudiante
 */
public class PositiveIntegerStrategy implements AttributeStrategy<Integer>
{
    Random random = new Random();
    @Override
    public Integer getValue() {
       Integer i = new Integer(random.nextInt()); 
       while(i.equals(0))
           i = new Integer(random.nextInt());
       
       return (i>0)? i: -i;
    }
}
