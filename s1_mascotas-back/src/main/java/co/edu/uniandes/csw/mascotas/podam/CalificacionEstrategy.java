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

public class CalificacionEstrategy implements AttributeStrategy<Integer> {
    @Override
    public Integer getValue() {
        Random r = new Random();
        int y=r.nextInt(5);
        while (0>=y || 5<y)
        {
            y=r.nextInt(5);
        }
        return y;
    }
}
