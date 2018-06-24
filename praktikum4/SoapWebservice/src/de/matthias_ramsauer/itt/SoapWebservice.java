/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.matthias_ramsauer.itt;

import javax.xml.ws.Endpoint;

/**
 *
 * @author matthias
 */
public class SoapWebservice {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String host = "localhost";
        String port = "8080";
        if (args.length >= 1) {
            host = args[0];
        }
        if (args.length >= 2) {
            port = args[1];
        }
        
        final Party parties[] = new Party[] {
            new Party("2018-06-24", "me", 100),
            new Party("0000-00-00", "Jesus", 5),
            new Party("3000-00-00", "Jesus", Short.MAX_VALUE)
        };
        final Weather weather = new Weather();
        for (int id = 0; id < parties.length; id++) {
            Endpoint.publish(String.format("http://%s:%s/party/%d", host, port, id), parties[id]);
        }
        Endpoint.publish(String.format("http://%s:%s/weather", host, port), weather);
    }
    
}
