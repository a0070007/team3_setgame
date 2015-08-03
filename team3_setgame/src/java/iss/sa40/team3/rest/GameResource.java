package iss.sa40.team3.rest;

import iss.sa40.team3.business.CardBean;
import iss.sa40.team3.business.PlayerBean;
import iss.sa40.team3.model.Card;
import iss.sa40.team3.model.Game;
import iss.sa40.team3.model.Main;
import iss.sa40.team3.model.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path ("/game")
@Produces(MediaType.APPLICATION_JSON)
public class GameResource {
    
    @EJB private CardBean cardBean;
    @EJB private PlayerBean playerBean; 
    @Inject private Main main;
    
    @GET
    @Path("{title}/{duration}/{maxPlayers}")
    public Response createGame(
            @PathParam("title") String title, 
            @PathParam("duration")String duration, 
            @PathParam("maxPlayers") int maxPlayers){
        
        Card[]  table = new Card[12];
        List<Card> deck = cardBean.getShuffledDeck();
        List<Object> list = cardBean.issueCards(deck, table, 12);
        deck = (List<Card>) list.get(0);
        table = (Card[]) list.get(1);
        while(!cardBean.setExists(table)){
            deck.clear();
            Arrays.fill(table, null);
            list.clear();
            deck = cardBean.getShuffledDeck();
            list = cardBean.issueCards(deck, table, 12);
            deck = (List<Card>) list.get(0);
            table = (Card[]) list.get(1);
        }
        
        Game game = new Game();
        if (title != null && duration != null && maxPlayers>0){
            game.setTitle(title);
            game.setDuration(duration);
            game.setDeck(deck);
            game.setTable(table);
            game.setMaxPlayers(maxPlayers);
            game.setRound(1);
        }
        
        List<Game> games = main.getGames();
        games.add(game);
        main.setGames(games);
        
        return (Response.ok(game.toJson()).build());
    }
    
    @GET
    @Path("{gameId}")
    public Response getGame(@PathParam("gameId") int gameId){
        
        List<Game> games = main.getGames();
        Game selectedGame = new Game();
        for(Game game : games){
            if(game.getGameId() == gameId){
                selectedGame = game;
            }
        }
        if(selectedGame == null)
            return (Response.status(Response.Status.NOT_FOUND).build());
        return (Response.ok(selectedGame.toJson()).build());
    }
    
