/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.matthias_ramsauer.itt;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 *
 * @author matthias
 */
public class SoapWebClient {
    
    private static PartyService getPartyService(String basePath, int id) throws MalformedURLException {
        return new PartyService(new URL(String.format("%s/party/%d?wsdl", basePath, id)));
    }
    
    /**
     * @param args the command line arguments
     * @throws java.net.MalformedURLException
     */
    public static void main(String[] args) throws MalformedURLException {
        String basePath = "http://localhost:8080";
        if (args.length >= 1) {
            basePath = args[0];
        }
        
        final WeatherService weatherService = new WeatherService(new URL(String.format("%s/weather?wsdl", basePath)));
        final Weather weather = weatherService.getWeatherPort();
        
        final PartyService[] partyServices = new PartyService[] {
            getPartyService(basePath, 0),
            getPartyService(basePath, 1),
            getPartyService(basePath, 2)
        };
        
        for (final PartyService partyService : partyServices) {
            final Party party = partyService.getPartyPort();
            party.invite("Andi");
            party.invite("Jasmin");
            
            final String date = party.getDate();
            
            System.out.printf("DJ: %s, Date: %s, Temperature: %d°C, Weather: %s\n", party.getDj(), date, weather.getTemperature(date), weather.getRain(date));
        }
        
        partyServices[0].getPartyPort().invite("Matthias");
    }
    
}
