package iss.sa40.team3.model;

import java.util.List;
import java.util.HashMap;
import java.sql.Time;
import java.util.ArrayList;
import javax.json.JsonObject;

public class Game {

    static int gameId=0;
    String title;
    Time duration; 
    int maxPlayers;
    List<Card> deck = new ArrayList<Card>();
    List<Card> table = new ArrayList<Card>();
    HashMap<Player, Integer> playerscore;

    public Game(String title, Time duration, List<Card> deck, List<Card> table, int maxPlayers) {
        gameId++;
        this.title = title;
        this.duration = duration;
        this.maxPlayers = maxPlayers;
        this.deck = deck;
        this.table = table;
        playerscore = new HashMap<>();
    }

    public static int getGameId() {
        return gameId;
    }

    public static void setGameId(int gameId) {
        Game.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }
    
    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public List<Card> getTable() {
        return table;
    }

    public void setTable(List<Card> table) {
        this.table = table;
    }

    public HashMap<Player, Integer> getPlayerscore() {
        return playerscore;
    }

    public void setPlayerscore(HashMap<Player, Integer> playerscore) {
        this.playerscore = playerscore;
    }
    
    public JsonObject toJson(){
//        
//        JsonArrayBuilder deckArray = Json.createArrayBuilder();
//        
//        for(int i = 0; i < deck.size(); i++){
//        }
//        
//        return (Json.createObjectBuilder()
//                .add("gameId", gameId)
//                .add("title", title)
//                .add("duration",duration.toString())
//                .add("deck", deckArray)
//                .add("table", table)
//                .add("deck", deck.toString())
//                .build());
        return null;
    }

    @Override
    public String toString() {
        return "Game{" + "title=" + title + ", duration=" + duration + ", maxPlayers=" + maxPlayers + ", deck=" + deck + ", table=" + table + ", playerscore=" + playerscore + '}';
    }
    
}
