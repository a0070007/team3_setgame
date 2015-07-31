package iss.sa40.team3.rest;

import iss.sa40.team3.business.CardBean;
import iss.sa40.team3.model.Card;
import iss.sa40.team3.model.Game;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
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
        
        List<Card> table = new ArrayList<Card>();
        List<Card> deck = cardBean.getShuffledDeck();
        List<List<Card>> list = cardBean.issueCards(deck, table, 12);
        deck = (List<Card>) list.get(0);
        table = (List<Card>) list.get(1);
        
        //include null check for title, duration and maxPlayers
        //convert duration from string to time
        Game game = new Game(title, duration, deck, table, maxPlayers);
        
        
        return null;
    }
}
