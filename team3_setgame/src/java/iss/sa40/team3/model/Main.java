package iss.sa40.team3.model;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class Main {
    
    List<Game> games = new ArrayList<Game>();

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "Main{" + "games=" + games + '}';
    }
    
    
}
