package iss.sa40.team3.model;

import java.util.List;
import javax.faces.bean.ApplicationScoped;

@ApplicationScoped
public class Main {
    
    List<Game> games;

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
