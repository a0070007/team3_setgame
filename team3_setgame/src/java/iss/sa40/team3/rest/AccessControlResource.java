package iss.sa40.team3.rest;

import iss.sa40.team3.business.PlayerBean;
import iss.sa40.team3.model.Player;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path ("/access")
public class AccessControlResource {
    
    @EJB private PlayerBean playerBean; 
    
    @GET
    @Path("{email}/{password}")
    public Response login(
            @PathParam("email") String email, 
            @PathParam("password") String password){
        Player player = new Player();
        if (email != null){
            player = playerBean.findPlayer(email);
        }
        if(!player.getPassword().equals(password) || player==null)
            return (Response.status(Response.Status.BAD_REQUEST).build());
        return (Response.status(Response.Status.ACCEPTED).build());
    }
    
    /**
     *
     * @param req
     */
    @GET
    public Response logout(@Context HttpServletRequest req){
        req.getSession().invalidate();
        return (Response.ok().build());
    }
    
}
