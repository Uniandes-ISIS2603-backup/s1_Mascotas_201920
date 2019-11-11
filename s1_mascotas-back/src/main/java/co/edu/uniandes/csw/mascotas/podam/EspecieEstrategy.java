/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.mascotas.podam;

/**
 * Estrategy de especie, solo puede tomar valores entre 0 y 1. Debido a que solo existen dos enums de especie.
 * @author Lily 
 */
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.co.jemos.podam.common.AttributeStrategy;

public class EspecieEstrategy implements AttributeStrategy<Integer> {
    @Override
    public Integer getValue() {
        int y=0;
        try {
            Random r = SecureRandom.getInstanceStrong();
            y= r.nextInt(1);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(EspecieEstrategy.class.getName()).log(Level.SEVERE, null, ex);
        }
        return y;
    }
}
