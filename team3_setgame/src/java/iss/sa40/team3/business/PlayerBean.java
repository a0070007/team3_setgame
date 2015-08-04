package iss.sa40.team3.business;

import iss.sa40.team3.model.Game;
import iss.sa40.team3.model.Main;
import iss.sa40.team3.model.Player;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;

@Stateless
public class PlayerBean {
    
    @PersistenceContext private EntityManager em;
    @Inject private Main main;
    
    public Player findPlayer(String email){
        
        return(em.find(Player.class, email));
    }
    
    public Player findPlayerFromGame (String email, int gameId){
        List<Game> games = main.getGames();
        Game selectedGame = null;
        for(Game game : games){
            if(game.getGameId() == gameId){
                selectedGame = game;
            }
        }
        if(selectedGame == null)
            return (null);
        
        Set<Player> playerSet= selectedGame.getPlayerscore().keySet();
        List<Player> playerList = new ArrayList<>();
        playerList.addAll(playerSet);
        Player player = null;
        for(Player p : playerList) {
            System.out.println(p.getEmail());
            System.out.println("*****");
            if(p.getEmail().contentEquals(email)){
                player = p;
            }
        }
        return player;
        
    }
    
    public boolean updatePlayer(String email, String password, String name, int highscore){
        
        Player player = findPlayer(email);
        if(player == null)
            return false;
        player.setPassword(password);
        player.setName(name);
        player.setHighscore(highscore);
        try{
        em.merge(player);
        }
        catch(IllegalArgumentException e){
            return false;
        }
        return true;
    }
    
    public boolean insertPlayer(String email, String password, String name, int highscore){
        Player p = new Player();
        p.setEmail(email);
        p.setPassword(password);
        p.setName(name);
        p.setHighscore(highscore);
        try{
            em.persist(p);
        }
        catch (PersistenceException e){
            return (false);
        }
        return true;
        
    }
    
    public List<Player> getTop10Players(){
        List<Player> result = em.createQuery(
                "SELECT p FROM Player p ORDER BY p.highscore DESC")
                .setMaxResults(2).getResultList();
        return ((result.size() > 0)? result:null);
    }
    
}
