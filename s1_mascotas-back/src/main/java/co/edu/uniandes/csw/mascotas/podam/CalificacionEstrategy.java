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

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.co.jemos.podam.common.AttributeStrategy;

public class CalificacionEstrategy implements AttributeStrategy<Integer> {
     
    @Override
    public Integer getValue() {
        Random r;
        int y=1;
        try {
            r = SecureRandom.getInstanceStrong();
            y=r.nextInt(5);
            while (0>=y || 5<y)
            {
                y=r.nextInt(5);
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CalificacionEstrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return y;
    }
}
