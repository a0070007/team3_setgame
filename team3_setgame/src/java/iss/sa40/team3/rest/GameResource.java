package iss.sa40.team3.rest;

import iss.sa40.team3.business.CardBean;
import iss.sa40.team3.model.Game;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@RequestScoped
@Path ("/player")
public class GameResource {
    
    @EJB private CardBean cardBean;
    
    @GET
    @Path("{title}/{duration}/{maxPlayers}")
    public Response createGame(
            @PathParam("title") String title, 
            @PathParam("duration")String duration, 
            @PathParam("maxPlayers") String maxPlayers){
        
        return null;
    }
}
