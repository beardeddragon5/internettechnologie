package de.matthias_ramsauer.itt;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author matthias
 */
@Path("/dj")
public class DJService {
    
    private final Map<String, String> playlists;

    public DJService() {
        this.playlists = new HashMap<>();

        this.playlists.put("Jesus", "Hallelulia, WTF, Amen");
        this.playlists.put("Hamster", "Attack, Bones, Human Flesh");
    }
    
    @OPTIONS
    @Produces("application/json")
    public Response options() {
        return Response.status(Status.OK)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Content-Type")
                .header("Access-Control-Allow-Methods", "GET, POST")
                .entity("{\"status\":\"ok\"}")
                .build();
    }
    
    @GET
    @Produces("text/plain")
    public String getDJs() {
        return playlists.keySet().stream().collect(Collectors.joining(", "));
    }
    
    @POST
    @Path("/{name}")
    @Produces("text/plain")
    public Response getPlayList(@PathParam("name") String name) {
        if (!playlists.containsKey(name)) {
            return Response.status(Status.NOT_FOUND).entity("DJ not found").build();
        }
        return Response.ok(playlists.get(name)).build();
    }
    
}
