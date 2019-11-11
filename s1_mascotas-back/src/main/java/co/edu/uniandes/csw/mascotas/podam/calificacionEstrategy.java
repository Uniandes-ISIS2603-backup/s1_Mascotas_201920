/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.podam;

/**
 *
 * @author Lily Duque
 */

import java.util.Random;
import uk.co.jemos.podam.common.AttributeStrategy;

public class calificacionEstrategy implements AttributeStrategy<Integer> {
    @Override
    public Integer getValue() {
        int y=(int)((Math.random()*10)%6);
        while (0>=y || 5<y)
        {
            y=(int)((Math.random()*50)%6);
        }
        return new Integer(y);
    }
}
