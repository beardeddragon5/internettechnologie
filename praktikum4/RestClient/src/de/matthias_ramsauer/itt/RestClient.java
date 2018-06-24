/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.matthias_ramsauer.itt;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.Buffer;
import java.nio.file.Paths;
import java.util.Scanner;
import javax.xml.ws.Response;
import sun.net.www.http.HttpClient;

/**
 *
 * @author matthias
 */
public class RestClient {
    
    private static String readAll(InputStream input) {
        Scanner s = new Scanner(input).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
    
    private static String request(String method, String url) throws MalformedURLException, IOException, Exception {
        final HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod(method);
        connection.setDoOutput(false);
        connection.setDoInput(true);
        
        final String response = readAll(connection.getInputStream());
        final int code = connection.getResponseCode();
        if (code != 200) {
            throw new Exception(response);
        }
        return response;
    }
    
    private static String post(String basePath, String resource) throws Exception {
        return request("POST", basePath + resource);
    }
    
    private static String get(String basePath, String resource) throws Exception {
        return request("GET", basePath + resource);
    }
    
    private static String getDJs(String basePath) throws Exception {
        return get(basePath, "/dj");
    }
    
    private static String getPlaylist(String basePath, String dj) throws Exception {
        return post(basePath, Paths.get("/dj", dj).toString());
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        String basePath = "http://localhost:8080/api";
        if (args.length >= 1) {
            basePath = args[0];
        }
        
        System.out.println(getDJs(basePath));
        System.out.println(getPlaylist(basePath, "Jesus"));
        
        try {
            System.out.println(getPlaylist(basePath, "not%20found"));
        } catch(FileNotFoundException e) {
            System.out.println("DJ not found");
        }
        
    }
    
}
