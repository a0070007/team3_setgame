package iss.sa40.team3.business;

import iss.sa40.team3.model.Player;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@Stateless
public class PlayerBean {
    
    @PersistenceContext private EntityManager em;
    
    public Player findPlayer(String email){
        
        return(em.find(Player.class, email));
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
