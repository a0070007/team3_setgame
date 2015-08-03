package iss.sa40.team3.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;

public class Game {

    static int gameId=0;
    String title;
    String duration; 
    int maxPlayers;
    int round =0;
    List<Card> deck = new ArrayList<>();
    List<Card> table = new ArrayList<>();
    HashMap<Player, Integer> playerscore;
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    String startTime = df.format(Calendar.getInstance().getTime());

    public Game(String title, String duration, List<Card> deck, List<Card> table, int maxPlayers) {
        gameId++;
        this.title = title;
        this.duration = duration;
        this.maxPlayers = maxPlayers;
        this.deck = deck;
        this.table = table;
        playerscore = new HashMap<>();
    }

    public Game() {
    }
    
    public static int getGameId() {
        return gameId;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
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

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public String getStartTime() {
        return startTime;
    }
    
    public JsonObject toJson(){
        
        JsonArrayBuilder deckArray = Json.createArrayBuilder();
        for(Card card : deck){
            deckArray.add(Json.createObjectBuilder()
                    .add("number", card.getNumber())
                    .add("shading", card.getShading())
                    .add("color", card.getColor())
                    .add("shape", card.getShape()));
        }
        deckArray.build();
        
        JsonArrayBuilder tableArray = Json.createArrayBuilder();
        
        for(Card card : table){
            deckArray.add(Json.createObjectBuilder()
                    .add("number", card.getNumber())
                    .add("shading", card.getShading())
                    .add("color", card.getColor())
                    .add("shape", card.getShape()));
        }
        tableArray.build();
        
        JsonArrayBuilder playerScoreArray = Json.createArrayBuilder();
        Set playerSet = playerscore.keySet();
        Iterator playerIterator = playerSet.iterator();
        while (playerIterator.hasNext()){
            Player key = (Player) playerIterator.next();
            System.out.println(key);
            playerScoreArray.add(Json.createObjectBuilder()
                    .add("playerEmail", key.getEmail())
                    .add("currentScore", playerscore.get(key))
                    .build());
        }
        playerScoreArray.build();
        
        return (Json.createObjectBuilder()
                .add("gameId", gameId)
                .add("title", title)
                .add("duration",duration)
                .add("maxPlayers", maxPlayers)
                .add("round", round)
                .add("deck", deckArray)
                .add("table", tableArray)
                .add("playerScoreArray", playerScoreArray)
                .add("startTime", startTime)
                .build());
    }

    @Override
    public String toString() {
        return "Game{" + "title=" + title + ", duration=" + duration + ", maxPlayers=" + maxPlayers + ", round=" + round + ", deck=" + deck + ", table=" + table + ", playerscore=" + playerscore + ", df=" + df + ", startTime=" + startTime + '}';
    }
    
}
