package iss.sa40.team3.rest;

import iss.sa40.team3.business.PlayerBean;
import iss.sa40.team3.model.Game;
import iss.sa40.team3.model.Main;
import iss.sa40.team3.model.Player;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path ("/main")
@Produces(MediaType.APPLICATION_JSON)
public class MainResource {
    
    @EJB private PlayerBean playerBean;
    @Inject private Main main;
    
    @GET
    @Path("/getallgames")
    public Response getAllGames(){
        
        List<Game> games = main.getGames();
        
        JsonArrayBuilder gamesArray = Json.createArrayBuilder();
        for (Game game : games){
            gamesArray.add(game.toJson());
        }
        
        if(games == null)
            return (Response.status(Response.Status.NOT_FOUND).build());
        return (Response.ok(Json.createObjectBuilder()
                            .add("gamesArray", gamesArray)
                            .build()).build());
    }
    
    @GET
    @Path("/gettopplayers")
    public Response getTopPlayers(){
        List<Player> topPlayers = playerBean.getTop10Players();
        
        JsonArrayBuilder topPlayersArray = Json.createArrayBuilder();
        for (Player player : topPlayers){
            topPlayersArray.add(player.toJson());
        };
        
        if(topPlayers == null)
            return (Response.status(Response.Status.NOT_FOUND).build());
        return (Response.ok(Json.createObjectBuilder()
                            .add("topPlayersArray", topPlayersArray)
                            .build()).build());
        
    }
    
    
}
