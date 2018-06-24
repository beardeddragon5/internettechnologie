/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.matthias_ramsauer.itt;

import java.util.Random;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 *
 * @author matthias
 */
@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Weather {
    
    public int getTemperature(String date) {
        DateMatcher.requireValidDate(date);
        return new Random(date.hashCode()).nextInt(31);
    }
    
    public String getRain(String date) {
        DateMatcher.requireValidDate(date);
        return new Random(date.hashCode()).nextBoolean() ? "sun" : "rain";
    }
}