    @GET
    @Path("{gameId}/{position1}/{cardId1}/{position2}/{cardId2}/{position3}/{cardId3}/{email}")
    public Response verifyChosenSet(
            @PathParam("gameId") int gameId,
            @PathParam("position1") int position1,
            @PathParam("cardId1") int cardId1,
            @PathParam("position2") int position2,
            @PathParam("cardId2") int cardId2,
            @PathParam("position3") int position3,
            @PathParam("cardId3") int cardId3,
            @PathParam("email") String email){
        
        //Check if the three cards make a set
        Card card1 = new Card(
                Integer.parseInt(Integer.toString(cardId1).substring(0,1)), 
                Integer.parseInt(Integer.toString(cardId1).substring(1,2)), 
                Integer.parseInt(Integer.toString(cardId1).substring(2,3)),
                Integer.parseInt(Integer.toString(cardId1).substring(3,4)));
        
        Card card2 = new Card(
                Integer.parseInt(Integer.toString(cardId2).substring(0,1)), 
                Integer.parseInt(Integer.toString(cardId2).substring(1,2)), 
                Integer.parseInt(Integer.toString(cardId2).substring(2,3)),
                Integer.parseInt(Integer.toString(cardId2).substring(3,4)));
        
        Card card3 = new Card(
                Integer.parseInt(Integer.toString(cardId3).substring(0,1)), 
                Integer.parseInt(Integer.toString(cardId3).substring(1,2)), 
                Integer.parseInt(Integer.toString(cardId3).substring(2,3)),
                Integer.parseInt(Integer.toString(cardId3).substring(3,4)));
        
        Card[] set = new Card[3];
        set[0] = card1;
        set[1] = card2;
        set[2] = card3;
        
        int[] position = new int[3];
        position[1] = position1;
        position[2] = position2;
        position[3] = position3;
        
        boolean isSet = cardBean.setExists(set);
        if(isSet == false)
            return (Response.status(Response.Status.BAD_REQUEST).build());
        
        //Get the game
        List<Game> games = main.getGames();
        Game selectedGame = new Game();
        for(Game game : games){
            if(game.getGameId() == gameId){
                selectedGame = game;
            }
        }
        if(selectedGame == null)
            return (Response.status(Response.Status.NOT_FOUND).build());
        
        //Get Player
        Player player = new Player();
        if (email != null){
            player = playerBean.findPlayer(email);
        }
        if(player == null)
            return (Response.status(Response.Status.NOT_FOUND).build());
        
        //Add +1 to player's score
        HashMap<Player, Integer> playerscore = selectedGame.getPlayerscore();
        playerscore.put(player, playerscore.get(player)+1);
        
        //remove the set (3 cards) from table and round++
        selectedGame.setTable(cardBean.removeCards(position, selectedGame.getTable()));
        selectedGame.setRound(selectedGame.getRound()+1);
        
        //if no more cards in deck, return response 'ok' only
        if(selectedGame.getDeck() == null)
            return (Response.ok().build());
        
        //if there are cards in deck, return response 'ok' and id of 3 cards
        List<Object> list = cardBean.issueCards(selectedGame.getDeck(), selectedGame.getTable(), 3);
        selectedGame.setDeck((List<Card>) list.get(0));
        selectedGame.setTable((Card[]) list.get(1));
        
        List<Card> newCards = (List<Card>) list.get(2);
        List<String> newCardsId = new ArrayList<>();
        for(Card card: newCards){
            int number = card.getNumber();
            int shading = card.getShading();
            int color = card.getColor();
            int shape = card.getShape();
            String cardId = String.valueOf(number) 
                        + String.valueOf(shading)
                        + String.valueOf(color)
                        + String.valueOf(shape);
            newCardsId.add(cardId);
        }
        
        return (Response.ok(Json.createObjectBuilder()
                            .add("newCard1", newCardsId.get(0))
                            .add("newCard2", newCardsId.get(1))
                            .add("newCard3", newCardsId.get(2))
                            .build()).build());
    }
    
    @GET
    @Path("verifyTableSet/{gameId}")
    public Response verifyTableSet(@PathParam("gameId") int gameId){
    
        List<Game> games = main.getGames();
        Game selectedGame = new Game();
        for(Game game : games){
            if(game.getGameId() == gameId){
                selectedGame = game;
            }
        }
        if(selectedGame == null)
            return (Response.status(Response.Status.NOT_FOUND).build());
        
        if(!cardBean.setExists(selectedGame.getTable())){
            return (Response.status(Response.Status.BAD_REQUEST).build());
        }
        
        return (Response.ok().build());
    }
    
    @GET
    @Path("{gameId}/{email}")
    public Response joinGame(
            @PathParam("gameId") int gameId,
            @PathParam("email") String email){
        
        //Get game
        List<Game> games = main.getGames();
        Game selectedGame = new Game();
        for(Game game : games){
            if(game.getGameId() == gameId){
                selectedGame = game;
            }
        }
        if(selectedGame == null)
            return (Response.status(Response.Status.NOT_FOUND).build());
        
        //Get player
        Player player = new Player();
        if (email != null){
            player = playerBean.findPlayer(email);
        }
        if(player == null)
            return (Response.status(Response.Status.NOT_FOUND).build());
        
        //Add player to game
        HashMap<Player, Integer> playerscore = selectedGame.getPlayerscore();
        if(playerscore == null)
            playerscore = new HashMap<>();
        playerscore.put(player, 0);
        selectedGame.setPlayerscore(playerscore);
        
        return (Response.ok(selectedGame.toJson()).build());
    }
    
}
