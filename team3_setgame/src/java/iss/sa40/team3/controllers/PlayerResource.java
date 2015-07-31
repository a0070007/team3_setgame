package iss.sa40.team3.controllers;

import iss.sa40.team3.business.PlayerBean;
import iss.sa40.team3.model.Player;
import java.awt.PageAttributes;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static javax.swing.text.html.FormSubmitEvent.MethodType.GET;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path ("/player")
public class PlayerResource extends HttpServlet{
    
    @EJB private PlayerBean playerBean; 
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response getPlayer(@FormParam("email") String email){
        Player player = new Player();
        if (email != null){
            player = playerBean.findPlayer(email);
        }
        if(player == null)
            return (Response.status(Response.Status.NOT_FOUND).build());
        return (Response.ok(player.toJson()).build());
    }
    
}
