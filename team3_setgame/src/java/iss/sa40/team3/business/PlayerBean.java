package iss.sa40.team3.business;

import iss.sa40.team3.model.Player;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

@Stateless
public class PlayerBean {
    
    @PersistenceContext private EntityManager em;
    
    public Player findPlayer(String email){
        
        return(em.find(Player.class, email));
    }
    
    public boolean updatePlayer(String email, String password, String name, int highscore){
        
        Query query = em.createQuery("Update player set password = :password, name ="
                + " :name, highscore  = :highscore where email = :email");
        query.setParameter("password", password);
        query.setParameter("name", name);
        query.setParameter("highscore", highscore);
        query.setParameter("email", email);
        int result = query.executeUpdate();
        if(result>0)
            return true;
        else
            return false;
        
        //return((query.executeUpdate() == 1)? true:false);
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
        TypedQuery<Player> query = em.createQuery(
                "SELECT * FROM table ORDER BY highscore DESC LIMIT 10", Player.class);
        List<Player> result = query.getResultList();
        return ((result.size() > 0)? result:null);
    }
    
}
